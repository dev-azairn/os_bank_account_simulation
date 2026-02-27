// File: CurrentAccount.java
// Author: Nattanita
// Description: Implementation of CurrentAccount extending BankAccount with interest and validation logic.

public class CurrentAccount extends BankAccount {
    public static double interestRate;


    // Task 1: Set Interest Rate
    public static void setInterestRate(double interestRate)
    {
        interestRate = rate;
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
        // Checking if balance is at least 0 and the account owner is assigned
        return this.getBalance() >= 0 && this.getAccountOwner() != null;
    }

    // Task 2: Earn interest by adding deposit money by admin
   @Override
    public void earnInterest() {
        // Interest earned = current balance * interest rate
        double interestEarned = this.getBalance() * interestRate;
        if (interestEarned > 0) {
            this.deposit(interestEarned);
        }
    }
    // tostring
    @Override
    public String toString() {
        return super.toString() + " [Type: Current Account, Interest Rate: " + (interestRate * 100) + "%]";
    }
}
