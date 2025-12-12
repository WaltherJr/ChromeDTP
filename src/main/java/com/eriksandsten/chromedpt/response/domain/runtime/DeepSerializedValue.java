package com.eriksandsten.chromedpt.response.domain.runtime;

public class DeepSerializedValue {
    public String type;
    public Object value;
    public String objectId;
    public Integer weakLocalObjectReference;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getWeakLocalObjectReference() {
        return weakLocalObjectReference;
    }

    public void setWeakLocalObjectReference(Integer weakLocalObjectReference) {
        this.weakLocalObjectReference = weakLocalObjectReference;
    }
}
