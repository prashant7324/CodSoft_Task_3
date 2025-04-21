import java.util.Scanner;

// BankAccount class
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double checkBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// ATM class
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        int choice;
        do {
            System.out.println("\nSelect an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Funds");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number between 1 and 4: ");
                scanner.next();
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.checkBalance());
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: $");
        double amount = getValidAmount();
        if (account.deposit(amount)) {
            System.out.printf("Successfully deposited $%.2f%n", amount);
        } else {
            System.out.println("Deposit failed. Please enter a positive amount.");
        }
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: $");
        double amount = getValidAmount();
        if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f%n", amount);
        } else {
            System.out.println("Withdrawal failed. Insufficient balance or invalid amount.");
        }
    }

    private double getValidAmount() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a valid amount: $");
            scanner.next();
        }
        return scanner.nextDouble();
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00); // Starting with $1000
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
