package repository;

import helper.DbConnection;
import model.Chat;
import model.Game;
import model.Post;
import model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryJdbcImpl implements CrudRepository<Post>{
    //@Override
    public int save(Post model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        int chatId= new ChatRepositoryJdbcImpl().save(new Chat(0));
        int id = 0;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO post(game, author, requiredplayers, date, chat) VALUES (?, ?, ?, ?, ?) returning id");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        try {
            st.setInt(i, model.getGame().getId());
            st.setInt(++i, model.getAuthor().getId());
            st.setInt(++i, model.getRequiredPlayers());
            st.setDate(++i, model.getDate());
            st.setInt(++i, chatId);
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

    //@Override
    public Post findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        for (Post post : findAll()) {
            if (post.getId() == id)
                return post;
        }
        return null;
    }

    public List<Post> findByAuthor(User author) throws SQLException, IOException, ClassNotFoundException {
        List<Post> posts = new ArrayList<>();
        for (Post post : findAll()) {
            if (post.getAuthor().equals(author))
                posts.add(post);
        }
        return posts;
    }
    //@Override
    public void delete(Post model) {

    }

    //@Override
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

    public Post findByGameName(String gameName) throws SQLException, IOException, ClassNotFoundException {
        for (Post post : findAll()) {
            if (post.getGame().getName().equals(gameName))
                return post;
        }
        return null;
    }

    @Override
    public void update(Post post) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE post SET requiredplayers = ? WHERE id = ?");
        statement.setInt(1, post.getRequiredPlayers());
        statement.setInt(2, post.getId());
        statement.executeUpdate();
    }
}
