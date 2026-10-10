/*
ResultSet Metadata
Display the column names, column count, data types and other properties of
a table dynamically using ResultSetMetaData.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetadataDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement setup = connection.createStatement()) {
            setup.executeUpdate("CREATE TABLE students (roll_no INT PRIMARY KEY, name VARCHAR(50), course VARCHAR(50))");

            try (Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery("SELECT * FROM students")) {
                ResultSetMetaData metadata = result.getMetaData();
                int columnCount = metadata.getColumnCount();
                System.out.println("Column count: " + columnCount);
                for (int column = 1; column <= columnCount; column++) {
                    System.out.println("Column " + column + ":");
                    System.out.println("  Name: " + metadata.getColumnName(column));
                    System.out.println("  Type: " + metadata.getColumnTypeName(column));
                    System.out.println("  Nullable: " + (metadata.isNullable(column) == ResultSetMetaData.columnNullable ? "Yes" : "No"));
                    System.out.println("  Display size: " + metadata.getColumnDisplaySize(column));
                }
            }
        } catch (SQLException exception) {
            System.out.println("Could not read result set metadata: " + exception.getMessage());
        }
    }
}
