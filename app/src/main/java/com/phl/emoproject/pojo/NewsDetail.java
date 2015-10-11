package com.phl.emoproject.pojo;


import com.google.gson.annotations.SerializedName;

public class NewsDetail {
    public static class NewsInfo {
        private String id;
        private String title;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    public static class FileInfo {
        private String fileName;
        private String fileUrl;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
    @SerializedName("jsonObject")
    private NewsInfo jsonObject;
    @SerializedName("files")
    private FileInfo[] files;
    @SerializedName("message")
    private Message message;

    public NewsInfo getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(NewsInfo jsonObject) {
        this.jsonObject = jsonObject;
    }

    public FileInfo[] getFiles() {
        return files;
    }

    public void setFiles(FileInfo[] files) {
        this.files = files;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
