package com.eriksandsten.chromedpt;

record ChromeConnection(ChromeDTPWebSocketClient webSocketClient, BrowserTarget browserTarget) {}
