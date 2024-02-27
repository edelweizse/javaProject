import java.util.List;
import java.util.Scanner;

public class CLI {
    public static void startMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the NMBank!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    Customer customer = loginUser(scanner);
                    if (customer != null) {
                        handleCustomerMenu(scanner, customer);
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void registerUser(Scanner scanner) {
        System.out.println("Enter your name:");
        String customer_name = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        System.out.println("Enter your address:");
        String address = scanner.nextLine();

        Customer customer = Auth.registerCustomer(customer_name, email, password, address);

        if (customer != null) {
            System.out.println("Registration successful. Welcome, " + customer.getName() + "!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
    public static Customer loginUser(Scanner scanner) {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();


        return Auth.login(email, password);
    }
    public static void handleCustomerMenu(Scanner scanner, Customer currentCustomer){
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Display Customer Details");
            System.out.println("2. Accounts Menu");
            System.out.println("3. Edit profile");
            System.out.println("4. Delete Account");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentCustomer.displayDetails();
                    break;
                case 2:
                    accountsMenu(scanner, currentCustomer);
                    break;
                case 3:
                    editProfileMenu(scanner, currentCustomer);
                    break;
                case 4:
                    deleteCustomerMenu(scanner, currentCustomer);
                    return;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void accountsMenu(Scanner scanner, Customer currentCustomer){
        System.out.println("\nSelect an option:");
        System.out.println("1. Bank Account Menu");
        System.out.println("2. Savings Account Menu");
        System.out.println("3. Create account");
        System.out.println("4. Back");

        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                bankAccountMenu(scanner, currentCustomer);
                break;
            case 2:
                savingsAccountMenu(scanner, currentCustomer);
                break;
            case 3:
                createAccountMenu(scanner, currentCustomer);
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    public static void createAccountMenu(Scanner scanner, Customer currentCustomer){
        System.out.println("\nSelect an option:");
        System.out.println("1. Create Bank Account");
        System.out.println("2. Create Savings Account");
        System.out.println("3. Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter account name:");
                String accountName = scanner.nextLine();

                System.out.println("Enter initial balance:");
                double balance = scanner.nextDouble();

                System.out.println("Enter interest rate:");
                double interestRate = scanner.nextDouble();

                BankAccount.create(accountName, balance, interestRate, currentCustomer.getCustomerId(), "Bank");
                currentCustomer.read();
                System.out.println("Account created successfully.");
                break;
            case 2:
                System.out.println("Enter account name:");
                String savAccountName = scanner.nextLine();

                System.out.println("Enter initial balance:");
                double savBalance = scanner.nextDouble();

                System.out.println("Enter interest rate:");
                double savInterestRate = scanner.nextDouble();

                SavingsAccount.create(savAccountName, savBalance, savInterestRate, currentCustomer.getCustomerId(), "Savings");
                currentCustomer.read();
                System.out.println("Account created successfully.");
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    public static void editProfileMenu(Scanner scanner, Customer currentCustomer) {
        System.out.println('\n' + "Select an option:");
        System.out.println("1. Change Name");
        System.out.println("2. Change Email");
        System.out.println("3. Change Password");
        System.out.println("4. Change Address");
        System.out.println("5. Back");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Enter new name:");
                currentCustomer.update("customer_name", scanner.nextLine());
                break;
            case 2:
                System.out.println("Enter new email:");
                currentCustomer.update("email", scanner.nextLine());
                break;
            case 3:
                System.out.println("Enter new password:");
                currentCustomer.update("pass", scanner.nextLine());
                break;
            case 4:
                System.out.println("Enter new address:");
                currentCustomer.update("address", scanner.nextLine());
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void deleteCustomerMenu(Scanner scanner, Customer currentCustomer){
        System.out.println("Are you sure?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                currentCustomer.delete();
                System.out.println("Account deleted.");
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
    public static void bankAccountMenu(Scanner scanner, Customer currentCustomer){
        List<Account> accounts = currentCustomer.getAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No Bank Accounts found for this customer.");
            return;
        }

        System.out.println("\nSelect a Bank Account:");
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof BankAccount) {
                System.out.println((i + 1) + ". " + accounts.get(i).getName());
            }
        }

        int accountChoice = scanner.nextInt();
        if (accountChoice < 1 || accountChoice > accounts.size()) {
            System.out.println("Invalid account selection.");
            return;
        }

        BankAccount selectedAccount = (BankAccount) accounts.get(accountChoice - 1);

        System.out.println("\nSelect an operation for Bank Account:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Info");
        System.out.println("4. Delete account");
        System.out.println("5. Back");

        int operationChoice = scanner.nextInt();

        switch (operationChoice) {
            case 1:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                selectedAccount.deposit(depositAmount);
                break;
            case 2:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                selectedAccount.withdraw(withdrawalAmount);
                break;
            case 3:
                selectedAccount.displayDetails();
                break;
            case 4:
                System.out.println("Are you sure?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        selectedAccount.delete();
                        System.out.println("Account deleted.");
                        handleCustomerMenu(scanner, currentCustomer);;
                    case 2:
                        handleCustomerMenu(scanner, currentCustomer);;
                    default:
                        System.out.println("Invalid choice");
                        handleCustomerMenu(scanner, currentCustomer);
                }
            case 5:
                break;
            default:
                System.out.println("Invalid operation selection.");
        }
    }

    private static void savingsAccountMenu(Scanner scanner, Customer currentCustomer){
        List<Account> accounts = currentCustomer.getAccounts();

        if (accounts.isEmpty()) {
            System.out.println("No Savings Accounts found for this customer.");
            return;
        }

        System.out.println("\nSelect a Savings Account:");
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) instanceof SavingsAccount) {
                System.out.println(i + ". " + accounts.get(i).getName());
            }
        }

        int accountChoice = scanner.nextInt() + 1;
        if (accountChoice < 1 || accountChoice > accounts.size()) {
            System.out.println("Invalid account selection.");
            return;
        }

        SavingsAccount selectedAccount = (SavingsAccount) accounts.get(accountChoice - 1);

        System.out.println("\nSelect an operation for " + selectedAccount.getName() + " account:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Info");
        System.out.println("4. Calculate Interest");
        System.out.println("5. Add Interest");
        System.out.println("6. Delete Account");
        System.out.println("7. Back");

        int operationChoice = scanner.nextInt();

        switch (operationChoice) {
            case 1:
                System.out.print("Enter deposit amount: ");
                double depositAmount = scanner.nextDouble();
                selectedAccount.deposit(depositAmount);
                break;
            case 2:
                System.out.print("Enter withdrawal amount: ");
                double withdrawalAmount = scanner.nextDouble();
                selectedAccount.withdraw(withdrawalAmount);
                break;
            case 3:
                selectedAccount.displayDetails();
                break;
            case 4:
                System.out.print("Enter months for adding interest: ");
                int addInterestMonths = scanner.nextInt();
                selectedAccount.interest(addInterestMonths);
                break;
            case 5:
                System.out.print("Enter months for interest calculation: ");
                int interestMonths = scanner.nextInt();
                selectedAccount.calculateInterest(interestMonths);
                break;
            case 6:
                System.out.println("Are you sure?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = scanner.nextInt();
                switch(choice){
                    case 1:
                        selectedAccount.delete();
                        System.out.println("Account deleted.");
                        handleCustomerMenu(scanner, currentCustomer);;
                    case 2:
                        handleCustomerMenu(scanner, currentCustomer);;
                    default:
                        System.out.println("Invalid choice");
                        handleCustomerMenu(scanner, currentCustomer);
                }
            case 7:
                break;
            default:
                System.out.println("Invalid operation selection.");
        }
    }
}
