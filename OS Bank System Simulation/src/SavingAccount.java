// File: SavinngAccount
// Author: Khomgrit Kongjaroen
// Description: For user that wants to save money

public class SavingAccount extends BankAccount {

    private static double interestRate = 0.02; // 2% default

    public SavingAccount(User accoutOwner, String accountNo, double initBalance) {
        super(accoutOwner, accountNo, initBalance, "SavingAccount");
        System.out.println("[LOG] SavingAccount constructor (User) called");
    }
    
    public SavingAccount(String citizenId, String name, String telephoneNo, String address,
            String accountNo, double initBalance) {
                super(citizenId, name, telephoneNo, address, accountNo, initBalance, "SavingAccount");
        System.out.println("[LOG] SavingAccount constructor (String) called");
        
    }

    // Getter & Setter for interest rate
    public static void setInterestRate(double rate) {
        System.out.println("[LOG] setInterestRate() method called in SavingAccount with rate: " + rate);
        if (rate > 0) {
            interestRate = rate;
        }
    }

    public static double getInterestRate() {
        System.out.println("[LOG] getInterestRate() method called in SavingAccount");
        return interestRate;
    }

    // Implement Validation according to account type
    @Override
    public boolean validate(double amount) {
        System.out.println("[LOG] validate() method called in SavingAccount with amount: " + amount);
        return amount < this.getBalance();
    }

    @Override
    public void earnInterest() {
        System.out.println("[LOG] earnInterest() method called in SavingAccount");
        double interest = getBalance() * interestRate;
        deposit(interest);
    }

    @Override
    public String toString() {
        System.out.println("[LOG] toString() method called in SavingAccount");
        return super.toString() + " [Type: Saving Account, Interest Rate: " + (interestRate * 100) + "%]";
    }
}
