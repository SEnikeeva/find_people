package model;

import java.sql.Date;


public class Message {

    private Integer id;
    private User sender;
    private String text;
    private Date date;

    public Message(Integer id, User sender_id, String text, Date timesent) {
        this.id = id;
        this.sender = sender_id;
        this.text = text;
        this.date = timesent;
    }

    public Integer getId() {
        return id;
    }

    public User getSender() {
        return sender;
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
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}