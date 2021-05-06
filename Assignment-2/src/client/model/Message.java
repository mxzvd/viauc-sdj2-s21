package model;

import java.util.Date;

public class Message {

    private String message;
    private Date date;
    private String sender;
    private String senderIP;

    public Message(String message, String sender, String senderIP) {
        this.message = message;
        date = new Date();
        this.sender = sender;
        this.senderIP = senderIP;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getSenderIP() {
        return senderIP;
    }
}
