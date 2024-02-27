import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer implements CustomerOps {
    private String customer_name;
    private long customer_id;
    private String email;
    private String address;
    private List<Account> accounts;

    public Customer(long customer_id, String customer_name, String address, List<Account> accounts, String email){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.accounts = accounts;
        this.email = email;
    }
    //CRUD
    public static Customer create(String customer_name, String email, String password, String address) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            long customer_id = Auth.generateId("customer", "customer_id");
            if (customer_id == -1) {
                System.out.println("Registration failed.");
                return null;
            }

            String insertCustomerSQL = "INSERT INTO customer (customer_id, customer_name, email, pass, address) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertCustomerStatement = connection.prepareStatement(insertCustomerSQL)) {
                insertCustomerStatement.setLong(1, customer_id);
                insertCustomerStatement.setString(2, customer_name);
                insertCustomerStatement.setString(3, email);
                insertCustomerStatement.setString(4, password);
                insertCustomerStatement.setString(5, address);

                int affectedRows = insertCustomerStatement.executeUpdate();
                if (affectedRows == 0) {
                    System.out.println("Registration failed.");
                    return null;
                }

                List<Account> accounts = Customer.getAllAccounts(customer_id);

                System.out.println("Registration successful. Customer ID: " + customer_id);
                return new Customer(customer_id, customer_name, address, accounts, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void read() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM customer WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, this.customer_id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        this.email = resultSet.getString("email");
                        this.customer_name = resultSet.getString("customer_name");
                        this.address = resultSet.getString("address");
                        this.accounts = Customer.getAllAccounts(this.customer_id);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(String columnName, String newValue){
            try (Connection connection = DatabaseConnector.getConnection()) {
                String sql = "UPDATE customer SET " + columnName + " = ? WHERE customer_id = ?;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, newValue);
                    preparedStatement.setLong(2, this.customer_id);
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public void delete() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "DELETE FROM customer WHERE customer_id = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, this.customer_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // GETTERS
    public static List<Account> getAllAccounts(long customer_id){
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM account WHERE customer_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, customer_id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        long account_id = resultSet.getLong("account_id");
                        double balance = resultSet.getDouble("balance");
                        double interestRate = resultSet.getDouble("interest_rate");
                        String account_name = resultSet.getString("account_name");
                        String accountType = resultSet.getString("account_type");

                        Account account;
                        if ("Bank".equals(accountType)) {
                            account = new BankAccount(account_id, balance, interestRate, account_name);
                        } else if ("Savings".equals(accountType)) {
                            account = new SavingsAccount(account_id, balance, interestRate, account_name);
                        } else {
                            continue;
                        }

                        accounts.add(account);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void displayDetails() {
        System.out.println("Customer Name: " + customer_name);
        System.out.println("Customer ID: " + customer_id);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }
    public String getName() {
        return customer_name;
    }

    public String getAddress() {
        return address;
    }

    public long getCustomerId() {  return customer_id; }

    public String getEmail() { return this.email; }

    public List<Account> getAccounts() {
        return accounts;
    }


    // SETTERS
    public void setName(String name) {
        this.customer_name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCustomerId(long customer_id) { this.customer_id = customer_id; }

    public void setEmail(String email) { this.email = email; }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}