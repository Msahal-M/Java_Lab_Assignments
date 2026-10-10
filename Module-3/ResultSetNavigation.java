/*
ResultSet Navigation
Retrieve employee/student records and demonstrate ResultSet navigation
methods such as next(), previous(), first(), last() and absolute().
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetNavigation {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    private static void display(ResultSet result) throws SQLException {
        System.out.println(result.getInt("roll_no") + "  " + result.getString("name") + "  " + result.getString("course"));
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement setup = connection.createStatement()) {

            setup.executeUpdate("CREATE TABLE IF NOT EXISTS students (roll_no INT PRIMARY KEY, name VARCHAR(50), course VARCHAR(50))");
            setup.executeUpdate("INSERT INTO students VALUES (301, 'Maya', 'BCA'), (302, 'Arun', 'BSc'), (303, 'Neha', 'BCom') ON DUPLICATE KEY UPDATE name = VALUES(name), course = VALUES(course)");

            try (Statement statement = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet result = statement.executeQuery("SELECT roll_no, name, course FROM students ORDER BY roll_no")) {

                System.out.println("first():");
                if (result.first()) {
                    display(result);
                }

                System.out.println("next():");
                if (result.next()) {
                    display(result);
                }

                System.out.println("previous():");
                if (result.previous()) {
                    display(result);
                }

                System.out.println("last():");
                if (result.last()) {
                    display(result);
                }

                System.out.println("absolute(2):");
                if (result.absolute(2)) {
                    display(result);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Could not navigate the result set: " + exception.getMessage());
        }
    }
}
