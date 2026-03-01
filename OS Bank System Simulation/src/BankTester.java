//File: BankTester.java
//Author: Piyapong Pongruang, Natthanita
//Description: To demonstate following 3 tasks
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BankTester 
{

    // pre-defined users for account management
    public static final User user1 = new User("1234567890", "John Doe", "0812345678", "123 Main Street");
    public static final User user2 = new User("0987654321", "Jane Doe", "0899999999", "125 Main House");
    public static final User user3 = new User("1122334455", "Alice Smith", "0876543210", "789 Second Ave");

    // aggregate list for easy iteration
    
    public static BlockingQueue<Transaction> queue = new LinkedBlockingQueue<>();
    private static final double CAINTEREST = 0.02;
    private static final double FAINTEREST = 0.02;
    private static final double SAINTEREST = 0.02;

    public static void main(String[] args) throws Exception 
    {
        System.out.println("[LOG] main() method started");
        System.out.println("[LOG] LOADING - TODAY'S INTEREST.....");
        CurrentAccount.setInterestRate(CAINTEREST, "admin", "1234");
        SavingAccount.setInterestRate(SAINTEREST, "admin", "1234");
        // Scanner scanner = new Scanner(System.in);
        // int mode;
        // do {
        //     System.out.println("Welcome to Bank System Simulation");
        //     System.out.println("1: Stack Pointer Analysis - Tracing Stack Call");
        //     System.out.println("2: Stack Pointer Analysis - StackOverflow");
        //     System.out.println("3: Heap Allocation - Tracing Call Heap");
        //     System.out.println("4: Heap Allocation - Reassign Reference");
        //     System.out.println("5: Heap Allocation - Nullify Reference");
        //     System.out.println("6: Passing Primitive vs Reference Parameter");
        //     System.out.println("7: String Immuability");
        //     System.out.println("8: Scheduling and Concurrency Showcase");
        //     System.out.println("9: Exit");
        //     System.out.println("Select input: 1-9");
        //     mode = scanner.nextInt();
        //     switch (mode) {
        //         case 1 -> tracingCallStack();
        //         case 2 -> stackOverflowAddingTransaction();
        //         case 3 -> tracingCallHeap();
        //         case 4 -> reassignReference();
        //         case 5 -> nullifyReference();
        //         case 6 -> passingParameter();
        //         case 7 -> stringImmune();
        //         case 8 -> realLifeShowcase();
        //         case 9 -> exit();
        //         default -> System.out.println("Please type the availble input (1-9)!!!");
        //     }
        // } while (mode != 9);
        // scanner.close();
        // System.out.println("[LOG] main() method finished");

        // Uncomment the below function for testing individual

        // tracingCallStack();
        // stackOverflowAddingTransaction();
        // tracingCallHeap();
        // reassignReference();
        // nullifyReference();
        // passingParameter();
        stringImmune();
        // realLifeShowcase();
        // exit();
    }
    
    static void exit()
    {
        System.out.println("[LOG] exit() method started");
        System.out.println("Thank you for using Bank System Simulation");
        System.out.println("[LOG] exit() method finished");
    }

    // Task 1.1
    static void tracingCallStack()
    {
        System.out.println("[LOG] tracingCallStack() method started");
        CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
        SavingAccount account2 = new SavingAccount(user1, "0123456789", 1500.00);
        Transaction transaction = new Transaction(account1, account2, 500);
        transaction.process();
        System.out.println("[LOG] tracingCallStack() method finished");
    }

    // Task 1.2
    static void stackOverflowAddingTransaction()
    {
        try 
        {
            System.out.println("[LOG] stackOverflowAddingTransaction() method started");
            CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
            FixedDepositAccount account2 = new FixedDepositAccount(user1, "0123456789", 1500.00, FAINTEREST, new Date(2026, 06, 01));
            Transaction transaction = new Transaction(account2, account1, 500);
            transaction.process();
        } catch (StackOverflowError error)
        {
            error.printStackTrace();
        }
        System.out.println("[LOG] stackOverflowAddingTransaction() method finished");
    }

    // Task 2.1
    static void tracingCallHeap()
    {
        System.out.println("[LOG] tracingCallHeap() method started");
        CurrentAccount account1 = new CurrentAccount(user1, "1234567890", 15000.00);
        FixedDepositAccount account2 = new FixedDepositAccount(user1, "0123456789", 1500.00, FAINTEREST, new Date(2026, 06, 01));
        SavingAccount account3 = new SavingAccount(user1, "7894561230", 500);
        System.out.println("---- Print Result ----");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println("----------------------");
        System.out.println("[LOG] tracingCallHeap() method finished");
    }

    // Task 2.2
    static void reassignReference()
    {
        System.out.println("[LOG] reassignReference() method started");
        User user4 = new User("AB1111", "Yonezu Kenshi", "1234567890", "123 Main House");
        System.out.println("[LOG] Before reassigning");
        System.out.println(user4 + " " + System.identityHashCode(user4));
        user4 = user1;
        System.out.println("[LOG] After reassigning");
        System.out.println(user4 + " " + System.identityHashCode(user4));
        System.out.println("[LOG] reassignReference() method finished");
    }

    // Task 2.3
    static void nullifyReference()
    {
        System.out.println("[LOG] nullifyReference() method started");
        User user4 = new User("AB1111", "Yonezu Kenshi", "1234567890", "123 Main House");
        System.out.println(user4 + " " + System.identityHashCode(user4));
        user4 = null;
        System.out.println(user4 + " " + System.identityHashCode(user4));
        int tempNo = 50000;
        User[] user = new User[tempNo];
        System.out.println("[Log] Length = " + user.length);
        for (int i = 0; i < tempNo; i++) {
            user[i] = new User("AB" + i, "JB" + i, "151121215", i + " Main Street");
        }
        for (int i = 0; i < tempNo; i++) {
            user[i] = null;
        }
        System.gc();
        System.out.println("[LOG] nullifyReference() method finished");
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
        System.out.println("[LOG] passingParameter() method finished");
    }

    // Task 3.2
    static void stringImmune()
    {
        System.out.println("[LOG] stringImmune() method started");
        String name = user2.getName();
        String name2 = "Jane Doe";
        String name5 = new String("Jane Doe");
        System.out.println("[LOG] Same String");
        System.out.println(name + " " + System.identityHashCode(name));
        System.out.println(name2 + " " + System.identityHashCode(name2));
        System.out.println("[LOG] Concatnate name2");
        name2 += " (VIP)";
        System.out.println(name + " " + System.identityHashCode(name));
        System.out.println(name2 + " " + System.identityHashCode(name2));
        System.out.println(name5 + " " + System.identityHashCode(name5) + " [Note]" + " Using new");
        // StringBuilder (Mutable String)
        System.out.println("[LOG] Mutable String");
        StringBuilder name3 = new StringBuilder(user2.getName());
        StringBuilder name4 = name3;
        name4.append(" (VIP)");
        System.out.println(name3 + " " + System.identityHashCode(name3));
        System.out.println(name4 + " " + System.identityHashCode(name4));
        System.out.println("[LOG] Final Result");
        System.out.println(name + " " + System.identityHashCode(name));
        System.out.println(name2 + " " + System.identityHashCode(name2));
        System.out.println(name3 + " " + System.identityHashCode(name3));
        System.out.println(name4 + " " + System.identityHashCode(name4));
        System.out.println("[LOG] stringImmune() method finished");
    }

    // Task 4: Transaction Showcase - Thread, Lock and Concurrency
    static void realLifeShowcase()
    {
        System.out.println("[LOG] realLifeShowcase() method started");

        CurrentAccount account1 = new CurrentAccount(user1, "ACC1001", 20000.00);
        SavingAccount account2 = new SavingAccount(user2, "ACC2001", 15000.00);
        CurrentAccount account3 = new CurrentAccount(user3, "ACC3001", 10000.00);

        // Create thread pool with 2 workers
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Producer: Push 60 transactions
        for (int i = 0; i < 20; i++)
        {
            pushTransactionQueue(new Transaction(account1, account2, 100 + i * 10));
            pushTransactionQueue(new Transaction(account2, account3, 80 + i * 5));
            pushTransactionQueue(new Transaction(account3, account1, 60 + i * 3));
        }

        // Consumers
        Runnable worker = () -> {
            try 
            {
                while (true) {
                    Transaction transaction = queue.poll(1, TimeUnit.SECONDS);

                    if (transaction == null) {
                        break; // No more transactions
                    }

                    System.out.println("Processing by: " + Thread.currentThread().getName());
                    transaction.process();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        executor.execute(worker);
        executor.execute(worker);

        executor.shutdown();

        try 
        {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        System.out.println("---- FINAL BALANCES ----");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println("------------------------");

        System.out.println("[LOG] scheduleInterest() starte: 5 year simulation");

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Count how many simulated years have passed
        final int maxYears = 5; // simulate 5 years
        final AtomicInteger yearCounter = new AtomicInteger(0);

        Runnable interestTask = () -> {
            int currentYear = yearCounter.incrementAndGet();

            System.out.println("===== YEAR " + currentYear + " =====");

            account1.earnInterest();
            account2.earnInterest();
            account3.earnInterest();

            System.out.println(account1);
            System.out.println(account2);
            System.out.println(account3);
            System.out.println("========================");

            // Stop after reaching max years
            if (currentYear >= maxYears) {
                System.out.println("[LOG] Reached " + maxYears + " years. Stopping scheduler...");
                scheduler.shutdown();
            }
        };

        // Run every 5 seconds (simulate 1 year = 5 seconds)
        scheduler.scheduleAtFixedRate(interestTask,
                                    5,
                                    5,
                                    TimeUnit.SECONDS);

        try {
            scheduler.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("[LOG] realLifeShowcase() method finished");
    }

    

    // push transaction to the queue
    public static void pushTransactionQueue(Transaction transaction) 
    {
        System.out.println("[LOG] pushTransactionQueue() method called");
        try {
            queue.put(transaction);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[LOG] pushTransactionQueue() method finished");
    }
}
