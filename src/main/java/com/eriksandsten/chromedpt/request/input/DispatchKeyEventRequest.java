package com.eriksandsten.chromedpt.request.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eriksandsten.chromedpt.request.BaseRequest;

import java.util.Map;

public class DispatchKeyEventRequest extends BaseRequest {
    private String sessionId;
    public String type;
    public String key;

    public DispatchKeyEventRequest(String sessionId, String type, String key) {
        this.sessionId = sessionId;
        this.type = type;
        this.key = key;
    }

    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
                "id", 1,
                "sessionId", sessionId,
                "method", "Input.dispatchKeyEvent",
                "params", Map.of(
                    "type", type,
                    "key", key
                )
        ));
    }
}
