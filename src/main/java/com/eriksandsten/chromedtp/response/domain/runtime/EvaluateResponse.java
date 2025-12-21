package com.eriksandsten.chromedtp.response.domain.runtime;

public class EvaluateResponse {
    private Long id;
    private Result result;
    private String sessionId;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static class Result {
        private String type;
        private String className;
        private EvalResult result;
        private ExceptionDetails exceptionDetails;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public EvalResult getResult() {
            return result;
        }

        public void setResult(EvalResult result) {
            this.result = result;
        }

        public ExceptionDetails getExceptionDetails() {
            return exceptionDetails;
        }

        public void setExceptionDetails(ExceptionDetails exceptionDetails) {
            this.exceptionDetails = exceptionDetails;
        }
    }

    public static class EvalResult {
        public String type;
        public String subtype;
        public String className;
        public Object value;
        public String unserializableValue;
        public String description;
        public DeepSerializedValue deepSerializedValue;
        public String objectId;
        public ObjectPreview preview;
        public CustomPreview customPreview;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getUnserializableValue() {
            return unserializableValue;
        }

        public void setUnserializableValue(String unserializableValue) {
            this.unserializableValue = unserializableValue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public DeepSerializedValue getDeepSerializedValue() {
            return deepSerializedValue;
        }

        public void setDeepSerializedValue(DeepSerializedValue deepSerializedValue) {
            this.deepSerializedValue = deepSerializedValue;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public ObjectPreview getPreview() {
            return preview;
        }

        public void setPreview(ObjectPreview preview) {
            this.preview = preview;
        }

        public CustomPreview getCustomPreview() {
            return customPreview;
        }

        public void setCustomPreview(CustomPreview customPreview) {
            this.customPreview = customPreview;
        }
    }

    public static class ExceptionDetails {
        public Integer exceptionId;
        public String text;
        public Integer lineNumber;
        public Integer columnNumber;
        public String scriptId;
        public String url;
        public StackTrace stackTrace;
        public EvalResult exception;
        public Integer executionContextId;
        public Object exceptionMetaData;

        public Integer getExceptionId() {
            return exceptionId;
        }

        public void setExceptionId(Integer exceptionId) {
            this.exceptionId = exceptionId;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(Integer lineNumber) {
            this.lineNumber = lineNumber;
        }

        public Integer getColumnNumber() {
            return columnNumber;
        }

        public void setColumnNumber(Integer columnNumber) {
            this.columnNumber = columnNumber;
        }

        public String getScriptId() {
            return scriptId;
        }

        public void setScriptId(String scriptId) {
            this.scriptId = scriptId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public StackTrace getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(StackTrace stackTrace) {
            this.stackTrace = stackTrace;
        }

        public EvalResult getException() {
            return exception;
        }

        public void setException(EvalResult exception) {
            this.exception = exception;
        }

        public Integer getExecutionContextId() {
            return executionContextId;
        }

        public void setExecutionContextId(Integer executionContextId) {
            this.executionContextId = executionContextId;
        }

        public Object getExceptionMetaData() {
            return exceptionMetaData;
        }

        public void setExceptionMetaData(Object exceptionMetaData) {
            this.exceptionMetaData = exceptionMetaData;
        }
    }
}
