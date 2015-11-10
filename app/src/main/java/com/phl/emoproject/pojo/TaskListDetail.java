package com.phl.emoproject.pojo;

import java.util.List;

public class TaskListDetail {
    public static class Control {
        private String id;
        private String labelText;
        private String isHidden;
        private String controlType;
        private String value;
        private String dataType;

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabelText() {
            return labelText;
        }

        public void setLabelText(String labelText) {
            this.labelText = labelText;
        }

        public String getIsHidden() {
            return isHidden;
        }

        public void setIsHidden(String isHidden) {
            this.isHidden = isHidden;
        }

        public String getControlType() {
            return controlType;
        }

        public void setControlType(String controlType) {
            this.controlType = controlType;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class HistoryNodes {
        private  String id;
        private String finishedTime;
        private String title;
        private String actualUserName;
        private String idea;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFinishedTime() {
            return finishedTime;
        }

        public void setFinishedTime(String finishedTime) {
            this.finishedTime = finishedTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActualUserName() {
            return actualUserName;
        }

        public void setActualUserName(String actualUserName) {
            this.actualUserName = actualUserName;
        }

        public String getIdea() {
            return idea;
        }

        public void setIdea(String idea) {
            this.idea = idea;
        }
    }

    public static class TaskFile {
        private String id;
        private String name;
        private String fileUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
    private List<Control> controls;
    private List<HistoryNodes> histotyNodes;
    private List<TaskFile> files;
    private Message message;
    private List<Control> ideaControls;

    public List<Control> getIdeaControls() {
        return ideaControls;
    }

    public void setIdeaControls(List<Control> ideaControls) {
        this.ideaControls = ideaControls;
    }

    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }

    public List<HistoryNodes> getHistotyNodes() {
        return histotyNodes;
    }

    public void setHistotyNodes(List<HistoryNodes> histotyNodes) {
        this.histotyNodes = histotyNodes;
    }

    public List<TaskFile> getFiles() {
        return files;
    }

    public void setFiles(List<TaskFile> files) {
        this.files = files;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
