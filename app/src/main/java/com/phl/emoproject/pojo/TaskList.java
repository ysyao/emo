package com.phl.emoproject.pojo;


import java.io.Serializable;

public class TaskList implements Serializable{
    private String taskId;
    private String taskCode;
    private String category;
    private String url;
    private String receiverId;
    private String senderName;
    private String title;
    private String createDate;
    private String nodeId;
    private String historyNodeId;
    private String discussid;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getHistoryNodeId() {
        return historyNodeId;
    }

    public void setHistoryNodeId(String historyNodeId) {
        this.historyNodeId = historyNodeId;
    }

    public String getDiscussid() {
        return discussid;
    }

    public void setDiscussid(String discussid) {
        this.discussid = discussid;
    }
}
