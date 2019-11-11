package service;

import helper.DbConnection;
import model.Post;
import model.User;
import repository.PostRepositoryJdbcImpl;
import repository.UserRepositoryJdbcImpl;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PostService {
    public HashSet<User> getGamers(Post post) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        HashSet<User> gamers = new HashSet<>();
        try {
            PreparedStatement statement = connection.prepareStatement( "SELECT *  FROM" +
                    " post_user WHERE post = ?");
            statement.setInt(1, post.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                gamers.add(new UserRepositoryJdbcImpl().findByID(resultSet.getInt("user_id")));
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return gamers;
    }

    public void addGamer(int post, int gamer) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO post_user(post, user_id) VALUES (?, ?)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        st.setInt(i++, post);
        st.setInt(i, gamer);
        st.executeUpdate();
        st.close();
        Post post1 = new PostRepositoryJdbcImpl().findByID(post);
        post1.setRequiredPlayers(post1.getRequiredPlayers()- 1);
        new PostRepositoryJdbcImpl().update(post1);
    }

    public List<Post> findLike(String pattern) throws SQLException, IOException, ClassNotFoundException {
        List<Post> posts = new ArrayList<>();
        List<Post> postList = new PostRepositoryJdbcImpl().findAll();
        for (Post post : postList) {
            if (post.getGame().getName().contains(pattern))
                posts.add(post);
        }
        return posts;
    }
}
