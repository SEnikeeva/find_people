package repository;


import helper.DbConnection;
import model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {

    @Override
    public int save(User model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        int id = 0;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO users(username, password, profile_picture) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        try {
            st.setString(i, model.getUsername());
            st.setString(++i, model.getPassword());
            st.setString(++i, model.getProfilePicture());
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
    public User findByID(int id) throws SQLException, IOException, ClassNotFoundException {

        for (User user : findAll()) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User findByName(String name) throws SQLException, IOException, ClassNotFoundException {

        for (User user : findAll()) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    public User validateUser(String username, String password) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement( "SELECT *  FROM" +
                " users WHERE username = ? AND  password = ?");
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("profile_picture"));
        }
        return null;
    }


    @Override
    public void delete(User model) {

    }

    @Override
    public List<User> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_picture")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return users;
    }

    @Override
    public void update(User user) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        if (!user.getUsername().equals("")){
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        }

        if (!user.getPassword().equals("")){
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        }

        if (user.getProfilePicture() != null){
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET profile_picture = ? WHERE id = ?");
            statement.setString(1, user.getProfilePicture());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        }
    }
}
