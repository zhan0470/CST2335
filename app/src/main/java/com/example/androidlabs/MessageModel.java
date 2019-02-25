package com.example.androidlabs;

class MessageModel {
    public String message;
    public boolean isSent;
    public long messageId;

    public MessageModel() {
        this(null, false);
    }
    public MessageModel(String message, boolean isSent) {
        this.message = message;
        this.isSent = isSent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        this.isSent = sent;
    }

    public long getMessageId() { return messageId; }
    public void setMessageId(long id) { messageId = id; }
}
