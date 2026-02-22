
import java.util.LinkedList;
import java.util.Queue;

// File:
// Author:
// Description:

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
    public static void processTransactionQueue()
    {

    }

    // create the new Thread for executing the transaction
    public static void pushTransactionQueue()
    {

    }

    //
    public static void endProcessTransaction()
    {

    }
}
