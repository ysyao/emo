package com.phl.emoproject.pojo;


import com.google.gson.annotations.SerializedName;

public class Message {
    private int returnCode;
    @SerializedName("value")
    private String value;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
