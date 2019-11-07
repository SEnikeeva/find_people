package model;

import java.sql.Date;
import java.util.List;

public class Post {
    private Integer id;
    private Game game;
    private User author;
    private Integer requiredPlayers;
    private Date date;
    private Comment comment;
    private Chat chat;

    public Integer getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public User getAuthor() {
        return author;
    }

    public Integer getRequiredPlayers() {
        return requiredPlayers;
    }

    public Date getDate() {
        return date;
    }

    public Comment getComment() {
        return comment;
    }


    public Chat getChat() {
        return chat;
    }

    public Post(Integer id, Game game, User author, Integer requiredPlayers, Date date, Comment comment, Chat chat) {
        this.id = id;
        this.game = game;
        this.author = author;
        this.requiredPlayers = requiredPlayers;
        this.date = date;
        this.comment = comment;
        this.chat = chat;
    }
}
