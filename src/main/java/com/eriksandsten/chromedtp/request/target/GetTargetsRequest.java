package com.eriksandsten.chromedtp.request.target;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eriksandsten.chromedtp.request.BaseRequest;

import java.util.Map;

// TODO: add "filter" parameter
public class GetTargetsRequest extends BaseRequest {
    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
            "id", 1,
            "method", "Target.getTargets"
        ));
    }
}
