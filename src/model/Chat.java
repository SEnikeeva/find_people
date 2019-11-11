package model;

import service.ChatService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Chat {
    private int id;


    public Chat(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private List<User> chatters;
    private List<Message> conversation;

    public List<User> getChatters() {
        return chatters;
    }

    public List<Message> getConversation() throws SQLException, IOException, ClassNotFoundException {
        return new ChatService().getConversation(this);
    }
}
