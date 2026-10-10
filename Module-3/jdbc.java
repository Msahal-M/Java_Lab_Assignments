/*
JDBC Architecture & Basic Database Connection [orig. Roll 20, Q1]
Write a Java program to connect to a MySQL database using JDBC and display
a successful connection message.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to MySQL database successfully.");
            
        } catch (SQLException exception) {
            System.out.println("Could not connect to the database: " + exception.getMessage());
        }
    }
}
