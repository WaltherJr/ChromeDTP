package com.eriksandsten.chromedtp.request.page;

import com.eriksandsten.chromedtp.request.BaseRequest;
import com.eriksandsten.chromedtp.request.domain.page.PageSize;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.HashMap;
import java.util.Map;

public class PrintToPDFRequest extends BaseRequest {
    private String sessionId;
    private static final Map<String, Object> params = new HashMap<>();
    private Margins margins;
    public PrintToPDFRequest(String sessionId) {
        this.sessionId = sessionId;
    }

    static {
        // "sessionId", this.sessionId
        params.put("landscape", false);
        params.put("displayHeaderFooter", false);
        params.put("printBackground", true);
        params.put("scale", 1);
        params.put("paperWidth", PageSize.A4.getWidth());
        params.put("paperHeight", PageSize.A4.getHeight());
        params.put("marginTop", 0);
        params.put("marginBottom", 0);
        params.put("marginLeft", 0);
        params.put("marginRight", 0);
        params.put("pageRanges", "");
        params.put("headerTemplate", "");
        params.put("footerTemplate", "");
        params.put("preferCSSPageSize", true);
        params.put("transferMode", "ReturnAsStream");
        params.put("generateTaggedPDF", false);
        params.put("generateDocumentOutline", false);
    }

    @Override
    public String getJSON() throws JsonProcessingException {
        return objectMapper.writeValueAsString(Map.of(
            "id", 1,
            // "sessionId", sessionId,
            "method", "Page.printToPDF",
        "params", params
        ));
    }

    public record Margins(Double top, Double right, Double bottom, Double left) {}

    public PrintToPDFRequest withMargins(Double top, Double right, Double bottom, Double left) {
        this.margins = new Margins(top, right, bottom, left);
        return this;
    }
}
