package p1;

import java.util.*;

public class ATM {
    private static Map<String, String> accountNumbers = new HashMap<>(); 
    private static Map<String, Double> balances = new HashMap<>();
    private static String currentAccountNumber;

    public static void main(String[] args) {
        accountNumbers.put("1001 5632 9821 9056", "8791");
        balances.put("1001 5632 9821 9056", 2500.0);

        accountNumbers.put("1002 6723 0947 2981", "5201");
        balances.put("1002 6723 0947 2981", 1500.0);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the ATM");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                if (login(scanner)) {
                    showMenu(scanner);
                }
            } else if (choice == 2) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        if (accountNumbers.containsKey(accountNumber) && accountNumbers.get(accountNumber).equals(pin)) {
            currentAccountNumber = accountNumber;
            System.out.println("Login successful!\n");
            return true;
        } else {
            System.out.println("Invalid account number or PIN. Please try again.");
            return false;
        }
    }

    private static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("ATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Log out\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    System.out.println("\nLogging out!");
                    currentAccountNumber = null;
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("\nCurrent balance: $" + balances.get(currentAccountNumber));
    }

    private static void deposit(Scanner scanner) {
        System.out.print("\nEnter deposit amount: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            double newBalance = balances.get(currentAccountNumber) + amount;
            balances.put(currentAccountNumber, newBalance);
            System.out.println("Deposited $" + amount + "\nNew balance: $" + newBalance);
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("\nEnter withdrawal amount: ");
        double amount = scanner.nextDouble();
        double currentBalance = balances.get(currentAccountNumber);

        if (amount > 0 && amount <= currentBalance) {
            double newBalance = currentBalance - amount;
            balances.put(currentAccountNumber, newBalance);
            System.out.println("Withdrew $" + amount + "\nNew balance: $" + newBalance);
        } else if (amount > currentBalance) {
            System.out.println("Insufficient funds. Please try again.");
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }
}
