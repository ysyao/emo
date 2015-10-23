package com.phl.emoproject.pojo;

public class JsonObjectRes<T> {
    private T jsonObject;
    private Message message;

    public T getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(T jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
