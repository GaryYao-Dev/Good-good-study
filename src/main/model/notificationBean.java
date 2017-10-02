package main.model;

import java.sql.Timestamp;

public class notificationBean {
    private String n_content;
    private Timestamp n_time;
    private boolean readornot;

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    public Timestamp getN_time() {
        return n_time;
    }

    public void setN_time(Timestamp n_time) {
        this.n_time = n_time;
    }

    public boolean isReadornot() {
        return readornot;
    }

    public void setReadornot(boolean readornot) {
        this.readornot = readornot;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    private String senderName;
    private String receiverName;


    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    private  int senderID;
    private int receiverID;


}
