package model;

import java.util.List;

public class Chat {
    int id;

    public Chat(int id) {
    }

    public int getId() {
        return id;
    }

    List<User> chatters;
    List<Message> conversation;

    public List<User> getChatters() {
        return chatters;
    }

    public List<Message> getConversation() {
        return conversation;
    }
}
