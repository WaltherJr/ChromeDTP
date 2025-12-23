package com.eriksandsten.chromedtp.request.io;

import com.eriksandsten.chromedtp.request.BaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public class ReadRequest extends BaseRequest {
    private final String streamHandle;
    private final Integer offset;
    private final Integer size;

    public ReadRequest(String streamHandle, Integer offset, Integer size) {
        this.streamHandle = streamHandle;
        this.offset = offset;
        this.size = size;
    }

    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
                "id", 1,
                // "sessionId", sessionId,
                "method", "IO.read",
                "params", Map.of(
                        "handle", streamHandle,
                        "offset", offset,
                        "size", size
                )
        ));
    }
}
