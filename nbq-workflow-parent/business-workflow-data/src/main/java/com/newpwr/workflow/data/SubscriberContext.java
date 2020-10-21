package com.newpwr.workflow.data;

import com.newpwr.workflow.data.Local.LocalLanguage;

import java.io.Serializable;

/**
 * @author niubq
 * @date 2020/6/28 10:26
 * @description
 */
public class SubscriberContext implements Serializable {
    /**
     * 消息数据
     */
    private SubscirberData subscirberData;
    /**
     * 工作流实例Id
     */
    private Long workflowInstanceId;
    /**
     * 消息Id
     */
    private Long messageId;
    /**
     * 全局事务Id
     */
    private String xid;
    /**
     * 语言
     */
    private LocalLanguage localLanguage;
    /**
     * 是否处于事务中
     */
    private Boolean inDTC;
    /**
     * 订阅者参数
     */
    private String subCode;

    /**
     * 节点Id
     */
    private String eventId;

    private String errorMessage;

    private String errorCode;

    private Boolean callBack;

    public SubscirberData getSubscirberData() {
        return subscirberData;
    }

    public void setSubscirberData(SubscirberData subscirberData) {
        this.subscirberData = subscirberData;
    }

    public Long getWorkflowInstanceId() {
        return workflowInstanceId;
    }

    public void setWorkflowInstanceId(Long workflowInstanceId) {
        this.workflowInstanceId = workflowInstanceId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public LocalLanguage getLocalLanguage() {
        return localLanguage;
    }

    public void setLocalLanguage(LocalLanguage localLanguage) {
        this.localLanguage = localLanguage;
    }

    public Boolean getInDTC() {
        return inDTC;
    }

    public void setInDTC(Boolean inDTC) {
        this.inDTC = inDTC;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Boolean getCallBack() {
        return callBack;
    }

    public void setCallBack(Boolean callBack) {
        this.callBack = callBack;
    }
}
