package com.eriksandsten.chromedtp;

record ChromeConnection(ChromeDTPWebSocketClient webSocketClient, BrowserTarget browserTarget) {}
