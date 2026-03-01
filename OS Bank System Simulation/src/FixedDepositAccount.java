// File: FixedDepositAccount
// Author: Khomgrit Kongjaroen
// Description: Fixed amount on how many time can the user deposite their account

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
public class FixedDepositAccount extends BankAccount 
{

    private double interestRate;
    private Date endDate;
    private boolean interestPaid;
    final AtomicInteger counter = new AtomicInteger(0);

    public FixedDepositAccount(User accoutOwner, String accountNo, double initBalance,  double interestRate, Date endDate) 
    {
        super(accoutOwner, accountNo, initBalance, "FixedDepositAccount");
        System.out.println("[LOG] FixedDepositAccount constructor (User) called");
        this.interestRate = interestRate;
        this.endDate = endDate;
        System.out.println("[LOG] FixedDepositAccount constructor (User) finished");
    }

    public FixedDepositAccount(String citizenId, String name, String telephoneNo, String address, String accountNo, double initBalance, double interestRate, Date endDate) 
    {
        super(citizenId, name, telephoneNo, address, accountNo, initBalance, "FixedDepositAccount");
        System.out.println("[LOG] FixedDepositAccount constructor (String) called");
        this.interestRate = interestRate;
        this.endDate = endDate;
        System.out.println("[LOG] FixedDepositAccount constructor (String) finished");
    }

    // override all transactional method to be real life transaction
    @Override
    public boolean deposit(double amount) 
    {
        System.out.println("[LOG] deposit() method called in FixedDepositAccount with amount: " + amount);
        // implement here!
        System.out.println("Cannot deposit into Fixed Deposit account.");
        System.out.println("[LOG] deposit() method finished");
        return false;
    }

    @Override
    public boolean withdraw(double amount) 
    {
        System.out.println("[LOG] withdraw() method called in FixedDepositAccount with amount: " + amount);
        // implement here!
        Date today = new Date();

        if (today.before(endDate)) 
        {
            System.out.println("Cannot withdraw before maturity date.");
            System.out.println("[LOG] withdraw() method finished");
            return false;
        }

        boolean result = super.withdraw(amount);
        System.out.println("[LOG] withdraw() method finished");
        return result;
    }
    
    // force recursive
    @Override
    public double getBalance() 
    {
        System.out.println("[LOG] getBalance() method called in FixedDepositAccount");
        double balance;
        if (validate()) { 
            balance = super.getBalance();
        } else {
            balance = 0;
        }
        System.out.println("[LOG] getBalance() method finished");
        return balance;
    }

    @Override
    public boolean validate(double amount) 
    {
        System.out.println("[LOG] validate(double) method called in FixedDepositAccount with amount: " + amount);
        boolean result = amount < this.getBalance() && endDate.after(new Date());
        System.out.println("[LOG] validate(double) method finished");
        
        return result;
    }

    // earn interest at end date and no more deposit
    @Override
    public void earnInterest() 
    {
        System.out.println("[LOG] earnInterest() method called in FixedDepositAccount");
        Date today = new Date();

        if (!today.before(endDate) && !interestPaid) 
        {

            double interest = getBalance() * interestRate;
            super.deposit(interest);
            interestPaid = true;

            System.out.println("Interest added to Fixed Deposit account.");
        }
        System.out.println("[LOG] earnInterest() method finished");
    }

    public boolean validate()
    {
        System.out.println("[LOG] validate() method called in FixedDepositAccount");
        // Force stack overflow that is able to be unwinded.
        if(counter.incrementAndGet() == 5) throw new StackOverflowError("StackOverflow!!!!!");
        boolean result = this.getBalance() >= 500;
        System.out.println("[LOG] validate() method finished");
        return result;
    }

    @Override
    public String toString() {
        System.out.println("[LOG] toString() method called in FixedDepositAccount");
        String result = super.toString() + " [Type: Fixed Deposit, Interest Rate: " + (interestRate * 100) + 
               "%, End Date: " + endDate + ", Interest Paid: " + interestPaid + "]";
        System.out.println("[LOG] toString() method finished");
        return result;
    }
}
