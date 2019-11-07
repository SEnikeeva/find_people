package repository;

import model.Subscription;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRepositoryJdbcImpl implements CrudRepository<Subscription> {

    private Connection connection;

    public SubscriptionRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Subscription model) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO subscription(subscriber, subscription) VALUES (?, ?)");
            int i = 1;
            st.setInt(i, model.getSubscriber().getId());
            st.setInt(++i, model.getSubscription().getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Subscription findByID(int id) {
        for (Subscription subscription : findAll()) {
            if (subscription.getId() == id) {
                return subscription;
            }
        }
        return null;
    }

    @Override
    public void delete(Subscription model) {

    }


    @Override
    public List<Subscription> findAll() {
        List<Subscription> subscriptions = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = null;
            resultSet = statement.executeQuery("SELECT * FROM subscription ");
            while (resultSet.next()) {
                subscriptions.add(new Subscription(resultSet.getInt("id"), new UserRepositoryJdbcImpl().findByID(resultSet.getInt("subscriber")),
                        new UserRepositoryJdbcImpl().findByID(resultSet.getInt("subscription"))));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    @Override
    public void update() {

    }
}
