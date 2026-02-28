//File: BankTester.java
//Author: Piyapong Pongruang
//Description: To demonstate following 3 tasks

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class BankTester 
{

    // pre-defined users for account management
    public static final User user1 = new User("1234567890", "John Doe", "0812345678", "123 Main Street");
    public static final User user2 = new User("0987654321", "Jane Doe", "0899999999", "125 Main House");
    public static final User user3 = new User("1122334455", "Alice Smith", "0876543210", "789 Second Ave");

    // aggregate list for easy iteration
    public static Queue<Transaction> queue = new LinkedList<>();
    private static boolean isQueueRunning = false;
    private static double todayInterest = 0.02;

    public static void main(String[] args) throws Exception 
    {
        System.out.println("[LOG] main() method started");
        
        stackPointerAnalysis();
        heapAllocator();
        passingParameter();
        realLifeShowcase();
        System.out.println("[LOG] main() method finished");
    }
    

    // Task 1: Stack Pointer Analysis
    static void stackPointerAnalysis()
    {
        System.out.println("[LOG] stackPointerAnalysis() method started");
        memory();
        //stackOverflowAddingTransaction();
        System.out.println("[LOG] stackPointerAnalysis() method finished");
    }   

    // Task 2: Heap Allocator
    static void heapAllocator()
    {
        System.out.println("[LOG] heapAllocator() method started");
        CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
        FixedDepositAccount account2 = new FixedDepositAccount(user1, "0123456789", 1500.00, todayInterest, new Date(2026, 06, 01));
        SavingAccount account3 = new SavingAccount(user1, "7894561230", todayInterest);
        System.out.println("---- Print Result ----");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println("----------------------");
        System.out.println("[LOG] heapAllocator() method finished");
    }


    // Task 3: Parameter Passing Analysis
    static void passingParameter()
    {
        System.out.println("[LOG] passingParameter() method called");
        CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
        SavingAccount account2 = new SavingAccount(user1, "7894561230", 5000);
        CurrentAccount account3 = new CurrentAccount(user2, "55555555555", 15000.00);
        SavingAccount account4 = new SavingAccount(user2, "4444444444", 5000);

        // Passing primitive value
        Transaction transaction1 = new Transaction(account1, account2, 500);
        double transactionAmount = -500;
        System.out.println("Transaction Amount Before Passing: " + transactionAmount);
        transaction1.process(transactionAmount);
        System.out.println("Transaction Amount After Passing: " + transactionAmount);
        System.out.println("Transaction Amount is not changed");


        // Passing object value
        Transaction transaction2 = new Transaction(account3, account4, 500);
        System.out.println("Account1 Balance: " + account1.getBalance());
        System.out.println("Account2 Balance: " + account2.getBalance());
        // Transaction amount = 500, but change the transaction account to account1 --> account2
        transaction2.process(account1, account2);
        System.out.println("Account1 Balance: " + account1.getBalance());
        System.out.println("Account2 Balance: " + account2.getBalance());
        System.out.println("Amount is changed!!!!!");
    }


    // Task 4: Transaction Showcase - Thread, Lock and Concurrency
    static void realLifeShowcase()
    {
        CurrentAccount account1 = new CurrentAccount(user1, "ACC1001", 20000.00);
        SavingAccount account2 = new SavingAccount(user2, "ACC2001", 15000.00);
        CurrentAccount account3 = new CurrentAccount(user3, "ACC3001", 10000.00);
        
        // 60 Transaction push in the queue
        for (int i = 0; i < 20; i++) 
        {
            pushTransactionQueue(new Transaction(account1, account2, 100 + i * 10));
            pushTransactionQueue(new Transaction(account2, account3, 80 + i * 5));
            pushTransactionQueue(new Transaction(account3, account1, 60 + i * 3));
        }

        // Initial thread for process transaction
        Thread thread1 = new Thread(() -> processTransactionQueue());
        Thread thread2 = new Thread(() -> processTransactionQueue());


        // Start process
        thread1.start();
        thread2.start();

        try 
        {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        System.out.println("---- FINAL BALANCES ----");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println("------------------------");
        System.out.println("---- 1 Years Then ----");
        account1.earnInterest();
        account2.earnInterest();
        account3.earnInterest();
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println("------------------------");


        System.out.println("[LOG] realLifeShowcase() method finished");
    }

    static void memory()
    {
        System.out.println("[LOG] memory() method started");
        CurrentAccount.setInterestRate(0.05, "admin", "1234");
        CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
        SavingAccount account2 = new SavingAccount(user1, "0123456789", 1500.00);
        Transaction transaction = new Transaction(account1, account2, 500);
        transaction.process();
        System.out.println("[LOG] memory() method finished");
    }

    // Helper function intentionally for StackOverflow which appear in getBalance and validation loop
    static void stackOverflowAddingTransaction()
    {
        try 
        {
            System.out.println("[LOG] stackOverflowAddingTransaction() method started");
            CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
            FixedDepositAccount account2 = new FixedDepositAccount(user1, "0123456789", 1500.00, todayInterest, new Date(2026, 06, 01));
            Transaction transaction = new Transaction(account2, account1, 500);
            transaction.process();
        } catch (StackOverflowError error)
        {
            System.out.println("Stack Overflow!!!!!!");
        }
        System.out.println("[LOG] stackOverflowAddingTransaction() method finished");
    }

    public static void processTransactionQueue() 
    {
        System.out.println("[LOG] processTransactionQueue() method started");
        isQueueRunning = true;
        while (isQueueRunning) 
        {
            System.out.println("Current Thread: " + Thread.currentThread().getName());
            Transaction currentTransaction = null;
            if (!queue.isEmpty()) currentTransaction = queue.poll();
            else 
                // If queue is empty, stop the thread
                endProcessTransaction();
            if (currentTransaction != null) currentTransaction.process();
        }
    }

    // push transaction to the queue
    public static void pushTransactionQueue(Transaction transaction) 
    {
        System.out.println("[LOG] pushTransactionQueue() method called");
        queue.add(transaction);
    }

    // force to end the queue
    public static void endProcessTransaction()
    {
        System.out.println("[LOG] endProcessTransaction() method called");
        isQueueRunning = false;
    }
}
