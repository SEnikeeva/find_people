package service;

import helper.DbConnection;
import model.Post;
import model.User;
import repository.UserRepositoryJdbcImpl;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostService {
    public List<User> getGamers(Post post) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<User> gamers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement( "SELECT *  FROM" +
                    " post_user WHERE post = ?");
            statement.setInt(1, post.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                gamers.add(new UserRepositoryJdbcImpl().findByID(resultSet.getInt("user")));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return gamers;
    }
}
