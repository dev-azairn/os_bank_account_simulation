// File: FixedDepositAccount
// Author: Khomgrit Kongjaroen
// Description: Fixed amount on how many time can the user deposite their account

import java.util.Date;

public class FixedDepositAccount extends BankAccount {

    private double interestRate;
    private Date endDate;
    private boolean interestPaid;

    public FixedDepositAccount(User accoutOwner, String accountNo, double initBalance,  double interestRate, Date endDate) {
        super(accoutOwner, accountNo, initBalance, "FixedDepositAccount");
        System.out.println("[LOG] FixedDepositAccount constructor (User) called");
        this.interestRate = interestRate;
        this.endDate = endDate;
    }

    public FixedDepositAccount(String citizenId, String name, String telephoneNo, String address, String accountNo, double initBalance, double interestRate, Date endDate) {
        super(citizenId, name, telephoneNo, address, accountNo, initBalance, "FixedDepositAccount");
        System.out.println("[LOG] FixedDepositAccount constructor (String) called");
        this.interestRate = interestRate;
        this.endDate = endDate;
    }

    // override all transactional method to be real life transaction
    @Override
    public boolean deposit(double amount) {
        System.out.println("[LOG] deposit() method called in FixedDepositAccount with amount: " + amount);
        // implement here!
        System.out.println("Cannot deposit into Fixed Deposit account.");
        return false;
    }

    @Override
    public boolean withdraw(double amount) {
        System.out.println("[LOG] withdraw() method called in FixedDepositAccount with amount: " + amount);
        // implement here!
        Date today = new Date();

        if (today.before(endDate)) {
            System.out.println("Cannot withdraw before maturity date.");
            return false;
        }

        return super.withdraw(amount);
    }
    
    // force recursive
    @Override
    public double getBalance() {
        System.out.println("[LOG] getBalance() method called in FixedDepositAccount");
        if (validate()) { 
            return super.getBalance();
        }
        return 0;
    }

    @Override
    public boolean validate(double amount) {
        System.out.println("[LOG] validate(double) method called in FixedDepositAccount with amount: " + amount);
        return amount < this.getBalance() && endDate.after(new Date());
    }

    // earn interest at end date and no more deposit
    @Override
    public void earnInterest() {
        System.out.println("[LOG] earnInterest() method called in FixedDepositAccount");
        Date today = new Date();

        if (!today.before(endDate) && !interestPaid) {

            double interest = getBalance() * interestRate;
            super.deposit(interest);
            interestPaid = true;

            System.out.println("Interest added to Fixed Deposit account.");
        }
    }

    public boolean validate()
    {
        System.out.println("[LOG] validate() method called in FixedDepositAccount");
        return this.getBalance() >= 500;
    }

    @Override
    public String toString() {
        System.out.println("[LOG] toString() method called in FixedDepositAccount");
        return super.toString() + " [Type: Fixed Deposit, Interest Rate: " + (interestRate * 100) + 
               "%, End Date: " + endDate + ", Interest Paid: " + interestPaid + "]";
    }
}
