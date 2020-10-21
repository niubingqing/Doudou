package com.newpwr.workflow.data;

import com.newpwr.workflow.data.enums.WorkflowNodeTypeEnum;

import java.util.List;

/**
 * @author niubq
 * @date 2020/6/24 15:59
 * @description
 */
public class WorkflowNode {
    private String id;
    private WorkflowNodeTypeEnum category = WorkflowNodeTypeEnum.NORMAL_NODE;
    private Boolean rollback = false;
    private Boolean callback = true;
    private Boolean transactionalStart = false;
    private Boolean transactionalEnd = false;
    private List<WorkflowSubscriber> subscribers;
    private int cycleTimes = 0;

    public void addCycleTimes() {
        this.cycleTimes++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkflowNodeTypeEnum getCategory() {
        return category;
    }

    public void setCategory(WorkflowNodeTypeEnum category) {
        this.category = category;
    }

    public Boolean getRollback() {
        return rollback;
    }

    public void setRollback(Boolean rollback) {
        this.rollback = rollback;
    }

    public Boolean getCallback() {
        return callback;
    }

    public void setCallback(Boolean callback) {
        this.callback = callback;
    }

    public Boolean getTransactionalStart() {
        return transactionalStart;
    }

    public void setTransactionalStart(Boolean transactionalStart) {
        this.transactionalStart = transactionalStart;
    }

    public Boolean getTransactionalEnd() {
        return transactionalEnd;
    }

    public void setTransactionalEnd(Boolean transactionalEnd) {
        this.transactionalEnd = transactionalEnd;
    }

    public List<WorkflowSubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<WorkflowSubscriber> subscribers) {
        this.subscribers = subscribers;
    }

    public int getCycleTimes() {
        return cycleTimes;
    }

    public void setCycleTimes(int cycleTimes) {
        this.cycleTimes = cycleTimes;
    }
}
