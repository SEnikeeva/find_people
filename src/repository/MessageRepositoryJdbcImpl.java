package repository;

import helper.DbConnection;
import model.Message;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryJdbcImpl implements MessageRepository {



    @Override
    public int save(Message model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        int id = 0;
        try {
            PreparedStatement st = connection.prepareStatement
                    ("INSERT INTO message(sender, text, timesent, id) VALUES (?, ?, ?, DEFAULT) returning id");
            int i = 1;
            st.setInt(i, model.getSender().getId());
            st.setString(++i, model.getText());
            st.setDate(++i, new Date(System.currentTimeMillis()));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return id;
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
                        resultSet.getString("text"),
                        resultSet.getDate("timesent")));
            }
        } catch (SQLException e) {
           throw new IllegalArgumentException(e);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public void update(Message message) {
    }
}
