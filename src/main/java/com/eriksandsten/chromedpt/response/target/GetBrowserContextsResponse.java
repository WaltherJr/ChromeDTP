package com.eriksandsten.chromedpt.response.target;

import java.util.List;

public class GetBrowserContextsResponse {
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
        private List<String> browserContextIds;

        public List<String> getBrowserContextIds() {
            return browserContextIds;
        }

        public void setBrowserContextIds(List<String> browserContextIds) {
            this.browserContextIds = browserContextIds;
        }
    }
}
