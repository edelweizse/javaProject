import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Account implements  AccountOps {
    protected long account_id;
    protected double balance;
    protected String name;
    protected double interestRate;

    public Account(long account_id, double balance, double interestRate, String name) {
        this.account_id = account_id;
        this.balance = balance;
        this.interestRate = interestRate;
        this.name = name;
    }
    //CRUD
    public static void create(String name, double balance, double interestRate, long customer_id, String accountType) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            long account_id = Auth.generateId("account", "account_id");
            if (account_id == -1) {
                throw new RuntimeException("Error generating account ID");
            }
            String sql = "INSERT INTO account (account_id, balance, interest_rate, account_name, account_type, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, account_id);
                preparedStatement.setDouble(2, balance);
                preparedStatement.setDouble(3, interestRate);
                preparedStatement.setString(4, name);
                preparedStatement.setString(5, accountType);
                preparedStatement.setLong(6, customer_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract void read();
    public abstract void update();
    public abstract void delete();

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public void displayDetails() {
        System.out.println("Account Name: " + name);
        System.out.println("Account ID: " + account_id);
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + interestRate);
    }


    // GETTERS
    public double getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return account_id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getInterest(int months) {
        return ((balance * interestRate * 30) / 360) * months;
    }

    public String getName() {
        return name;
    }

    // SETTERS
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setName(String name) {
        this.name = name;
    }
}