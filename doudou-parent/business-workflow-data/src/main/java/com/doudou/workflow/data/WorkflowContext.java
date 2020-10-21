package com.doudou.workflow.data;

import com.doudou.workflow.data.enums.WorkFlowStatusEnum;

/**
 * @author niubq
 * @date 2020/6/24 15:58
 * @description
 */
public class WorkflowContext {
    /**
     * 工作流配置id
     */
    private Long workflowId;
    /**
     * 工作流实例id
     */
    private Long workflowInstanceId;

    /**
     * 是否处于事务中
     */
    private Boolean inDTC = false;
    /**
     * 全局事务ID
     */
    private String xid;
    /**
     * 当前事件节点ID
     */
    private Long currentEventId;
    /**
     * 数据
     */
    private Object data;

    private WorkflowNode currentWorkflowNode;
    /**
     * 工作流状态
     */
    private WorkFlowStatusEnum workFlowStatusEnum;

    private String result;

    private String detailMessage;

    private String errorCode;

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Long getWorkflowInstanceId() {
        return workflowInstanceId;
    }

    public void setWorkflowInstanceId(Long workflowInstanceId) {
        this.workflowInstanceId = workflowInstanceId;
    }

    public Boolean getInDTC() {
        return inDTC;
    }

    public void setInDTC(Boolean inDTC) {
        this.inDTC = inDTC;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public Long getCurrentEventId() {
        return currentEventId;
    }

    public void setCurrentEventId(Long currentEventId) {
        this.currentEventId = currentEventId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public WorkflowNode getCurrentWorkflowNode() {
        return currentWorkflowNode;
    }

    public void setCurrentWorkflowNode(WorkflowNode currentWorkflowNode) {
        this.currentWorkflowNode = currentWorkflowNode;
    }

    public WorkFlowStatusEnum getWorkFlowStatusEnum() {
        return workFlowStatusEnum;
    }

    public void setWorkFlowStatusEnum(WorkFlowStatusEnum workFlowStatusEnum) {
        this.workFlowStatusEnum = workFlowStatusEnum;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
