package com.doudou.workflow.data;

import com.doudou.workflow.data.enums.WorkflowSubscriberTypeEnum;

import java.util.List;

/**
 * @author niubq
 * @date 2020/6/28 9:57
 * @description
 */
public class WorkflowSubscriber {
    /**
     * 工作流ID
     */
    private Long wfId;
    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 订阅者类型;0-微服务，1-外部API，2-数据库
     */
    private WorkflowSubscriberTypeEnum subType;

    private Integer subTypeValue;

    private String subName;

    private String subCode;
    /**
     * 来源ID;0-为微服务接口id,1、2对应资源池id
     */
    private Long sourceId;

    private List<WorkflowSubscriberParameter> workflowSubParameterList;

    private SqlResource sqlResource;
    private ThirdPartyApi thirdPartyApi;
    private MSApi msApi;

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

    public WorkflowSubscriberTypeEnum getSubType() {
        return subType;
    }

    public void setSubType(WorkflowSubscriberTypeEnum subType) {
        this.subType = subType;
    }

    public void setSubTypeValue(Integer subTypeValue) {
        this.subTypeValue = subTypeValue;
    }

    public Integer getSubTypeValue() {
        return this.subTypeValue;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public List<WorkflowSubscriberParameter> getWorkflowSubParameterList() {
        return workflowSubParameterList;
    }

    public void setWorkflowSubParameterList(List<WorkflowSubscriberParameter> workflowSubParameterList) {
        this.workflowSubParameterList = workflowSubParameterList;
    }

    public SqlResource getSqlResource() {
        return sqlResource;
    }

    public void setSqlResource(SqlResource sqlResource) {
        this.sqlResource = sqlResource;
    }

    public ThirdPartyApi getThirdPartyApi() {
        return thirdPartyApi;
    }

    public void setThirdPartyApi(ThirdPartyApi thirdPartyApi) {
        this.thirdPartyApi = thirdPartyApi;
    }

    public MSApi getMsApi() {
        return msApi;
    }

    public void setMsApi(MSApi msApi) {
        this.msApi = msApi;
    }

}
