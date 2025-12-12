package com.eriksandsten.chromedpt.response.page;

import com.eriksandsten.chromedpt.response.domain.io.StreamHandle;

public class PrintToPDFResponse {
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
        private String data;
        private StreamHandle stream;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public StreamHandle getStream() {
            return stream;
        }

        public void setStream(StreamHandle stream) {
            this.stream = stream;
        }
    }
}
