public class Customer {
    private String name;
    private String address;
    private Account[] accounts;

    public Customer(String name, String address, Account[] accounts) {
        this.name = name;
        this.address = address;
        this.accounts = accounts;
    }

    // GETTERS
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void depositToSavings(double amount) {
        if (accounts != null && accounts.length > 1 && accounts[1] instanceof SavingsAccount) {
            ((SavingsAccount) accounts[1]).deposit(amount);
        }
    }

    public void withdrawFromSavings(double amount) {
        if (accounts != null && accounts.length > 1 && accounts[1] instanceof SavingsAccount) {
            ((SavingsAccount) accounts[1]).withdraw(amount);
        }
    }

    public void calculateInterestForSavings(int months) {
        if (accounts != null && accounts.length > 1 && accounts[1] instanceof SavingsAccount) {
            SavingsAccount savingsAccount = (SavingsAccount) accounts[1];
            System.out.println("Interest for " + months + " months: " + savingsAccount.getInterest(months));
            System.out.println("Interest Rate: " + savingsAccount.getInterestRate());
        }
    }

    public void addInterestToSavings(int months) {
        if (accounts != null && accounts.length > 1 && accounts[1] instanceof SavingsAccount) {
            ((SavingsAccount) accounts[1]).interest(months);
        }
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }
}