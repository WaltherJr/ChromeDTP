package com.eriksandsten.chromedtp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChromeHelper {
    private static String radxaRockServerUrl;

    public ChromeHelper(String radxaRockServerUrl) {
        ChromeHelper.radxaRockServerUrl = radxaRockServerUrl;
    }

    public static void startNewChromeInstance(Integer remoteDebuggingPort, String userDataDirectory, boolean enableLogging, List<String> additionalParams) {
        try {
            String[] command = buildCommand(remoteDebuggingPort, userDataDirectory, enableLogging, additionalParams);
            final ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.start();

        } catch (final IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    private static String[] buildCommand(Integer remoteDebuggingPort, String userDataDirectory, boolean enableLogging, List<String> additionalParams) {
        List<String> command = new ArrayList<>();
        command.add("chrome.exe");

        if (remoteDebuggingPort != null) {
            command.add("--remote-debugging-port=" + remoteDebuggingPort);
        }
        if (userDataDirectory != null) {
            command.add("--user-data-dir=" + userDataDirectory);
        }
        if (enableLogging) {
            command.add("--enable-logging");
        }
        if (additionalParams != null && !additionalParams.isEmpty()) {
            command.addAll(additionalParams);
        }

        return command.toArray(new String[0]);
    }
}
