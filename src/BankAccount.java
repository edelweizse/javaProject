public class BankAccount extends Account {
    public BankAccount(long accountNumber, double balance, double interestRate, String name) {
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
}
