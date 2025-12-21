package com.eriksandsten.chromedtp.response.domain;

import com.eriksandsten.chromedtp.BrowserWindowState;

public class WindowBounds {
    private Integer left;
    private Integer top;
    private Integer width;
    private Integer height;
    private BrowserWindowState windowState;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public BrowserWindowState getWindowState() {
        return windowState;
    }

    public void setWindowState(BrowserWindowState windowState) {
        this.windowState = windowState;
    }
}
