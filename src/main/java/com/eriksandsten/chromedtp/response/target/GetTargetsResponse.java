package com.eriksandsten.chromedtp.response.target;

import java.util.List;

public class GetTargetsResponse {
    private Long id;
    private Result result;

    public static class Result {
        private List<TargetInfo> targetInfos;

        public List<TargetInfo> getTargetInfos() {
            return targetInfos;
        }

        public void setTargetInfos(List<TargetInfo> targetInfos) {
            this.targetInfos = targetInfos;
        }
    }

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

    public static class TargetInfo {
        private String targetId;
        private String type;
        private String title;
        private String url;
        private Boolean attached;
        private String openerId;
        private Boolean canAccessOpener;
        private String openerFrameId;
        private String browserContextId;
        private String subtype;

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Boolean getAttached() {
            return attached;
        }

        public void setAttached(Boolean attached) {
            this.attached = attached;
        }

        public String getOpenerId() {
            return openerId;
        }

        public void setOpenerId(String openerId) {
            this.openerId = openerId;
        }

        public Boolean getCanAccessOpener() {
            return canAccessOpener;
        }

        public void setCanAccessOpener(Boolean canAccessOpener) {
            this.canAccessOpener = canAccessOpener;
        }

        public String getOpenerFrameId() {
            return openerFrameId;
        }

        public void setOpenerFrameId(String openerFrameId) {
            this.openerFrameId = openerFrameId;
        }

        public String getBrowserContextId() {
            return browserContextId;
        }

        public void setBrowserContextId(String browserContextId) {
            this.browserContextId = browserContextId;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }
    }
}
