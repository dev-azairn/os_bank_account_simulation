// File:BankAccount
// Author: Witthayut Phicharanan 6788086
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
    
    
    public BankAccount(User accountOwner, String accountNo,
                       double initBalance, String accountType) {

        this.accountOwner = accountOwner;
        this.accountNo = accountNo;
        this.balance = initBalance;
        this.accountType = accountType;
        this.isJoint = false;
        this.jointUser = new ArrayList<>();
    }

    public BankAccount(String citizenId, String name,
                       String telephoneNo, String address,
                       String accountNo, double initBalance,
                       String accountType) {

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
        if (this.isJoint) {
            return false;
        }

        // ต้องตั้งเป็น true และมีรายชื่อ
        if (!isJoint || jointList == null || jointList.isEmpty()) {
            return false;
        }

        this.isJoint = true;
        this.jointUser = new ArrayList<>(jointList);

        return true;
        }
    // -------------------------------------------
    
    protected abstract boolean validate();
    protected abstract double getAmount();
    public abstract void earnInterest();

    // Task 3: implement deposit by add money, validation needed
    @Override
    public boolean deposit() {
        // implement here!
        if (!validate()) {
            return false;
        }

        // สมมติ validate ตรวจจำนวนเงินเรียบร้อยแล้ว
        balance += getAmount();

        return true;
    }

    // Task 4: implement withdraw by decrease money, validation needed
    @Override
    public boolean withdraw() {
    if (!validate()) {
        return false;
    }

    double amount = getAmount(); 

    if (amount > balance) {
        return false;
    }

    balance -= amount;

    return true;
}

    // Task 5: implement getBalance to getBalance after validation
    @Override
    public double getBalance() {
        if (!validate()) {
            return -1;   // validation ไม่ผ่าน
        }

        return balance;
    }
    
}
