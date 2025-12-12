package com.eriksandsten.chromedpt.response.domain.runtime;

public class StackTraceId {
    public String id;
    public String debuggerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDebuggerId() {
        return debuggerId;
    }

    public void setDebuggerId(String debuggerId) {
        this.debuggerId = debuggerId;
    }
}
