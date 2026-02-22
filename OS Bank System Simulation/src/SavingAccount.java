// File:
// Author:
// Description:

public class SavingAccount extends BankAccount {

    

    public SavingAccount(User accoutOwner, String accountNo, double initBalance, String accountType) {
        super(accoutOwner, accountNo, initBalance, "SavingAccount");
    }

    
    public SavingAccount(String citizenId, String name, String telephoneNo, String address, User accoutOwner,
            String accountNo, double initBalance, String accountType) {
        super(citizenId, name, telephoneNo, address, accoutOwner, accountNo, initBalance, "SavingAccount");
    }

    // Implement Validation according to account type
    @Override
    public boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void earnInterest() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
