/*
Database Metadata
Write a program to display database information such as database name,
version, driver name, supported features and available tables using
DatabaseMetaData.
*/

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMetadataDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (roll_no INT PRIMARY KEY, name VARCHAR(50), course VARCHAR(50))");
            DatabaseMetaData metadata = connection.getMetaData();
            System.out.println("Database: " + metadata.getDatabaseProductName());
            System.out.println("Database version: " + metadata.getDatabaseProductVersion());
            System.out.println("Driver: " + metadata.getDriverName());
            System.out.println("Driver version: " + metadata.getDriverVersion());
            System.out.println("Transactions supported: " + metadata.supportsTransactions());
            System.out.println("Batch updates supported: " + metadata.supportsBatchUpdates());
            System.out.println("Tables:");

            try (ResultSet tables = metadata.getTables(
                    connection.getCatalog(), null, "%", new String[]{"TABLE"})) {
                while (tables.next()) {
                    System.out.println(" - " + tables.getString("TABLE_NAME"));
                }
            }
        } catch (SQLException exception) {
            System.out.println("Could not read database metadata: " + exception.getMessage());
        }
    }
}
