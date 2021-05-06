package common.network;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private String message;
    private Date date;
    private String sender;
    private String senderIP;

    public Message(String message, Date date, String sender, String senderIP) {
        this.message = message;
        this.date = date;
        this.sender = sender;
        this.senderIP = senderIP;
    }

    public Message(String message, String sender, String senderIP) {
        this(message, new Date(), sender, senderIP);
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
