package com.eriksandsten.chromedtp.utils;

import java.util.List;

public class WHelper {
    /*
    static {
        System.loadLibrary("wHelper");
    }
    */

    public native void setActiveWindow(long windowHandle);
    public native void setActiveWindow(String windowClassName);
    public native List<Long> getWindowHandlesByWindowClassName(String className);
}
