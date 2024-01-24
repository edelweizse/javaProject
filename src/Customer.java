public class Customer {
    private String name;
    private String address;
    private BankAccount bankAccount;

    public Customer(String name, String address, BankAccount bankAccount) {
        this.name = name;
        this.address = address;
        this.bankAccount = bankAccount;
    }
    //GETTERS
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public double getBalance() {
        return bankAccount.getBalance();
    }

    public long getAccountNumber() {
        return bankAccount.getAccountNumber();
    }

    public double getInterestRate() {
        return bankAccount.getInterestRate();
    }

    //SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
