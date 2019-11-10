package repository;

import helper.DbConnection;
import model.Comment;
import model.Post;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryJdbcImpl {
    //@Override
    public int save(Comment model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        int id = 0;
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO comment(text, date) VALUES (?, ?) returning id");
            int i = 1;
            st.setString(i, "");
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

    //@Override
    public Comment findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    //@Override
    public void delete(Comment model) {

    }

    //@Override
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
                        resultSet.getString("text"),
                        resultSet.getDate("date")
                       ));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return comments;
    }

    //@Override
    public void update() {

    }
}
