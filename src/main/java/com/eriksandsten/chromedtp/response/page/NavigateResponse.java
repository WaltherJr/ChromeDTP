package com.eriksandsten.chromedtp.response.page;

public class NavigateResponse {
    private Long id;
    private Result result;
    private String sessionId;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static class Result {
        private String frameId;
        private String loaderId;
        private String errorText;

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public String getLoaderId() {
            return loaderId;
        }

        public void setLoaderId(String loaderId) {
            this.loaderId = loaderId;
        }

        public String getErrorText() {
            return errorText;
        }

        public void setErrorText(String errorText) {
            this.errorText = errorText;
        }
    }
}
