
import java.util.Date;

// File:
// Author:
// Description:


public class FixedDepositAccount extends BankAccount {

    private double interestRate;
    private Date endDate;

    public FixedDepositAccount(User accoutOwner, String accountNo, double initBalance, String accountType, double interestRate, Date endDate) {
        super(accoutOwner, accountNo, initBalance, "FixedDepositAccount");
        this.interestRate = interestRate;
    }

    public FixedDepositAccount(String citizenId, String name, String telephoneNo, String address, User accoutOwner, String accountNo, double initBalance, String accountType, double interestRate, Date endDate) {
        super(citizenId, name, telephoneNo, address, accoutOwner, accountNo, initBalance, "FixedDepositAccount");
        this.interestRate = interestRate;
    }

    // override all transactional method to be real life transaction
    @Override
    public boolean deposit() {
        // implement here!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean withdraw() {
        // implement here!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public double getBalance() {
        // implement here!
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }

    @Override
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // earn interest at end date and no more deposit
    @Override
    public void earnInterest() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
