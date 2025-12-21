package com.eriksandsten.chromedtp.response.domain.runtime;

import java.util.List;

public class ObjectPreview {
    public String type;
    public String subtype;
    public String description;
    public Boolean overflow;
    public List<PropertyPreview> properties;
    public List<EntryPreview> entries;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOverflow() {
        return overflow;
    }

    public void setOverflow(Boolean overflow) {
        this.overflow = overflow;
    }

    public List<PropertyPreview> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyPreview> properties) {
        this.properties = properties;
    }

    public List<EntryPreview> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryPreview> entries) {
        this.entries = entries;
    }
}
