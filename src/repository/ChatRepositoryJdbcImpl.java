package repository;

import helper.DbConnection;
import model.Chat;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatRepositoryJdbcImpl implements CrudRepository<Chat> {

    @Override
    public void save(Chat model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO chat(id) VALUES (DEFAULT)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public Chat findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        for (Chat chat : findAll()) {
            if (chat.getId() == id)
                return chat;
        }
        return null;
    }

    @Override
    public void delete(Chat model) {

    }

    @Override
    public List<Chat> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<Chat> chats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM chat ");
            while (resultSet.next()) {
                chats.add(new Chat(resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return chats;
    }

    @Override
    public void update() {

    }
}
