package com.eriksandsten.chromedpt.request.browser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eriksandsten.chromedpt.request.BaseRequest;
import com.eriksandsten.chromedpt.request.domain.WindowBounds;

import java.util.Map;

public class SetWindowBoundsRequest extends BaseRequest {
    private final Integer windowId;
    private final WindowBounds bounds;
    private final Boolean flatten;

    public SetWindowBoundsRequest(Integer windowId, WindowBounds bounds, Boolean flatten) {
        this.windowId = windowId;
        this.bounds = bounds;
        this.flatten = flatten;
    }

    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
            "id", 1,
            "method", "Browser.setWindowBounds",
            "params", Map.of(
                    "windowId", windowId,
                    "bounds", bounds.getJSON(),
                    "flatten", flatten
            )
        ));
    }
}
