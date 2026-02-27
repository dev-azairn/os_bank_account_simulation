
import java.util.LinkedList;
import java.util.Queue;

// File: Transaction.java
// Author: Nattanita
// Description: Manages a thread-safe queue of bank transactions, processing transfers asynchronously.

public class Transaction{
    public static Queue<Transaction> queue = new LinkedList<>();
    public enum State {PENDING, EXECUTE, FINISH, FAILED};
    private static boolean isQueueRunning = false;
    private static Thread executeThread;

    private BankAccount from;
    private BankAccount to;
    private double amount;
    private boolean isValid;
    private State state;
    

    public Transaction(BankAccount from, BankAccount to, double amount, boolean isValid) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.isValid = isValid;
        this.state = State.PENDING;
    }

    // create thread to run transaction, run to wait the transaction until new transaction come
   public static void processTransactionQueue() {
        while (isQueueRunning) {
            Transaction currentTransaction = null;

            synchronized (queue) {
                if (!queue.isEmpty()) {
                    currentTransaction = queue.poll();
                } else {
                    // If queue is empty, stop the thread gracefully
                    endProcessTransaction();
                }
            }

            if (currentTransaction != null && currentTransaction.isValid) {
                currentTransaction.state = State.EXECUTE;
                
                // Logic: Attempt to withdraw from sender and deposit to receiver
                if (currentTransaction.from.withdraw(currentTransaction.amount)) {
                    currentTransaction.to.deposit(currentTransaction.amount);
                    currentTransaction.state = State.FINISH;
                } else {
                    currentTransaction.state = State.FAILED;
                }
            }
        }
    }

    // create the new Thread for executing the transaction
   public static void pushTransactionQueue(Transaction transaction) {
        synchronized (queue) {
            queue.add(transaction);
        }

        // If the thread isn't running, start it
        if (!isQueueRunning) {
            isQueueRunning = true;
            executeThread = new Thread(Transaction::processTransactionQueue);
            executeThread.start();
        }
    }

    //
    public static void endProcessTransaction()
    {
        isQueueRunning = false;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + from.getAccountNo() +
                ", to=" + to.getAccountNo() +
                ", amount=" + amount +
                ", state=" + state +
                '}';
    }
}
