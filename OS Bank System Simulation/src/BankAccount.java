// File:
// Author:
// Description:
import java.util.ArrayList;

public abstract class BankAccount implements Transferable {

    private User accountOwner;
    private String accountNo;
    private double balance;
    private String accountType;
    private boolean isJoint;
    private ArrayList<User> jointUser;

    // Task 1: Implement the constructor
    // ------------------------------------------
    
    
    public BankAccount(User accoutOwner, String accountNo, double initBalance, String accountType)
    {

    }

    public BankAccount(String citizenId, String name, String telephoneNo, String address, User accoutOwner, 
        String accountNo, double initBalance, String accountType)
    {

    }
    // ------------------------------------------

    // Task 2: Implement method setJointAccount
    // TO DO: if already joint account, cannot set the joint account
    //        if not joint account, set joint account and add joint user
    public boolean setJointAccount(boolean isJoint, ArrayList<User> jointList)
    {
        return false;
    }
    // -------------------------------------------
    
    protected abstract boolean validate();

    public abstract void earnInterest();

    // Task 3: implement deposit by add money, validation needed
    @Override
    public boolean deposit() {
        // implement here!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Task 4: implement withdraw by decrease money, validation needed
    @Override
    public boolean withdraw() {
        // implement here!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    // Task 5: implement getBalance to getBalance after validation
    @Override
    public double getBalance() {
        // implement here!
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }
    
}
