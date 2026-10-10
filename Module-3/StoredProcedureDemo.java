/*
CallableStatement & Stored Procedures
Create a MySQL stored procedure and invoke it from Java using CallableStatement.
*/

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcedureDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (  roll_no INT PRIMARY KEY, name VARCHAR(50), course VARCHAR(50))");
            statement.executeUpdate("INSERT INTO students VALUES (201, 'Ravi', 'BCA') ON DUPLICATE KEY UPDATE name = 'Ravi', course = 'BCA'");

            statement.executeUpdate("DROP PROCEDURE IF EXISTS get_student");
            statement.executeUpdate("CREATE PROCEDURE get_student(IN student_roll INT) SELECT roll_no, name, course FROM students WHERE roll_no = student_roll");

            try (CallableStatement call = connection.prepareCall("{call get_student(?)}")) {
                call.setInt(1, 201);
                try (ResultSet result = call.executeQuery()) {
                    if (result.next()) {
                        System.out.println("Roll number: " + result.getInt("roll_no"));
                        System.out.println("Name: " + result.getString("name"));
                        System.out.println("Course: " + result.getString("course"));
                    } else {
                        System.out.println("No student found.");
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println("Stored procedure operation failed: " + exception.getMessage());
        }
    }
}
