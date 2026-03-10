
import java.util.ArrayList;
import java.util.Scanner;

// Bank Account Class
class BankAccount {

    int accountNumber;
    String name;
    double balance;
    int pin;

    ArrayList<String> transactions = new ArrayList<>();

    BankAccount(int accountNumber, String name, double balance, int pin) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        this.pin = pin;

        transactions.add("Account created with balance: " + balance);
    }

    boolean verifyPin(int enteredPin) {
        return this.pin == enteredPin;
    }

    void deposit(double amount) {
        balance += amount;
        transactions.add("Deposited: " + amount);
        System.out.println("Amount Deposited Successfully!");
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add("Withdrawn: " + amount);
            System.out.println("Amount Withdrawn Successfully!");
        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    void display() {
        System.out.println("Account No: " + accountNumber
                + " | Name: " + name
                + " | Balance: " + balance);
    }

    void showTransactions() {

        System.out.println("\nTransaction History:");

        if (transactions.isEmpty()) {
            System.out.println("No Transactions Yet");
        } else {
            for (String t : transactions) {
                System.out.println(t);
            }
        }
    }
}

// Main Class
public class BankManagementSystem {

    static ArrayList<BankAccount> accounts = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static BankAccount findAccount(int accNo) {

        for (BankAccount acc : accounts) {
            if (acc.accountNumber == accNo) {
                return acc;
            }
        }

        return null;
    }

    static void createAccount() {

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.print("Set 4-digit PIN: ");
        int pin = sc.nextInt();

        accounts.add(new BankAccount(accNo, name, balance, pin));

        System.out.println("Account Created Successfully!");
    }

    static void deleteAccount() {

        System.out.print("Enter Account Number to Delete: ");
        int accNo = sc.nextInt();

        BankAccount acc = findAccount(accNo);

        if (acc != null) {

            System.out.print("Enter PIN: ");
            int pin = sc.nextInt();

            if (acc.verifyPin(pin)) {

                accounts.remove(acc);
                System.out.println("Account Deleted Successfully!");

            } else {
                System.out.println("Incorrect PIN!");
            }

        } else {
            System.out.println("Account Not Found!");
        }
    }

    static void transferMoney() {

        System.out.print("Enter Sender Account Number: ");
        int fromAcc = sc.nextInt();

        BankAccount sender = findAccount(fromAcc);

        if (sender == null) {
            System.out.println("Sender Account Not Found!");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();

        if (!sender.verifyPin(pin)) {
            System.out.println("Incorrect PIN!");
            return;
        }

        System.out.print("Enter Receiver Account Number: ");
        int toAcc = sc.nextInt();

        BankAccount receiver = findAccount(toAcc);

        if (receiver == null) {
            System.out.println("Receiver Account Not Found!");
            return;
        }

        System.out.print("Enter Amount to Transfer: ");
        double amount = sc.nextDouble();

        if (sender.balance >= amount) {

            sender.balance -= amount;
            receiver.balance += amount;

            sender.transactions.add("Transferred " + amount + " to account " + toAcc);
            receiver.transactions.add("Received " + amount + " from account " + fromAcc);

            System.out.println("Transfer Successful!");

        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n------ MINI BANKING SYSTEM ------");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Transfer Money");
            System.out.println("7. Delete Account");
            System.out.println("8. Transaction History");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:

                    System.out.print("Enter Account Number: ");
                    int depAcc = sc.nextInt();

                    BankAccount depAccount = findAccount(depAcc);

                    if (depAccount != null) {

                        System.out.print("Enter PIN: ");
                        int pin = sc.nextInt();

                        if (depAccount.verifyPin(pin)) {

                            System.out.print("Enter Amount: ");
                            double amount = sc.nextDouble();

                            depAccount.deposit(amount);

                        } else {
                            System.out.println("Incorrect PIN!");
                        }

                    } else {
                        System.out.println("Account Not Found!");
                    }

                    break;

                case 3:

                    System.out.print("Enter Account Number: ");
                    int withAcc = sc.nextInt();

                    BankAccount withAccount = findAccount(withAcc);

                    if (withAccount != null) {

                        System.out.print("Enter PIN: ");
                        int pin = sc.nextInt();

                        if (withAccount.verifyPin(pin)) {

                            System.out.print("Enter Amount: ");
                            double amount = sc.nextDouble();

                            withAccount.withdraw(amount);

                        } else {
                            System.out.println("Incorrect PIN!");
                        }

                    } else {
                        System.out.println("Account Not Found!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Account Number: ");
                    int balAcc = sc.nextInt();

                    BankAccount balAccount = findAccount(balAcc);

                    if (balAccount != null) {

                        System.out.print("Enter PIN: ");
                        int pin = sc.nextInt();

                        if (balAccount.verifyPin(pin)) {

                            balAccount.display();

                        } else {
                            System.out.println("Incorrect PIN!");
                        }

                    } else {
                        System.out.println("Account Not Found!");
                    }

                    break;

                case 5:

                    if (accounts.isEmpty()) {
                        System.out.println("No Accounts Available!");
                    } else {
                        for (BankAccount acc : accounts) {
                            acc.display();
                        }
                    }

                    break;

                case 6:
                    transferMoney();
                    break;

                case 7:
                    deleteAccount();
                    break;

                case 8:

                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();

                    BankAccount acc = findAccount(accNo);

                    if (acc != null) {

                        System.out.print("Enter PIN: ");
                        int pin = sc.nextInt();

                        if (acc.verifyPin(pin)) {

                            acc.showTransactions();

                        } else {
                            System.out.println("Incorrect PIN!");
                        }

                    } else {
                        System.out.println("Account Not Found!");
                    }

                    break;

                case 9:
                    System.out.println("Exiting System...");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 9);

    }
}
