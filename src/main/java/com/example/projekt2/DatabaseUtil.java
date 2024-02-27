package com.example.projekt2;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.*;

public class DatabaseUtil {
    private static final String URL = "jdbc:h2:~/tuzildo1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static Connection getConnection() throws SQLException {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL(URL);
        ds.setUser(USER);
        ds.setPassword(PASSWORD);

       return ds.getConnection();
    }

    public static void createTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255))";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
