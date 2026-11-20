package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = System.getenv().getOrDefault(
            "LIBRARY_DB_URL",
            "jdbc:mysql://localhost:3306/librarydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String user = System.getenv("LIBRARY_DB_USER");
        String password = System.getenv("LIBRARY_DB_PASSWORD");
        if (user == null || user.isBlank() || password == null) {
            throw new SQLException("Set LIBRARY_DB_USER and LIBRARY_DB_PASSWORD before starting the application.");
        }
        return DriverManager.getConnection(URL, user, password);
    }
}
