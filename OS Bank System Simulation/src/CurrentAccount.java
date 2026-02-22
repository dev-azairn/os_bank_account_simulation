// File:
// Author:
// Description:

public class CurrentAccount extends BankAccount {
    public static double interestRate;


    // Task 1: Set Interest Rate
    public static void setInterestRate(double interestRate)
    {
        
    }

    public CurrentAccount(String citizenId, String name, String telephoneNo, String address, User accoutOwner,
            String accountNo, double initBalance) {
        super(citizenId, name, telephoneNo, address, accoutOwner, accountNo, initBalance, "CurrentAccount");
    }

    public CurrentAccount( User accoutOwner, String accountNo, double initBalance) {
        super(accoutOwner, accountNo, initBalance, "Current Account");
       
    }

    // Task 1: validate according to real life current account
    @Override
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Task 2: Earn interest by adding deposit money by admin
    @Override
    public void earnInterest() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
