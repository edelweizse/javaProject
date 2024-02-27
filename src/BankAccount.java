import java.sql.*;
import java.util.Objects;

public class BankAccount extends Account {
    public BankAccount(long account_id, double balance, double interestRate, String name) {
        super(account_id, balance, interestRate, name);
    }
    // CRUD
    @Override
    public void read() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM account WHERE account_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, this.account_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        this.balance = resultSet.getDouble("balance");
                        this.interestRate = resultSet.getDouble("interest_rate");
                        this.name = resultSet.getString("account_name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "UPDATE account SET balance = ?, interest_rate = ?, account_name = ? WHERE account_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, this.balance);
                preparedStatement.setDouble(2, this.interestRate);
                preparedStatement.setString(3, this.name);
                preparedStatement.setLong(4, this.account_id);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void updateBalance() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "UPDATE account SET balance = ? WHERE account_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, this.balance);
                preparedStatement.setLong(2, this.account_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "DELETE FROM qccount WHERE account_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, this.account_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            updateBalance();
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            updateBalance();
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Error: Invalid withdrawal amount.");
        }
    }
}