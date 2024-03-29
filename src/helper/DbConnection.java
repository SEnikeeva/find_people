package helper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

    private static Connection connection = null;

    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException {

        if (connection != null)
            return connection;
        else {

            Properties prop = new Properties();
            InputStream inputStream = DbConnection.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

            return connection;
        }

    }
}