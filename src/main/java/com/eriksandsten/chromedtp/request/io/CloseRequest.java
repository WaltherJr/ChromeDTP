package com.eriksandsten.chromedtp.request.io;

import com.eriksandsten.chromedtp.request.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class CloseRequest extends BaseRequest {
    private final String streamHandle;

    public CloseRequest(String streamHandle) {
        this.streamHandle = streamHandle;
    }
    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
                "id", 1,
                // "sessionId", sessionId,
                "method", "IO.close",
                "params", Map.of(
                    "handle", streamHandle
                )
        ));
    }
}
