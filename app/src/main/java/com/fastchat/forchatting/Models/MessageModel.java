package com.fastchat.forchatting.Models;

public class MessageModel {

    String uId, massage, massageID;
    Long timestamp;

    public MessageModel(String uId, String massage, Long timestamp) {
        this.uId = uId;
        this.massage = massage;
        this.timestamp = timestamp;
    }

    public MessageModel(String uId, String massage) {
        this.uId = uId;
        this.massage = massage;
    }

    public MessageModel() {}

    public String getMassageID() {
        return massageID;
    }

    public void setMassageID(String massageID) {
        this.massageID = massageID;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
