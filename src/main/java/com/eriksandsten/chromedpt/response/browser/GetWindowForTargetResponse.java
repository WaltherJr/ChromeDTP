package com.eriksandsten.chromedpt.response.browser;

import com.eriksandsten.chromedpt.response.domain.WindowBounds;

public class GetWindowForTargetResponse {
    public Long id;
    public Result result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        public Integer windowId;
        public WindowBounds bounds;

        public Integer getWindowId() {
            return windowId;
        }

        public void setWindowId(Integer windowId) {
            this.windowId = windowId;
        }

        public WindowBounds getBounds() {
            return bounds;
        }

        public void setBounds(WindowBounds bounds) {
            this.bounds = bounds;
        }
    }
}
