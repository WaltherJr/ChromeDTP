package com.eriksandsten.chromedpt.request.page;

import com.eriksandsten.chromedpt.request.BaseRequest;
import com.eriksandsten.chromedpt.request.domain.page.PageSize;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;

public class PrintToPDFRequest extends BaseRequest {
    private String sessionId;
    private static final Map<String, Object> defaultParams = new HashMap<>();
    private Margins margins;
    public PrintToPDFRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    static {
        // "sessionId", this.sessionId
        defaultParams.put("landscape", "false");
        defaultParams.put("displayHeaderFooter", "false");
        defaultParams.put("printBackground", "true");
        defaultParams.put("scale", "1");
        defaultParams.put("paperWidth", PageSize.A4.getWidth());
        defaultParams.put("paperHeight", PageSize.A4.getHeight());
        defaultParams.put("marginTop", 1);
        defaultParams.put("marginBottom", 1);
        defaultParams.put("marginLeft", 1);
        defaultParams.put("marginRight", 1);
        defaultParams.put("pageRanges", "");
        defaultParams.put("headerTemplate", "");
        defaultParams.put("footerTemplate", "");
        defaultParams.put("preferCSSPageSize", "true");
        defaultParams.put("transferMode", "ReturnAsBase64");
        defaultParams.put("generateTaggedPDF", false);
        defaultParams.put("generateDocumentOutline", false);
    }

    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(defaultParams);
    }

    public record Margins(Double top, Double right, Double bottom, Double left) {}

    public PrintToPDFRequest withMargins(Double top, Double right, Double bottom, Double left) {
        this.margins = new Margins(top, right, bottom, left);
        return this;
    }
}
