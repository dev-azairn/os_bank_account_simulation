// File: SavinngAccount
// Author: Khomgrit Kongjaroen
// Description: For user that wants to save money

public class SavingAccount extends BankAccount {

    private static double interestRate = 0.02; // 2% default

    public SavingAccount(User accoutOwner, String accountNo, double initBalance, String accountType) {
        super(accoutOwner, accountNo, initBalance, "SavingAccount");
    }
    
    public SavingAccount(String citizenId, String name, String telephoneNo, String address, User accoutOwner,
            String accountNo, double initBalance, String accountType) {
        super(citizenId, name, telephoneNo, address, accoutOwner, accountNo, initBalance, "SavingAccount");
    }

    // Getter & Setter for interest rate
    public static void setInterestRate(double rate) {
        if (rate > 0) {
            interestRate = rate;
        }
    }

    public static double getInterestRate() {
        return interestRate;
    }

    // Implement Validation according to account type
    @Override
    public boolean validate() {
        return getBalance() >= 500
    }

    @Override
    public void earnInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

}
