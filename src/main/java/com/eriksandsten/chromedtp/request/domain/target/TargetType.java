package com.eriksandsten.chromedtp.request.domain.target;

public enum TargetType {
    PAGE("page");

    private final String type;

    TargetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
