package com.mobinyousefi.traffic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple JDBC configuration helper.
 *
 * In a real deployment you would externalize credentials (e.g. environment variables or JNDI DataSource).
 */
public final class DatabaseConfig {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/traffic_controller?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "traffic_user";
    private static final String JDBC_PASSWORD = "traffic_password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Failed to load MySQL JDBC driver");
        }
    }

    private DatabaseConfig() {
        // utility class
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
