package com.eriksandsten.chromedpt;

import com.eriksandsten.chromedpt.request.domain.target.TargetType;
import com.eriksandsten.chromedpt.utils.WHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.eriksandsten.chromedpt.request.page.EnableRequest;
import com.eriksandsten.chromedpt.request.page.NavigateRequest;
import com.eriksandsten.chromedpt.request.runtime.EvaluateRequest;
import com.eriksandsten.chromedpt.request.target.*;
import com.eriksandsten.chromedpt.response.page.EnableResponse;
import com.eriksandsten.chromedpt.response.page.NavigateResponse;
import com.eriksandsten.chromedpt.response.domain.runtime.EvaluateResponse;
import com.eriksandsten.chromedpt.response.target.*;
import com.eriksandsten.chromedpt.utils.ChromeHelper;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChromeClient {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private WHelper wHelper = new WHelper();

    private final String applicationPageTitle;
    private final String jsonAPIBaseUrl;
    private final Integer remoteDebuggingPort;
    private final String userDataDirectory;
    private final Boolean enableLogging;
    private final Map<String, String> additionalParams;

    private ChromeDTPWebSocketClient chromeWSClient;
    private ChromeSession currentSession;

    // public ChromeClient(String applicationPageTitle, String chromeDevToolsJsonUrl, String chromeWindowClassName) {
    public ChromeClient(String applicationPageTitle, String jsonAPIBaseUrl, Integer remoteDebuggingPort, String userDataDirectory, Boolean enableLogging, Map<String, String> additionalParams) {
        this.applicationPageTitle = applicationPageTitle;
        this.jsonAPIBaseUrl = jsonAPIBaseUrl;
        this.remoteDebuggingPort = remoteDebuggingPort;
        this.userDataDirectory = userDataDirectory;
        this.enableLogging = enableLogging;
        this.additionalParams = additionalParams;
        /*
        this.applicationPageTitle = applicationPageTitle;
        this.chromeDevToolsJsonUrl = chromeDevToolsJsonUrl;
        this.chromeWindowClassName = chromeWindowClassName;
        */
    }

    public void loadPage(String url) throws IOException, URISyntaxException, InterruptedException {
        loadPage(url, false);
    }

    public void loadPage(String url, boolean hasAttemptedToStartNewChrome) throws IOException, URISyntaxException, InterruptedException {
        try {
            chromeWSClient = connectToChrome().webSocketClient();
            final ChromeSession session = createNewOrGetExistingSession(url);
            activateMediaServerChannelChromeWindow();

            if (session.createdBrandNewSession()) {
                // chromeWSClient.addCallback("Page.loadEventFired", (params) -> setSessionWindowTitle(session, url));
            } else {
                // chromeWSClient.addCallback("Page.loadEventFired", (params) -> setSessionWindowTitle(session, url));
                navigateToUrl(session.sessionId(), url);
            }
        } catch (final ConnectException e) {
            if (!hasAttemptedToStartNewChrome) {
                ChromeHelper.startNewChromeInstance(remoteDebuggingPort, userDataDirectory, enableLogging, additionalParams); // Attempt to start a new Chrome instance (only one time)
                // createNewOrGetExistingSession(url); // Channel page could have been saved and now re-opened from the previous session - use it
                Thread.sleep(10000); // TODO: fix!
                System.out.println("==================================== APA =======================================");
                loadPage(url, true);
            }
        }
    }

    public Optional<EvaluateResponse> evaluateJavascript(String javascript) throws IOException, URISyntaxException, InterruptedException {
        chromeWSClient = connectToChrome().webSocketClient();
        return getExistingSession().map((session) -> {
            EvaluateResponse evaluateResponse = (EvaluateResponse) chromeWSClient.executeCommand(
                    new EvaluateRequest(session.sessionId(), javascript), EvaluateResponse.class);

            return evaluateResponse;
        });
    }

    private void navigateToUrl(String sessionId, String url) {
        NavigateResponse navigateResponse = (NavigateResponse) chromeWSClient.executeCommand(new NavigateRequest(sessionId, url), NavigateResponse.class);
        setSessionWindowTitle(sessionId, url);
    }

    private void setSessionWindowTitle(String sessionId, String url) {
        // Allente sets the window title after some seconds, override it by setting it later
        IntStream.range(0, 5).forEach(iteration -> {
                // TODO: should iterate 3 times, bug with queue.take()
                int a = iteration;
                // Thread.sleep(1000);
                EvaluateResponse setDocumentTitleResponse = (EvaluateResponse) chromeWSClient.executeCommand(
                        new EvaluateRequest(sessionId, "document.title = '%s [%s]';".formatted(applicationPageTitle, url.substring(url.lastIndexOf("/")))), EvaluateResponse.class);

        });
    }

    private Optional<ChromeSession> getExistingSession() {
        final Optional<GetTargetsResponse.TargetInfo> existingChannelTarget = getExistingChannelTarget();

        if (existingChannelTarget.isPresent()) {
            final String sessionId = attachToAndActivateTarget(existingChannelTarget.get().getTargetId());
            final EnableResponse enableResponse = (EnableResponse) chromeWSClient.executeCommand(new EnableRequest(sessionId), EnableResponse.class);

            currentSession = new ChromeSession(sessionId, false);
            return Optional.of(currentSession);
        } else {
            return Optional.empty();
        }
    }

    private ChromeSession createNewOrGetExistingSession(String url) {
        final Optional<GetTargetsResponse.TargetInfo> existingChannelTarget = getExistingChannelTarget();

        final String targetId = existingChannelTarget.map(GetTargetsResponse.TargetInfo::getTargetId).orElseGet(() -> {
            CreateTargetResponse createTargetResponse = (CreateTargetResponse) chromeWSClient.executeCommand(new CreateTargetRequest(url), CreateTargetResponse.class);
            return createTargetResponse.getResult().getTargetId();
        });

        final String sessionId = attachToAndActivateTarget(targetId);
        final EnableResponse enableResponse = (EnableResponse) chromeWSClient.executeCommand(new EnableRequest(sessionId), EnableResponse.class);

        currentSession = new ChromeSession(sessionId, existingChannelTarget.isEmpty());
        return currentSession;
    }

    private String attachToAndActivateTarget(String targetId) {
        final AttachToTargetResponse attachToTargetResponse = (AttachToTargetResponse) chromeWSClient.executeCommand(
                new AttachToTargetRequest(targetId, true), AttachToTargetResponse.class);

        // Does not activate the Chrome window, only the tab
        final ActivateTargetResponse activateTargetResponse = (ActivateTargetResponse)
                chromeWSClient.executeCommand(new ActivateTargetRequest(targetId, true), ActivateTargetResponse.class);

        return attachToTargetResponse.getResult().getSessionId();
    }

    private Optional<GetTargetsResponse.TargetInfo> getExistingChannelTarget() {
        GetTargetsResponse getTargetsCommand = (GetTargetsResponse) chromeWSClient.executeCommand(new GetTargetsRequest(), GetTargetsResponse.class);

        return getTargetsCommand.getResult().getTargetInfos().stream()
                .filter(target -> target.getType().equals(TargetType.PAGE.getType()) && target.getTitle().startsWith(applicationPageTitle)).findFirst();
    }

    private void activateMediaServerChannelChromeWindow() {
        /*
        final List<String> chromeWindowCaptions = windowHelper.getWindowCaptionsByWindowClassName(chromeWindowClassName);
        chromeWindowCaptions.stream().filter(caption -> caption.startsWith(applicationPageTitle)).findFirst().ifPresent(caption -> {
            long windowToActivate = windowHelper.getWindowHandleByWindowTitle(caption);
            windowHelper.setActiveWindow(windowToActivate);
        });
        */
    }

    private ChromeConnection connectToChrome() throws IOException, InterruptedException, URISyntaxException {
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            final HttpRequest fetchBrowserTargetsRequest = HttpRequest.newBuilder().uri(new URI(jsonAPIBaseUrl)).build();
            final HttpResponse<String> fetchBrowserTargetsResponse = httpClient.send(fetchBrowserTargetsRequest, HttpResponse.BodyHandlers.ofString());
            final BrowserTarget[] browserTargets = objectMapper.readValue(fetchBrowserTargetsResponse.body(), BrowserTarget[].class);
            final Map<String, List<BrowserTarget>> browserTargetsByType = Arrays.stream(browserTargets).collect(Collectors.groupingBy(BrowserTarget::getType));

            BrowserTarget browserTarget = Arrays.stream(browserTargets).filter(bt ->
                    bt.getTitle().startsWith(applicationPageTitle)).findFirst().orElseGet(() -> browserTargets[0]);

            final ChromeDTPWebSocketClient webSocketClient = new ChromeDTPWebSocketClient(browserTarget.getWebSocketDebuggerUrl());
            webSocketClient.connectBlocking();

            return new ChromeConnection(webSocketClient, browserTarget);
        }
    }
}
