package com.moin.letschat;

public class Message {

    private String text;
    private String name;
    private String photoUrl;
    private String uid;
    private long timestamp;

    public Message() {
    }

    public Message(String uid, String name, String text, String photoUrl, long timestamp) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.timestamp = timestamp;
        this.uid = uid;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUid() {
        return uid;
    }
}
