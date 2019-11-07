package repository;

import helper.DbConnection;
import model.Game;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryJdbcImpl implements CrudRepository<Game> {
    @Override
    public void save(Game model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO users(name, description, picture) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        try {
            st.setString(i, model.getName());
            st.setString(++i, model.getDescription());
            st.setString(++i, model.getPicture());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Game findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        return null;
    }

    @Override
    public void delete(Game model) {

    }

    @Override
    public List<Game> findAll() throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        List<Game> games = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ");
            while (resultSet.next()) {
                games.add(new Game(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("picture")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return games;
    }

    @Override
    public void update() {

    }
}
