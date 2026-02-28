// File:BankAccount
// Author: Witthayut Phicharanan 6788086
// Description:
import java.util.ArrayList;

public abstract class BankAccount implements Transferable 
{
    private User accountOwner;
    private String accountNo;
    private double balance;
    private String accountType;
    private boolean isJoint;
    private ArrayList<User> jointUser;

    // Task 1: Implement the constructor
    // ------------------------------------------
    
    
    public BankAccount(User accountOwner, String accountNo, double initBalance, String accountType) 
    {
        System.out.println("[LOG] BankAccount constructor (User) called");
        this.accountOwner = accountOwner;
        this.accountNo = accountNo;
        this.balance = initBalance;
        this.accountType = accountType;
        this.isJoint = false;
        this.jointUser = new ArrayList<>();
    }

    public BankAccount(String citizenId, String name, String telephoneNo, String address, String accountNo, double initBalance, String accountType) 
    {
        System.out.println("[LOG] BankAccount constructor (String) called");
        this.accountOwner = new User(citizenId, name, telephoneNo, address);
        this.accountNo = accountNo;
        this.balance = initBalance;
        this.accountType = accountType;
        this.isJoint = false;
        this.jointUser = new ArrayList<>();
    }
    // ------------------------------------------

    // Task 2: Implement method setJointAccount
    // TO DO: if already joint account, cannot set the joint account
    //        if not joint account, set joint account and add joint user
    public boolean setJointAccount(boolean isJoint, ArrayList<User> jointList)
    {
        System.out.println("[LOG] setJointAccount() method called");
        if (this.isJoint) return false;
        if (!isJoint || jointList == null || jointList.isEmpty()) return false;
        this.isJoint = true;
        this.jointUser = new ArrayList<>(jointList);
        return true;
    }
    // -------------------------------------------
    
    protected abstract boolean validate(double amount);
    public abstract void earnInterest();

    // Task 3: implement deposit by add money, validation needed
    @Override
    public boolean deposit(double amount) 
    {
        System.out.println("[LOG] deposit() method called with amount: " + amount);
        // implement here!
        if (!validate(amount)) return false;
        
        // Assume that validation is finished!
        updateBalance(amount);
        return true;
    }

    // Task 4: implement withdraw by decrease money, validation needed
    @Override
    public boolean withdraw(double amount) 
    {
        System.out.println("[LOG] withdraw() method called with amount: " + amount);
        if (!validate(amount)) 
            {
            return false;
        }
        updateBalance(-amount);
        return true;
    }

    // Task 5: implement getBalance
    @Override
    public double getBalance() 
    {
        System.out.println("[LOG] getBalance() method called");
        return balance;
    }

    protected void updateBalance(double amount)
    {
        this.balance -= amount;
    }
    protected User getAccountOwner()
    {
        System.out.println("[LOG] getAccountOwner() method called");
        return this.accountOwner;
    }

    protected String getAccountNo() 
    {
        return accountNo;
    }

    @Override
    public String toString() 
    {
        System.out.println("[LOG] toString() method called in BankAccount");
        return "BankAccount{" +
                "owner=" + accountOwner.getName() +
                ", accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", isJoint=" + isJoint +
                '}';
    }
}
