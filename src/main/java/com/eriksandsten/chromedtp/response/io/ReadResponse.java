package com.eriksandsten.chromedtp.response.io;

public class ReadResponse {
    private Long id;
    private Result result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        private Boolean base64Encoded;
        private String data;
        private Boolean eof;

        public Boolean getBase64Encoded() {
            return base64Encoded;
        }

        public void setBase64Encoded(Boolean base64Encoded) {
            this.base64Encoded = base64Encoded;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Boolean getEof() {
            return eof;
        }

        public void setEof(Boolean eof) {
            this.eof = eof;
        }
    }
}
