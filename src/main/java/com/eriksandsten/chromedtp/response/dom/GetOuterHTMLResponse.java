package com.eriksandsten.chromedtp.response.dom;

public class GetOuterHTMLResponse {
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
        private String outerHTML;

        public String getOuterHTML() {
            return outerHTML;
        }

        public void setOuterHTML(String outerHTML) {
            this.outerHTML = outerHTML;
        }
    }
}
