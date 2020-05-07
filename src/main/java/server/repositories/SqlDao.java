package server.repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SqlDao
{
    private Properties properties = new Properties();
    private Connection connection;

    protected SqlDao() throws ClassNotFoundException, IOException
    {
        try (FileInputStream stream = new FileInputStream("db.properties"))
        {
            properties.load(stream);
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    protected void connect() throws SQLException
    {
        connection = DriverManager.getConnection(
                properties.getProperty("connectionString"), properties);
    }

    protected ResultSet executeQuery(String query) throws SQLException
    {
        Statement s = connection.createStatement();
        return s.executeQuery(query);
    }

    protected PreparedStatement prepareStatement(String query) throws SQLException
    {
        return connection.prepareStatement(query);
    }

    protected void disconnect() throws SQLException
    {
        connection.close();
    }
}

