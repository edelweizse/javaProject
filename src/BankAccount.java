public class BankAccount {
    private long accountNumber;
    private double balance;
    private double interestRate;

    public BankAccount(long accountNumber, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Error: Invalid withdrawal amount.");
        }
    }

    public void interest(int months) {
        double interest = ((balance * interestRate * 30) / 360) * months;
        balance += interest;
        System.out.println("Interest calculated. \nNew balance: " + balance + "\nInterest: " + interest);
    }

    // GETTERS
    public double getBalance() {
        return balance;
    }
    public long getAccountNumber() {
        return accountNumber;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getInterest(int months) {
        return ((balance * interestRate * 30) / 360) * months;
    }

    // SETTERS
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
