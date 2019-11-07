package model;

import java.sql.Date;


public class Message {

    private Integer id;
    private User sender, receiver;
    private String text;
    private Date date;

    public Message(Integer id, User sender_id, User receiver_id, String text, Date timesent) {
        this.id = id;
        this.sender = sender_id;
        this.receiver = receiver_id;
        this.text = text;
        this.date = timesent;
    }

    public Integer getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", receiver=" + receiver +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}