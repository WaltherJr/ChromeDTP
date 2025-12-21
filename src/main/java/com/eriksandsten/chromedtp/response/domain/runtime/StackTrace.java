package com.eriksandsten.chromedtp.response.domain.runtime;

import java.util.List;

public class StackTrace {
    public String description;
    public List<CallFrame> callFrames;
    public StackTrace parent;
    public StackTraceId parentId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CallFrame> getCallFrames() {
        return callFrames;
    }

    public void setCallFrames(List<CallFrame> callFrames) {
        this.callFrames = callFrames;
    }

    public StackTrace getParent() {
        return parent;
    }

    public void setParent(StackTrace parent) {
        this.parent = parent;
    }

    public StackTraceId getParentId() {
        return parentId;
    }

    public void setParentId(StackTraceId parentId) {
        this.parentId = parentId;
    }
}
