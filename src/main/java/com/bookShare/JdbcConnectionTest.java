package com.bookShare;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionTest {
    public static void main(String[] args) {
        // Replace with your database connection details
        String dbUrl = "jdbc:mysql://localhost:3306/bookShare";
        String username = "root";
        String password = "";

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);

            System.out.println("Connected to the database successfully!");

            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}