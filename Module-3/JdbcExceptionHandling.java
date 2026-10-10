/*
JDBC Exception & Error Handling
Develop a JDBC application that handles connection errors, invalid SQL
queries, duplicate records and invalid input using appropriate exception
handling.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcExceptionHandling {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    public static void main(String[] args) {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException exception) {
            System.out.println("Connection error: check MySQL is running and credentials are correct.");
            System.out.println(exception.getMessage());
            return;
        }

        try (Connection db = connection; Scanner input = new Scanner(System.in)) {
            try (Statement statement = db.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS exception_students (roll_no INT PRIMARY KEY, name VARCHAR(50) NOT NULL)");
            }

            System.out.print("Enter student roll number: ");
            int rollNo;
            try {
                rollNo = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input: roll number must be a whole number.");
                return;
            }

            System.out.print("Enter student name: ");
            String name = input.nextLine();
            try (PreparedStatement insert = db.prepareStatement( "INSERT INTO exception_students (roll_no, name) VALUES (?, ?)")) {
                insert.setInt(1, rollNo);
                insert.setString(2, name);
                insert.executeUpdate();
                System.out.println("Student inserted.");
            } catch (SQLIntegrityConstraintViolationException exception) {
                System.out.println("Duplicate record: that roll number already exists.");
            }

            try (Statement statement = db.createStatement()) {
                statement.executeQuery("SELEC * FROM exception_students");
            } catch (SQLException exception) {
                System.out.println("Invalid SQL query: " + exception.getMessage());
            }
        } catch (SQLException exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }
}
