package com.eriksandsten.chromedtp;

import com.eriksandsten.chromedtp.request.domain.target.TargetType;
import com.eriksandsten.chromedtp.request.io.CloseRequest;
import com.eriksandsten.chromedtp.request.io.ReadRequest;
import com.eriksandsten.chromedtp.request.page.PrintToPDFRequest;
import com.eriksandsten.chromedtp.response.io.ReadResponse;
import com.eriksandsten.chromedtp.response.page.PrintToPDFResponse;
import com.eriksandsten.chromedtp.utils.NewChromeSession;
import com.eriksandsten.chromedtp.utils.WHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.eriksandsten.chromedtp.request.page.EnableRequest;
import com.eriksandsten.chromedtp.request.page.NavigateRequest;
import com.eriksandsten.chromedtp.request.runtime.EvaluateRequest;
import com.eriksandsten.chromedtp.request.target.*;
import com.eriksandsten.chromedtp.response.page.EnableResponse;
import com.eriksandsten.chromedtp.response.page.NavigateResponse;
import com.eriksandsten.chromedtp.response.domain.runtime.EvaluateResponse;
import com.eriksandsten.chromedtp.response.target.*;
import com.eriksandsten.chromedtp.utils.ChromeHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(ChromeClient.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private WHelper wHelper = new WHelper();

    private final String applicationPageTitle;
    private final String jsonAPIBaseUrl;
    private final Integer remoteDebuggingPort;
    private final String userDataDirectory;
    private final Boolean enableLogging;
    private final String initialUrl;
    private final List<String> additionalParams;
    private ChromeSession currentSession;

    public ChromeClient(String applicationPageTitle, String jsonAPIBaseUrl, Integer remoteDebuggingPort, String userDataDirectory, Boolean enableLogging, String initialUrl, List<String> additionalParams) {
        this.applicationPageTitle = applicationPageTitle;
        this.jsonAPIBaseUrl = jsonAPIBaseUrl;
        this.remoteDebuggingPort = remoteDebuggingPort;
        this.userDataDirectory = userDataDirectory;
        this.enableLogging = enableLogging;
        this.initialUrl = initialUrl;
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
            ChromeConnection chromeConnection = connectToChrome();
            final ChromeSession session = getExistingSession();
            activateMediaServerChannelChromeWindow();

            if (session.sessionId() == null) {
                // chromeWSClient.addCallback("Page.loadEventFired", (params) -> setSessionWindowTitle(session, url));
                navigateToUrl(chromeConnection.browserTarget().getId(), url);
            } else {
                // chromeWSClient.addCallback("Page.loadEventFired", (params) -> setSessionWindowTitle(session, url));
                navigateToUrl(session.sessionId(), url);
            }
        } catch (final ConnectException e) {
            if (!hasAttemptedToStartNewChrome) {
                ChromeHelper.startNewChromeInstance(remoteDebuggingPort, userDataDirectory, enableLogging, additionalParams); // Attempt to start a new Chrome instance (only one time)
                // getExistingSession(url); // Channel page could have been saved and now re-opened from the previous session - use it
                Thread.sleep(10000); // TODO: fix!
                System.out.println("==================================== APA =======================================");
                loadPage(url, true);
            }
        }
    }

    public EvaluateResponse evaluateJavascript(String javascript) throws IOException, JsonProcessingException, InterruptedException, URISyntaxException, InterruptedException {
        ChromeConnection chromeConnection = connectToChrome();
        ChromeSession existingSession = getExistingSession();

        if (existingSession != null) {
            EvaluateResponse evaluateResponse = ChromeDTPWebSocketClient.executeCommand(new EvaluateRequest(existingSession.sessionId(), javascript), EvaluateResponse.class);
            return evaluateResponse;
        } else {
            return null;
        }
    }

    public PrintToPDFResponse printPDF() throws IOException, InterruptedException, URISyntaxException {
        ChromeConnection chromeConnection = connectToChrome();
        ChromeSession existingSession = getExistingSession();

        if (existingSession != null) {
            PrintToPDFResponse printToPdfResponse = ChromeDTPWebSocketClient.executeCommand(new PrintToPDFRequest(existingSession.sessionId()), PrintToPDFResponse.class);
            return printToPdfResponse;
        } else {
            return null;
        }
    }

    public ReadResponse readIOStream(String streamHandle, Integer offset, Integer size) throws IOException, InterruptedException, URISyntaxException {
        ChromeConnection chromeConnection = connectToChrome();
        ChromeSession existingSession = getExistingSession();

        if (existingSession != null) {
            ReadResponse readResponse = ChromeDTPWebSocketClient.executeCommand(new ReadRequest(streamHandle, offset, size), ReadResponse.class);
            return readResponse;
        } else {
            return null;
        }
    }

    public void closeIOStream(String streamHandle) throws IOException, InterruptedException, URISyntaxException {
        ChromeConnection chromeConnection = connectToChrome();
        ChromeSession existingSession = getExistingSession();

        if (existingSession != null) {
            ChromeDTPWebSocketClient.executeCommand(new CloseRequest(streamHandle), ReadResponse.class);
        }
    }

    private void navigateToUrl(String sessionId, String url) throws JsonProcessingException, InterruptedException {
        NavigateResponse navigateResponse = ChromeDTPWebSocketClient.executeCommand(new NavigateRequest(sessionId, url), NavigateResponse.class);
        setSessionWindowTitle(sessionId, url);
    }

    private void setSessionWindowTitle(String sessionId, String url) throws JsonProcessingException, InterruptedException {
        // Allente sets the window title after some seconds, override it by setting it later
        for (int i = 0; i <= 5; i++) {
                EvaluateResponse setDocumentTitleResponse = ChromeDTPWebSocketClient.executeCommand(
                        new EvaluateRequest(sessionId, "document.title = '%s [%s]';".formatted(applicationPageTitle, url.substring(url.lastIndexOf("/")))), EvaluateResponse.class);
        }
    }
