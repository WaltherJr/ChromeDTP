package com.eriksandsten.chromedpt.request.target;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eriksandsten.chromedpt.request.BaseRequest;

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
