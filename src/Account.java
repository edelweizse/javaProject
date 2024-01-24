public abstract class Account {
    protected long accountNumber;
    protected double balance;
    protected String name;
    protected double interestRate;

    public Account(long accountNumber, double balance, double interestRate, String name) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.name = name;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void interest(int months);

    public void displayDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Name: " + name);
        System.out.println("Balance: " + balance);
        System.out.println("Interest Rate: " + interestRate);
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

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }
}