/*

java.lang.NullPointerException: Cannot invoke "java.util.List.stream()" because the return value of "com.eriksandsten.chromedtp.response.target.GetTargetsResponse$Result.getTargetInfos()" is null
	at com.eriksandsten.chromedtp.ChromeClient.getExistingPageTarget(ChromeClient.java:119) ~[chromedtp.jar:na]
 */
    private String getExistingPageTarget() throws JsonProcessingException, InterruptedException {
        GetTargetsResponse getTargetsCommand = ChromeDTPWebSocketClient.executeCommand(new GetTargetsRequest(), GetTargetsResponse.class);

        Optional<String> existingTarget = getTargetsCommand.getResult().getTargetInfos().stream()
            .filter(target -> target.getType().equals(TargetType.PAGE.getType()) && target.getTitle().startsWith(applicationPageTitle))
            .findFirst().map(GetTargetsResponse.TargetInfo::getTargetId);

        if (existingTarget.isPresent()) {
            return existingTarget.get();
        } else {
            CreateTargetResponse createTargetResponse = ChromeDTPWebSocketClient.executeCommand(new CreateTargetRequest(initialUrl), CreateTargetResponse.class);
            return createTargetResponse.getResult().getTargetId();
        }
    }

    private ChromeSession getExistingSession() throws JsonProcessingException, InterruptedException {
        final String existingPageTarget = getExistingPageTarget();
        final ActivateTargetResponse activateTargetResponse = attachToAndActivateTarget(existingPageTarget);
        final String sessionId = activateTargetResponse.getResult().getSessionId();

        if (sessionId != null) {
            final EnableResponse enableResponse = ChromeDTPWebSocketClient.executeCommand(new EnableRequest(sessionId), EnableResponse.class);
        }

        currentSession = new ChromeSession(sessionId, false);
        return currentSession;
    }

    private ActivateTargetResponse attachToAndActivateTarget(String targetId) throws JsonProcessingException, InterruptedException {
        final AttachToTargetResponse attachToTargetResponse = ChromeDTPWebSocketClient.executeCommand(
                new AttachToTargetRequest(targetId, true), AttachToTargetResponse.class);

        // Does not activate the Chrome window, only the tab
        return ChromeDTPWebSocketClient.executeCommand(new ActivateTargetRequest(targetId, true), ActivateTargetResponse.class);
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

            try {
                ChromeDTPWebSocketClient.getSocketClient().connectBlocking();
            } catch (IllegalStateException e) {
                logger.warn("WebSocket client already connected");
            }

            return new ChromeConnection(webSocketClient, browserTarget);
        }
    }
}
