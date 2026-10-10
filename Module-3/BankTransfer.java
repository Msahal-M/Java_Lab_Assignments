/*
JDBC Transactions
Implement a bank money-transfer system using JDBC transactions. Use commit()
when successful and rollback() when an error occurs.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BankTransfer {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "jdbcuser";
    private static final String PASSWORD = "jdbcuser";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner input = new Scanner(System.in);
             Statement setup = connection.createStatement()) {

            setup.executeUpdate("CREATE TABLE IF NOT EXISTS bank_accounts (account_no INT PRIMARY KEY, holder_name VARCHAR(50), balance DECIMAL(10, 2) NOT NULL)");
            setup.executeUpdate("INSERT INTO bank_accounts VALUES (1001, 'Asha', 5000.00), (1002, 'Bala', 3000.00) ON DUPLICATE KEY UPDATE account_no = VALUES(account_no)");

            System.out.print("Enter sender account number: ");
            int sender = Integer.parseInt(input.nextLine());
            System.out.print("Enter receiver account number: ");
            int receiver = Integer.parseInt(input.nextLine());
            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(input.nextLine());

            if (amount <= 0) {
                System.out.println("Transfer amount must be greater than zero.");
                return;
            }

            connection.setAutoCommit(false);
            try {
                if (getBalance(connection, sender) < amount) {
                    throw new SQLException("The sender does not have enough balance.");
                }

                try (PreparedStatement withdraw = connection.prepareStatement("UPDATE bank_accounts SET balance = balance - ? WHERE account_no = ?");
                     PreparedStatement deposit = connection.prepareStatement("UPDATE bank_accounts SET balance = balance + ? WHERE account_no = ?")) {
                    withdraw.setDouble(1, amount);
                    withdraw.setInt(2, sender);
                    if (withdraw.executeUpdate() != 1) {
                        throw new SQLException("Sender account was not found.");
                    }

                    deposit.setDouble(1, amount);
                    deposit.setInt(2, receiver);
                    if (deposit.executeUpdate() != 1) {
                        throw new SQLException("Receiver account was not found.");
                    }
                }

                connection.commit();
                System.out.println("Transfer successful. Transaction committed.");
            } catch (SQLException exception) {
                connection.rollback();
                System.out.println("Transfer failed. Transaction rolled back: " + exception.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (NumberFormatException exception) {
            System.out.println("Account numbers and amount must be numeric.");
        } catch (SQLException exception) {
            System.out.println("Database operation failed: " + exception.getMessage());
        }
    }

    private static double getBalance(Connection connection, int accountNo) throws SQLException {
        try (PreparedStatement query = connection.prepareStatement("SELECT balance FROM bank_accounts WHERE account_no = ?")) {
            query.setInt(1, accountNo);
            try (ResultSet result = query.executeQuery()) {
                if (result.next()) {
                    return result.getDouble("balance");
                }
                throw new SQLException("Sender account was not found.");
            }
        }
    }
}
