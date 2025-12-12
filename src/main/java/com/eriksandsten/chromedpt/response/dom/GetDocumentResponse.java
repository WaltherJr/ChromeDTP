package com.eriksandsten.chromedpt.response.dom;

import java.util.List;

public class GetDocumentResponse {
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
        private Long nodeId;
        private Root root;

        public Long getNodeId() {
            return nodeId;
        }

        public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

        public Root getRoot() {
            return root;
        }

        public void setRoot(Root root) {
            this.root = root;
        }
    }

    public static class Root {
        private Long nodeId;
        private Long backendNodeId;
        private Integer nodeType;
        private String nodeName;
        private String localName;
        private String nodeValue;
        private Integer childNodeCount;
        private List<NodeChild> children;
        private String documentURL;
        private String baseURL;
        private String xmlVersion;
        private String compatibilityMode;
        private Boolean isScrollable;

        public Long getNodeId() {
            return nodeId;
        }

        public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

        public Long getBackendNodeId() {
            return backendNodeId;
        }

        public void setBackendNodeId(Long backendNodeId) {
            this.backendNodeId = backendNodeId;
        }

        public Integer getNodeType() {
            return nodeType;
        }

        public void setNodeType(Integer nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

        public String getNodeValue() {
            return nodeValue;
        }

        public void setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
        }

        public Integer getChildNodeCount() {
            return childNodeCount;
        }

        public void setChildNodeCount(Integer childNodeCount) {
            this.childNodeCount = childNodeCount;
        }

        public List<NodeChild> getChildren() {
            return children;
        }

        public void setChildren(List<NodeChild> children) {
            this.children = children;
        }

        public String getDocumentURL() {
            return documentURL;
        }

        public void setDocumentURL(String documentURL) {
            this.documentURL = documentURL;
        }

        public String getBaseURL() {
            return baseURL;
        }

        public void setBaseURL(String baseURL) {
            this.baseURL = baseURL;
        }

        public String getXmlVersion() {
            return xmlVersion;
        }

        public void setXmlVersion(String xmlVersion) {
            this.xmlVersion = xmlVersion;
        }

        public String getCompatibilityMode() {
            return compatibilityMode;
        }

        public void setCompatibilityMode(String compatibilityMode) {
            this.compatibilityMode = compatibilityMode;
        }

        public Boolean getScrollable() {
            return isScrollable;
        }

        public void setScrollable(Boolean scrollable) {
            isScrollable = scrollable;
        }
    }

    public static class NodeChild {
        private Long nodeId;
        private Long parentId;
        private Long backendNodeId;
        private Integer nodeType;
        private String nodeName;
        private String localName;
        private String nodeValue;
        private String publicId;
        private String systemId;
        private Integer childNodeCount;
        private List<NodeChild> children;
        private List<String> attributes;
        private List<ShadowRoot> shadowRoots;
        private String frameId;
        private Boolean isScrollable;
        private Boolean isSVG;
        private TemplateContent templateContent;
        private List<PseudoElement> pseudoElements;
        private ContentDocument contentDocument;

        public Long getNodeId() {
            return nodeId;
        }

        public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public Long getBackendNodeId() {
            return backendNodeId;
        }

        public void setBackendNodeId(Long backendNodeId) {
            this.backendNodeId = backendNodeId;
        }

        public Integer getNodeType() {
            return nodeType;
        }

        public void setNodeType(Integer nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

        public String getNodeValue() {
            return nodeValue;
        }

        public void setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
        }

        public String getPublicId() {
            return publicId;
        }

        public void setPublicId(String publicId) {
            this.publicId = publicId;
        }

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        public Integer getChildNodeCount() {
            return childNodeCount;
        }

        public void setChildNodeCount(Integer childNodeCount) {
            this.childNodeCount = childNodeCount;
        }

        public List<NodeChild> getChildren() {
            return children;
        }

        public void setChildren(List<NodeChild> children) {
            this.children = children;
        }

        public List<String> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<String> attributes) {
            this.attributes = attributes;
        }

        public List<ShadowRoot> getShadowRoots() {
            return shadowRoots;
        }

        public void setShadowRoots(List<ShadowRoot> shadowRoots) {
            this.shadowRoots = shadowRoots;
        }

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public Boolean getScrollable() {
            return isScrollable;
        }

        public void setScrollable(Boolean scrollable) {
            isScrollable = scrollable;
        }

        public Boolean getSVG() {
            return isSVG;
        }

        public void setSVG(Boolean SVG) {
            isSVG = SVG;
        }

        public TemplateContent getTemplateContent() {
            return templateContent;
        }

        public void setTemplateContent(TemplateContent templateContent) {
            this.templateContent = templateContent;
        }

        public List<PseudoElement> getPseudoElements() {
            return pseudoElements;
        }

        public void setPseudoElements(List<PseudoElement> pseudoElements) {
            this.pseudoElements = pseudoElements;
        }

        public ContentDocument getContentDocument() {
            return contentDocument;
        }

        public void setContentDocument(ContentDocument contentDocument) {
            this.contentDocument = contentDocument;
        }
    }

    public static class ContentDocument {
        private String documentURL;
        private String baseURL;
        private String xmlVersion;
        private String compatibilityMode;
        private Long nodeId;
        private Long parentId;
        private Long backendNodeId;
        private Integer nodeType;
        private String nodeName;
        private String localName;
        private String nodeValue;
        private String publicId;
        private String systemId;
        private Integer childNodeCount;
        private List<NodeChild> children;

        public String getDocumentURL() {
            return documentURL;
        }

        public void setDocumentURL(String documentURL) {
            this.documentURL = documentURL;
        }

        public String getBaseURL() {
            return baseURL;
        }

        public void setBaseURL(String baseURL) {
            this.baseURL = baseURL;
        }

        public String getXmlVersion() {
            return xmlVersion;
        }

        public void setXmlVersion(String xmlVersion) {
            this.xmlVersion = xmlVersion;
        }

        public String getCompatibilityMode() {
            return compatibilityMode;
        }

        public void setCompatibilityMode(String compatibilityMode) {
            this.compatibilityMode = compatibilityMode;
        }

        public Long getNodeId() {
            return nodeId;
        }

        public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public Long getBackendNodeId() {
            return backendNodeId;
        }

        public void setBackendNodeId(Long backendNodeId) {
            this.backendNodeId = backendNodeId;
        }

        public Integer getNodeType() {
            return nodeType;
        }

        public void setNodeType(Integer nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

        public String getNodeValue() {
            return nodeValue;
        }

        public void setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
        }

        public String getPublicId() {
            return publicId;
        }

        public void setPublicId(String publicId) {
            this.publicId = publicId;
        }

        public String getSystemId() {
            return systemId;
        }

        public void setSystemId(String systemId) {
            this.systemId = systemId;
        }

        public Integer getChildNodeCount() {
            return childNodeCount;
        }

        public void setChildNodeCount(Integer childNodeCount) {
            this.childNodeCount = childNodeCount;
        }

        public List<NodeChild> getChildren() {
            return children;
        }

        public void setChildren(List<NodeChild> children) {
            this.children = children;
        }
    }

    public static class TemplateContent {
        private String frameId;
        private Long nodeId;
        private Long parentId;
        private String backendNodeId;
        private String nodeType;
        private String nodeName;
        private String localName;
        private String nodeValue;
        private Integer childNodeCount;

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public Long getNodeId() {
            return nodeId;
        }

        public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public String getBackendNodeId() {
            return backendNodeId;
        }

        public void setBackendNodeId(String backendNodeId) {
            this.backendNodeId = backendNodeId;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

        public String getNodeValue() {
            return nodeValue;
        }

        public void setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
        }

        public Integer getChildNodeCount() {
            return childNodeCount;
        }

        public void setChildNodeCount(Integer childNodeCount) {
            this.childNodeCount = childNodeCount;
        }
    }

    public static class ShadowRoot extends NodeChild {
        private String shadowRootType;

        public String getShadowRootType() {
            return shadowRootType;
        }

        public void setShadowRootType(String shadowRootType) {
            this.shadowRootType = shadowRootType;
        }
    }

    public static class PseudoElement {
        private String selector;
        private String frameId;
        private Long nodeId;
        private Long parentId;
        private String backendNodeId;
        private String nodeType;
        private String nodeName;
        private String localName;
        private String nodeValue;
        private Integer childNodeCount;
        private List<String> attributes;
        private PseudoType pseudoType;

        public String getSelector() {
            return selector;
        }

        public void setSelector(String selector) {
            this.selector = selector;
        }

        public String getFrameId() {
            return frameId;
        }

        public void setFrameId(String frameId) {
            this.frameId = frameId;
        }

        public Long getNodeId() {
            return nodeId;
        }

        public void setNodeId(Long nodeId) {
            this.nodeId = nodeId;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        public String getBackendNodeId() {
            return backendNodeId;
        }

        public void setBackendNodeId(String backendNodeId) {
            this.backendNodeId = backendNodeId;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public String getLocalName() {
            return localName;
        }

        public void setLocalName(String localName) {
            this.localName = localName;
        }

        public String getNodeValue() {
            return nodeValue;
        }

        public void setNodeValue(String nodeValue) {
            this.nodeValue = nodeValue;
        }

        public Integer getChildNodeCount() {
            return childNodeCount;
        }

        public void setChildNodeCount(Integer childNodeCount) {
            this.childNodeCount = childNodeCount;
        }

        public List<String> getAttributes() {
            return attributes;
        }

        public void setAttributes(List<String> attributes) {
            this.attributes = attributes;
        }

        public PseudoType getPseudoType() {
            return pseudoType;
        }

        public void setPseudoType(PseudoType pseudoType) {
            this.pseudoType = pseudoType;
        }
    }
    public static class PseudoType {
        private String before;

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public PseudoType(String before) {
            this.before = before;
        }
    }
}
