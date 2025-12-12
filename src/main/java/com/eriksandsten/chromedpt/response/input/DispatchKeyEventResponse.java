package com.eriksandsten.chromedpt.response.input;

public class DispatchKeyEventResponse {
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
    }
}
