public class BankAccount extends Account{

    public BankAccount(long accountNumber, double balance, double interestRate, String name){
        super(accountNumber, balance, interestRate, name);
    }
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("New balance: " + balance);
        } else {
            System.out.println("Error: Invalid withdrawal amount.");
        }
    }
    @Override
    public void interest(int months) {
        double interest = ((balance * interestRate * 30) / 360) * months;
        balance += interest;
        System.out.println("Interest calculated. \nNew balance: " + balance + "\nInterest: " + interest);
    }

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
