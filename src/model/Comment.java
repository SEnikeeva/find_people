package model;

import java.util.Date;

public class Comment {
    private Integer id;
    private String text;
    private Date date;


    public Comment(Integer id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

}
