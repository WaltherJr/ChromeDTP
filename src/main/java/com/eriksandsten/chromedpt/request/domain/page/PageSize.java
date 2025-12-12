package com.eriksandsten.chromedpt.request.domain.page;

public enum PageSize {
    A4(8.27, 11.69), A5(5.83, 8.27);

    private final Double width;
    private final Double height;

    PageSize(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }
}
