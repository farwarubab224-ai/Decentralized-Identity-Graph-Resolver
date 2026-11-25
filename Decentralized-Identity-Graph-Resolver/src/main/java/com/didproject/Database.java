package com.didproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL =
        "jdbc:sqlite:data/identity_graph.db";

    // Database connection
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create users table
    public static void initialize() {

        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                full_name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                github_username TEXT,
                wallet_address TEXT,
                created_at DATETIME DEFAULT CURRENT_TIMESTAMP
            )
            """;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            System.out.println("Database connected successfully!");

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Save user information
    public static boolean saveUser(
        String name,
        String email,
        String github,
        String wallet
    ) {

        String sql = """
            INSERT INTO users
            (full_name, email, github_username, wallet_address)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = connect();
             var statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, github);
            statement.setString(4, wallet);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Save Error: " + e.getMessage());
            return false;
        }
    }
}
