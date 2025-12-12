package com.eriksandsten.chromedpt.response.domain.runtime;

public class PropertyPreview {
    public String name;
    public String type;
    public String value;
    public ObjectPreview valuePreview;
    public String subtype;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ObjectPreview getValuePreview() {
        return valuePreview;
    }

    public void setValuePreview(ObjectPreview valuePreview) {
        this.valuePreview = valuePreview;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
}
