package com.eriksandsten.chromedtp.response.target;

import java.util.List;

public class AttachToTargetResponse {
    private Long id;
    private Result result;

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
        private String sessionId;
        private List<GetTargetsResponse.TargetInfo> targetInfos;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public List<GetTargetsResponse.TargetInfo> getTargetInfos() {
            return targetInfos;
        }

        public void setTargetInfos(List<GetTargetsResponse.TargetInfo> targetInfos) {
            this.targetInfos = targetInfos;
        }
    }
}
