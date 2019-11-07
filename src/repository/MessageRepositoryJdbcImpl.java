package repository;

import helper.DbConnection;
import model.Message;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryJdbcImpl implements MessageRepository {



    @Override
    public void save(Message model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO message(sender , reciever, text, timesent) VALUES (?, ?, ?)");
            int i = 1;
            st.setInt(i, model.getSender().getId());
            st.setInt(++i, model.getReceiver().getId());
            st.setString(++i, model.getText());
            st.setDate(++i, new Date(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public Message findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        for (Message message : findAll()) {
            if (message.getId() == id) {
                return message;
            }
        }
        return null;
    }

    @Override
    public void delete(Message model) {

    }

    @Override
    public List<Message> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<Message> messages = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = null;
            resultSet = statement.executeQuery("SELECT * FROM message ");
            while (resultSet.next()) {
                messages.add(new Message(Integer.parseInt(resultSet.getString("id")),
                        new UserRepositoryJdbcImpl().findByID(resultSet.getInt("sender")),
                        new UserRepositoryJdbcImpl().findByID(resultSet.getInt("receiver")),
                        resultSet.getString("text"),
                        resultSet.getDate("date")));
            }
        } catch (SQLException e) {
           throw new IllegalArgumentException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public void update() {
    }
}
