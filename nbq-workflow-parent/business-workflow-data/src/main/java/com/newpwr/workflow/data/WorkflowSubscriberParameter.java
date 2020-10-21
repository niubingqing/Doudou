package com.newpwr.workflow.data;

/**
 * @author niubq
 * @date 2020/6/28 10:00
 * @description
 */
public class WorkflowSubscriberParameter {
    private Long wfId;

    private String nodeId;

    private Long subId;

    private String paramName;

    private String functions;

    private String funcContent;

    private String baseFuncContent;

    private String jsonParam;

    private String jsonLogic;

    public Long getWfId() {
        return wfId;
    }

    public void setWfId(Long wfId) {
        this.wfId = wfId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getFuncContent() {
        return funcContent;
    }

    public void setFuncContent(String funcContent) {
        this.funcContent = funcContent;
    }

    public String getBaseFuncContent() {
        return baseFuncContent;
    }

    public void setBaseFuncContent(String baseFuncContent) {
        this.baseFuncContent = baseFuncContent;
    }

    public String getJsonParam() {
        return jsonParam;
    }

    public void setJsonParam(String jsonParam) {
        this.jsonParam = jsonParam;
    }

    public String getJsonLogic() {
        return jsonLogic;
    }

    public void setJsonLogic(String jsonLogic) {
        this.jsonLogic = jsonLogic;
    }
}
