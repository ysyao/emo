package com.phl.emoproject.pojo;


import java.util.List;

public class ListGenericClass<T> {
    private List<T> jsonList;
    private Message message;

    public List<T> getJsonList() {
        return jsonList;
    }

    public void setJsonList(List<T> jsonList) {
        this.jsonList = jsonList;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
