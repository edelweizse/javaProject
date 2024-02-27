import java.sql.*;
import java.util.List;

public class Auth {
    public static Customer registerCustomer(String customer_name, String email, String password, String address) {
        if (isEmailRegistered(email)) {
            System.out.println("Email already registered.");
            return null;
        }
        return Customer.create(customer_name, email, password, address);
    }
    public static boolean isEmailRegistered(String email) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String checkEmailSQL = "SELECT * FROM customer WHERE email = ?";
            try (PreparedStatement checkEmailStatement = connection.prepareStatement(checkEmailSQL)) {
                checkEmailStatement.setString(1, email);
                try (ResultSet resultSet = checkEmailStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Customer login(String email, String password) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM customer WHERE email = ? AND pass = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        long customer_id = resultSet.getLong("customer_id");
                        String customer_name = resultSet.getString("customer_name");
                        String address = resultSet.getString("address");

                        List<Account> accounts = Customer.getAllAccounts(customer_id);

                        return new Customer(customer_id, customer_name, address, accounts, email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long generateId(String tableName, String idColumnName) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT MAX(" + idColumnName + ") AS max_id FROM "+ tableName;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getLong("max_id") + 1;
                    } else {
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
