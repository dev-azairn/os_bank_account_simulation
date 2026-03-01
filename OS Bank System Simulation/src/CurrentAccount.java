// File: CurrentAccount.java
// Author: Nattanita
// Description: Implementation of CurrentAccount extending BankAccount with interest and validation logic.

public class CurrentAccount extends BankAccount {
    public static double interestRate;


    // Task 1: Set Interest Rate
    public static void setInterestRate(double rate, String adminUsername, String adminPassword)
    {
        System.out.println("[LOG] setInterestRate() method called with rate: " + rate);
        if(BankAdmin.validateAdmin(adminUsername, adminPassword))
            interestRate = rate;
        else
            System.out.println("Cannot Access!!!!");
        System.out.println("[LOG] setInterestRate() method finished");
    }

    public CurrentAccount(String citizenId, String name, String telephoneNo, String address, String accountNo, double initBalance) 
    {
        super(citizenId, name, telephoneNo, address, accountNo, initBalance, "CurrentAccount");
        System.out.println("[LOG] CurrentAccount constructor (String) called");
        System.out.println("[LOG] CurrentAccount constructor (String) finished");
    }

    public CurrentAccount( User accoutOwner, String accountNo, double initBalance) {
        super(accoutOwner, accountNo, initBalance, "Current Account");
        System.out.println("[LOG] CurrentAccount constructor (User) called");
        System.out.println("[LOG] CurrentAccount constructor (User) finished");
    }

    @Override
    public double getBalance()
    {
        System.out.println("[LOG] getBalance() method called in CurrentAccount");
        double balance = super.getBalance();
        System.out.println("[LOG] getBalance() method finished in CurrentAccount");
        return balance;
    }
    
    // Task 1: validate according to real life current account
    @Override
    public boolean validate(double amount) 
    {
        System.out.println("[LOG] validate() method called with amount: " + amount);
        // Checking if balance is at least 0 and the account owner is assigned
        boolean result = amount < 50000;
        System.out.println("[LOG] validate() method finished");
        return result;
    }

   

    // Task 2: Earn interest by adding deposit money by admin
   @Override
    public void earnInterest() 
    {
        System.out.println("[LOG] earnInterest() method called in CurrentAccount");
        // Interest earned = current balance * interest rate
        double interestEarned = this.getBalance() * interestRate;
        if (interestEarned > 0) this.deposit(interestEarned);
        System.out.println("[LOG] earnInterest() method finished");
    }
    // tostring
    @Override
    public String toString() 
    {
        System.out.println("[LOG] toString() method called in CurrentAccount");
        String result = super.toString() + " [Type: Current Account, Interest Rate: " + (interestRate * 100) + "%]";
        System.out.println("[LOG] toString() method finished");
        return result;
    }
}
