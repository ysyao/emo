package com.phl.emoproject.pojo;


public class RejectResponse {
    public static class RejectMessage {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    private int result;
    private RejectMessage message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public RejectMessage getMessage() {
        return message;
    }

    public void setMessage(RejectMessage message) {
        this.message = message;
    }
}
