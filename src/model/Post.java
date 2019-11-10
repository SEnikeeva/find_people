package model;

import service.PostService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private Integer id;
    private Game game;
    private User author;
    private Integer requiredPlayers;
    private Date date;
    private Comment comment;
    private Chat chat;
    private List<User> gamers;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setRequiredPlayers(Integer requiredPlayers) {
        this.requiredPlayers = requiredPlayers;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void setGamers(List<User> gamers) {
        this.gamers = gamers;
    }

    public List<User> getGamers() throws SQLException, IOException, ClassNotFoundException {
        return new PostService().getGamers(this);
    }

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
