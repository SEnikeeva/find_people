package model;

import java.util.Date;

public class Comment {
    private Integer id;
    private Post post;
    private String text;
    private Date date;
    private User author;

    public Comment(Integer id, Post post, String text, Date date, User author) {
        this.id = id;
        this.post = post;
        this.text = text;
        this.date = date;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getAuthor() {
        return author;
    }
}
