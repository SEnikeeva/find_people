package repository;

import helper.DbConnection;
import model.Game;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryJdbcImpl implements CrudRepository<Game>{
    @Override
    public int save(Game model) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = new DbConnection().getConnection();
        PreparedStatement st;
        int id = 0;
        try {
            st = connection.prepareStatement(
                    "INSERT INTO game(name, description, picture) VALUES (?, ?, ?) returning id");
            int i = 1;
            st.setString(i, model.getName());
            st.setString(++i, model.getDescription());
            st.setString(++i, model.getPicture());
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
    public Game findByID(int id) throws SQLException, IOException, ClassNotFoundException {
        for (Game game : findAll()) {
            if (game.getId() == id)
                return game;
        }
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM game ");
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
    public void update(Game game) {

    }
}
