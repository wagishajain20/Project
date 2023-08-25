import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> users;
    private static User currentUser;
    private static Scanner scanner;

    public static void main(String[] args) {
        users = new ArrayList<>();
        users.add(new User("1234", "5678")); // Sample user
        
        scanner = new Scanner(System.in);
        
        // Prompt for user ID and PIN
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        
        // Authenticate user
        boolean authenticated = authenticateUser(userID, pin);
        if (authenticated) {
            System.out.println("Authentication successful!");
            showMenu();
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
        
        scanner.close();
    }
    
    private static boolean authenticateUser(String userID, String pin) {
        for (User user : users) {
            if (user.getUserID().equals(userID) && user.getPin().equals(pin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    
    private static void showMenu() {
        boolean quit = false;
        
        while (!quit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            switch (choice) {
                case 1:
                    showTransactionHistory();
                    break;
                case 2:
                    performWithdrawal();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    private static void showTransactionHistory() {
        List<Transaction> transactionHistory = currentUser.getTransactionHistory();
        
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history.");
        } else {
            System.out.println("Transaction History:");
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction.toString());
            }
        }
    }
    
    private static void performWithdrawal() {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        
        currentUser.withdraw(amount);
    }
    
    private static void performDeposit() {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        
        currentUser.deposit(amount);
    }
    
    private static void performTransfer() {
        System.out.print("Enter recipient's user ID: ");
        String recipientID = scanner.next();
        
        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();
        
        currentUser.transfer(recipientID, amount);
    }
}

class User {
    private String userID;
    private String pin;
    private List<Transaction> transactionHistory;

    public User(String userID, String pin) {
        this.userID = userID;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public String getPin() {
        return pin;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            // Perform withdrawal logic
            Transaction transaction = new Transaction("Withdrawal", amount);
            transactionHistory.add(transaction);
            System.out.println("Withdrawal successful: $" + amount);
        } else {
            System.out.println("Invalid amount for withdrawal.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            // Perform deposit logic
            Transaction transaction = new Transaction("Deposit", amount);
            transactionHistory.add(transaction);
            System.out.println("Deposit successful: $" + amount);
        } else {
            System.out.println("Invalid amount for deposit.");
        }
    }

    public void transfer(String recipientID, double amount) {
        if (amount > 0) {
            // Perform transfer logic
            Transaction transaction = new Transaction("Transfer to " + recipientID, amount);
            transactionHistory.add(transaction);
            System.out.println("Transfer successful: $" + amount + " to " + recipientID);
        } else {
            System.out.println("Invalid amount for transfer.");
        }
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Amount: $" + amount;
    }
}
