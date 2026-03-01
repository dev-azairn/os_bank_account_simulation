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
        if (this.isJoint) {
            System.out.println("[LOG] setJointAccount() method finished");
            return false;
        }
        if (!isJoint || jointList == null || jointList.isEmpty()) {
            System.out.println("[LOG] setJointAccount() method finished");
            return false;
        }
        this.isJoint = true;
        this.jointUser = new ArrayList<>(jointList);
        System.out.println("[LOG] setJointAccount() method finished");
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
        if (!validate(amount)) {
            System.out.println("[LOG] deposit() method finished");
            return false;
        }
        
        // Assume that validation is finished!
        updateBalance(amount);
        System.out.println("[LOG] deposit() method finished");
        return true;
    }

    // Task 4: implement withdraw by decrease money, validation needed
    @Override
    public boolean withdraw(double amount) 
    {
        System.out.println("[LOG] withdraw() method called with amount: " + amount);
        if (!validate(amount)) 
            {
            System.out.println("[LOG] withdraw() method finished");
            return false;
        }
        updateBalance(-amount);
        System.out.println("[LOG] withdraw() method finished");
        return true;
    }

    // Task 5: implement getBalance
    @Override
    public double getBalance() 
    {
        System.out.println("[LOG] getBalance() method called");
        double balance = this.balance;
        System.out.println("[LOG] getBalance() method finished");
        return balance;
    }

    protected void updateBalance(double amount)
    {
        System.out.println("[LOG] updateBalance() method called");
        this.balance += amount;
        System.out.println("[LOG] updateBalance() method called");
    }
    protected User getAccountOwner()
    {
        System.out.println("[LOG] getAccountOwner() method called");
        System.out.println("[LOG] getAccountOwner() method finished");
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
        String result = "BankAccount{" +
                "owner=" + accountOwner.getName() +
                ", accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", isJoint=" + isJoint +
                '}';
        System.out.println("[LOG] toString() method finished");
        return result;
    }
}
