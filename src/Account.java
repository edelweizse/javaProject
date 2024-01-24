public abstract class Account {
    protected long accountNumber;
    protected double balance;
    protected String name;

    protected double interestRate;

    public Account(long accountNumber, double balance, double interestRate, String name){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
        this.name = name;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void interest(int  months);
}
