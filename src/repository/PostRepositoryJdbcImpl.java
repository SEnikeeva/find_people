package repository;

import helper.DbConnection;
import model.Game;
import model.Post;
import model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryJdbcImpl implements CrudRepository<Post> {
    @Override
    public void save(Post model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO post(game, author, requiredplayers, date, comments, chat) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        try {
            st.setInt(i, model.getGame().getId());
            st.setInt(++i, model.getAuthor().getId());
            st.setInt(++i, model.getRequiredPlayers());
            st.setDate(++i, model.getDate());
            st.setInt(++i, model.getComment().getId());
            st.setInt(++i, model.getChat().getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Post findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        for (Post post : findAll()) {
            if (post.getId() == id)
                return post;
        }
        return null;
    }

    @Override
    public void delete(Post model) {

    }

    @Override
    public List<Post> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<Post> posts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM post ");
            while (resultSet.next()) {
                posts.add(new Post(resultSet.getInt("id"),
                        new GameRepositoryJdbcImpl().findByID(resultSet.getInt("game")),
                        new UserRepositoryJdbcImpl().findByID(resultSet.getInt("author")),
                        resultSet.getInt("requiredplayers"),
                        resultSet.getDate("date"),
                        new CommentRepositoryJdbcImpl().findByID(resultSet.getInt("comments")),
                        new ChatRepositoryJdbcImpl().findByID(resultSet.getInt("chat"))));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return posts;
    }

    @Override
    public void update() {

    }
}
