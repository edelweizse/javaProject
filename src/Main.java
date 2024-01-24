import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(1037122582, 3500, 0.145);
        Customer customer = new Customer("Random", "Random St.", account);

        System.out.println("Welcome to NMBank:");

        while (true) {

            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate interest");
            System.out.println("4. Add interest");
            System.out.println("5. Customer info");
            System.out.println("6. Edit profile");
            System.out.println("7. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    customer.getBankAccount().deposit(depositAmount);
                    customerInfo(customer);
                    break;
                case 2:
                    System.out.print("Withdrawal amount: ");
                    double withdrawalAmount = sc.nextDouble();
                    customer.getBankAccount().withdraw(withdrawalAmount);
                    customerInfo(customer);
                    break;
                case 3:
                    System.out.print("Months for interest calculation: ");
                    int months = sc.nextInt();
                    System.out.println("Interest for " + months + " months: " + customer.getBankAccount().getInterest(months));
                    System.out.println("Interest Rate: " + customer.getBankAccount().getInterestRate());
                    break;
                case 4:
                    System.out.print("Months for getting interest: ");
                    int months_add = sc.nextInt();
                    customer.getBankAccount().interest(months_add);
                    customerInfo(customer);
                case 5:
                    customerInfo(customer);
                    break;
                case 6:
                    System.out.println("Edit profile:");
                    System.out.print("Name: ");
                    String name = sc.next();
                    customer.setName(name);
                    System.out.print("Address: ");
                    String address = sc.next();
                    customer.setAddress(address);
                    customerInfo(customer);
                    break;
                case 7:
                    System.out.println("Exiting NMBank...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }

        }
    }
    public static void customerInfo(Customer customer) {
        System.out.println("\nCustomer info:");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Account number: " + customer.getAccountNumber());
        System.out.println("Balance: " + customer.getBalance());
        System.out.println("Interest rate: " + customer.getInterestRate());
        System.out.println("Address: " + customer.getAddress());
    }
}
