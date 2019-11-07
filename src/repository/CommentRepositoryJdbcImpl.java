package repository;

import helper.DbConnection;
import model.Comment;
import model.Post;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryJdbcImpl implements CrudRepository<Comment> {
    @Override
    public void save(Comment model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO message(post, text, date, author) VALUES (?, ?, ?, ?)");
            int i = 1;
            st.setInt(i, model.getPost().getId());
            st.setString(++i, model.getText());
            st.setDate(++i, new Date(System.currentTimeMillis()));
            st.setInt(++i, model.getAuthor().getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public Comment findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Comment model) {

    }

    @Override
    public List<Comment> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<Comment> comments = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = null;
            resultSet = statement.executeQuery("SELECT * FROM comment");
            while (resultSet.next()) {
                comments.add(new Comment(Integer.parseInt(resultSet.getString("id")),
                        new PostRepositoryJdbcImpl().findByID(resultSet.getInt("post")),
                        resultSet.getString("text"),
                        resultSet.getDate("date"),
                        new UserRepositoryJdbcImpl().findByID(resultSet.getInt("author"))
                       ));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return comments;
    }

    @Override
    public void update() {

    }
}
