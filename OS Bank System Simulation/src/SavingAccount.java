// File: SavinngAccount
// Author: Khomgrit Kongjaroen
// Description: For user that wants to save money

public class SavingAccount extends BankAccount 
{
    private static double interestRate = 0.02; // 2% default

    public SavingAccount(User accoutOwner, String accountNo, double initBalance) 
    {
        super(accoutOwner, accountNo, initBalance, "SavingAccount");
        System.out.println("[LOG] SavingAccount constructor (User) called");
        System.out.println("[LOG] SavingAccount constructor (User) finished");
    }
    
    public SavingAccount(String citizenId, String name, String telephoneNo, String address, String accountNo, double initBalance) 
    {
        super(citizenId, name, telephoneNo, address, accountNo, initBalance, "SavingAccount");
        System.out.println("[LOG] SavingAccount constructor (String) called");     
        System.out.println("[LOG] SavingAccount constructor (String) finished");     
    }

    // Getter & Setter for interest rate
    public static void setInterestRate(double rate, String adminUsername, String adminPassword) 
    {
        System.out.println("[LOG] setInterestRate() method called in SavingAccount with rate: " + rate);
        System.out.println("[LOG] setInterestRate() method called with rate: " + rate);
        if(BankAdmin.validateAdmin(adminUsername, adminPassword))
            interestRate = rate;
        else
            System.out.println("Cannot Access!!!!");
        System.out.println("[LOG] setInterestRate() method finished");
    }

    public static double getInterestRate() 
    {
        System.out.println("[LOG] getInterestRate() method called in SavingAccount");
        System.out.println("[LOG] getInterestRate() method finished");
        return interestRate;
    }

    // Implement Validation according to account type
    @Override
    public boolean validate(double amount) 
    {
        System.out.println("[LOG] validate() method called in SavingAccount with amount: " + amount);
        boolean result = amount < 100000;
        System.out.println("[LOG] validate() method finished");
        return result;
    }

    @Override
    public void earnInterest() 
    {
        System.out.println("[LOG] earnInterest() method called in SavingAccount");
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("[LOG] earnInterest() method finished");
    }

    @Override
    public String toString() 
    {
        System.out.println("[LOG] toString() method called in SavingAccount");
        String result = super.toString() + " [Type: Saving Account, Interest Rate: " + (interestRate * 100) + "%]";
        System.out.println("[LOG] toString() method finished");
        return result;
    }
}
