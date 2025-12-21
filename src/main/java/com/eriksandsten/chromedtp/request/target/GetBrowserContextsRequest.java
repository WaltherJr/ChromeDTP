package com.eriksandsten.chromedtp.request.target;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eriksandsten.chromedtp.request.BaseRequest;

import java.util.Map;

public class GetBrowserContextsRequest extends BaseRequest {
    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
            "id", 1,
            "method", "Target.getBrowserContexts"
        ));
    }
}
