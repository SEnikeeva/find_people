package service;

import helper.DbConnection;
import model.Chat;
import model.Message;
import repository.MessageRepositoryJdbcImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    public List<Message> getConversation(Chat chat) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<Message> conversation = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement( "SELECT *  FROM" +
                    " chat_message WHERE chat = ?");
            statement.setInt(1, chat.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                conversation.add(new MessageRepositoryJdbcImpl().findByID(resultSet.getInt("message")));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return conversation;
    }

    public void addMessage(int chat_id, int message_id) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO chat_message(chat, message) VALUES (?, ?)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        st.setInt(i++, chat_id);
        st.setInt(i, message_id);
        st.executeUpdate();
        st.close();
    }

}
