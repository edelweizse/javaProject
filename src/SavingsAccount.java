public class SavingsAccount extends BankAccount {
    public SavingsAccount(long accountNumber, double balance, double interestRate, String name) {
        super(accountNumber, balance, interestRate, name);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("New balance (Savings): " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("New balance (Savings): " + balance);
        } else {
            System.out.println("Error: Invalid withdrawal amount.");
        }
    }

    @Override
    public void interest(int months) {
        double interest = ((balance * interestRate * 30) / 360) * months;
        balance += interest;
        System.out.println("Interest calculated (Savings). \nNew balance: " + balance + "\nInterest: " + interest);
    }

    @Override
    public void displayDetails() {
        System.out.println("Savings Account Number: " + accountNumber);
        System.out.println("Savings Account Name: " + name);
        System.out.println("Savings Balance: " + balance);
        System.out.println("Savings Interest Rate: " + interestRate);
    }

    //GETTERS

    public long getSavingsAccountNumber() {
        return getAccountNumber();
    }

    public double getSavingsBalance() {
        return getBalance();
    }

    public double getSavingsInterestRate() {
        return getInterestRate();
    }

    public String getSavingsName() {
        return getName();
    }

    //SETTERS
    public void setSavingsAccountNumber(long accountNumber) {
        setAccountNumber(accountNumber);
    }

    public void setSavingsBalance(double balance) {
        setBalance(balance);
    }

    public void setSavingsInterestRate(double interestRate) {
        setInterestRate(interestRate);
    }

    public void setSavingsName(String name) {
        setName(name);
    }
}