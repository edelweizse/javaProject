public class Customer {
    private String name;
    private String address;
    private Account[] accounts;

    public Customer(String name, String address, Account[] accounts) {
        this.name = name;
        this.address = address;
        this.accounts = accounts;
    }

    //GETTERS
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public BankAccount getBankAccount() {
        if (accounts != null && accounts.length > 0 && accounts[0] instanceof BankAccount) {
            return (BankAccount) accounts[0];
        }
        return null;
    }

    public double getBalance() {
        BankAccount bankAccount = getBankAccount();
        return (bankAccount != null) ? bankAccount.getBalance() : 0.0;
    }

    public long getAccountNumber() {
        BankAccount bankAccount = getBankAccount();
        return (bankAccount != null) ? bankAccount.getAccountNumber() : 0;
    }

    public double getInterestRate() {
        BankAccount bankAccount = getBankAccount();
        return (bankAccount != null) ? bankAccount.getInterestRate() : 0.0;
    }

    public SavingsAccount getSavingsAccount() {
        if (accounts != null && accounts.length > 0 && accounts[1] instanceof SavingsAccount) {
            return (SavingsAccount) accounts[1];
        }
        return null;
    }

    public void depositToSavings(double amount) {
        SavingsAccount savingsAccount = getSavingsAccount();
        if (savingsAccount != null) {
            savingsAccount.deposit(amount);
        }
    }

    public void withdrawFromSavings(double amount) {
        SavingsAccount savingsAccount = getSavingsAccount();
        if (savingsAccount != null) {
            savingsAccount.withdraw(amount);
        }
    }

    public void calculateInterestForSavings(int months) {
        SavingsAccount savingsAccount = getSavingsAccount();
        if (savingsAccount != null) {
            System.out.println("Interest for " + months + " months: " + savingsAccount.getInterest(months));
            System.out.println("Interest Rate: " + savingsAccount.getInterestRate());
        }
    }

    public void addInterestToSavings(int months) {
        SavingsAccount savingsAccount = getSavingsAccount();
        if (savingsAccount != null) {
            savingsAccount.interest(months);
        }
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}