package com.eriksandsten.chromedtp.utils;

import com.eriksandsten.chromedtp.ChromeSession;
import com.eriksandsten.chromedtp.response.page.EnableResponse;

public record NewChromeSession(EnableResponse enableResponse, ChromeSession chromeSession) {}
