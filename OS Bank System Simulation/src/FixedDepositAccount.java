import java.util.Date;

// File: FixedDepositAccount
// Author: Khomgrit Kongjaroen
// Description: Fixed amount on how many time can the user deposite their account


public class FixedDepositAccount extends BankAccount {

    private double interestRate;
    private Date endDate;

    public FixedDepositAccount(User accoutOwner, String accountNo, double initBalance, String accountType, double interestRate, Date endDate) {
        super(accoutOwner, accountNo, initBalance, "FixedDepositAccount");
        this.interestRate = interestRate;
        this.endDate = endDate;
    }

    public FixedDepositAccount(String citizenId, String name, String telephoneNo, String address, User accoutOwner, String accountNo, double initBalance, String accountType, double interestRate, Date endDate) {
        super(citizenId, name, telephoneNo, address, accoutOwner, accountNo, initBalance, "FixedDepositAccount");
        this.interestRate = interestRate;
        this.endDate = endDate;
    }

    // override all transactional method to be real life transaction
    @Override
    public boolean deposit() {
        // implement here!
        System.out.println("Cannot deposit into Fixed Deposit account.");
        return false;
    }

    @Override
    public boolean withdraw() {
        // implement here!
        Date today = new Date();

        if (today.before(endDate)) {
            System.out.println("Cannot withdraw before maturity date.");
            return false;
        }

        return super.withdraw(amount);
    }
    
    
    @Override
    public double getBalance() {
        // implement here!
        return super.getBalance();
    }

    @Override
    public boolean validate() {
        return getBalance() > 0 && endDate.after(new Date());
    }

    // earn interest at end date and no more deposit
    @Override
    public void earnInterest() {
        Date today = new Date();

        if (!today.before(endDate) && !interestPaid) {

            double interest = getBalance() * interestRate;
            super.deposit(interest);
            interestPaid = true;

            System.out.println("Interest added to Fixed Deposit account.");
        }
    }
}
