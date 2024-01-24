import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    BankAccount bankAccount = new BankAccount(218756129, 3500, 0.145, "Regular");
    SavingsAccount savingsAccount = new SavingsAccount(275601237, 10000, 0.3, "Savings");
    Customer customer = new Customer("Random", "Random St.", new Account[]{bankAccount, savingsAccount});

    System.out.println("Welcome to NMBank:");

    while (true) {
      System.out.println("\nSelect an option:");
      System.out.println("1. Deposit to Bank Account");
      System.out.println("2. Withdraw from Bank Account");
      System.out.println("3. Calculate interest for Bank Account");
      System.out.println("4. Add interest to Bank Account");
      System.out.println("5. Deposit to Savings Account");
      System.out.println("6. Withdraw from Savings Account");
      System.out.println("7. Calculate interest for Savings Account");
      System.out.println("8. Add interest to Savings Account");
      System.out.println("9. Customer info");
      System.out.println("10. Edit profile");
      System.out.println("11. Exit");

      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          System.out.print("Deposit amount to Bank Account: ");
          double bankDepositAmount = sc.nextDouble();
          customer.getAccounts()[0].deposit(bankDepositAmount);
          break;
        case 2:
          System.out.print("Withdrawal amount from Bank Account: ");
          double bankWithdrawalAmount = sc.nextDouble();
          customer.getAccounts()[0].withdraw(bankWithdrawalAmount);
          break;
        case 3:
          System.out.print("Months for interest calculation for Bank Account: ");
          int bankMonths = sc.nextInt();
          System.out.println("Interest for " + bankMonths + " months: " + customer.getAccounts()[0].getInterest(bankMonths));
          System.out.println("Interest Rate: " + customer.getAccounts()[0].getInterestRate());
          break;
        case 4:
          System.out.print("Months for getting interest for Bank Account: ");
          int bankMonthsAdd = sc.nextInt();
          customer.getAccounts()[0].interest(bankMonthsAdd);
          break;
        case 5:
          System.out.print("Deposit amount to Savings Account: ");
          double savingsDepositAmount = sc.nextDouble();
          customer.getAccounts()[1].deposit(savingsDepositAmount);
          break;
        case 6:
          System.out.print("Withdrawal amount from Savings Account: ");
          double savingsWithdrawalAmount = sc.nextDouble();
          customer.getAccounts()[1].withdraw(savingsWithdrawalAmount);
          break;
        case 7:
          System.out.print("Months for interest calculation for Savings Account: ");
          int savingsMonths = sc.nextInt();
          customer.getAccounts()[1].getInterest(savingsMonths);
          break;
        case 8:
          System.out.print("Months for getting interest for Savings Account: ");
          int savingsMonthsAdd = sc.nextInt();
          customer.getAccounts()[1].interest(savingsMonthsAdd);
          break;
        case 9:
          customerInfo(customer);
          break;
        case 10:
          System.out.println("Edit profile:");
          System.out.print("Name: ");
          String name = sc.next();
          customer.setName(name);
          System.out.print("Address: ");
          String address = sc.next();
          customer.setAddress(address);
          break;
        case 11:
          System.out.println("Exiting...");
          System.exit(0);
        default:
          System.out.println("Invalid option.");
      }
    }
  }

  public static void customerInfo(Customer customer) {
    System.out.println("\nCustomer info:");
    System.out.println("Customer: " + customer.getName());
    System.out.println("Address: " + customer.getAddress());
    customer.getAccounts()[0].displayDetails();
    customer.getAccounts()[1].displayDetails();
  }
}