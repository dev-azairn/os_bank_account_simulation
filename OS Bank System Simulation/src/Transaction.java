// Author: Nattanita
// Description: Record the transaction

public class Transaction
{
    public enum State {PENDING, EXECUTE, FINISH, FAILED};

    private BankAccount from;
    private BankAccount to;
    private double amount;
    private boolean isValid;
    private State state;    

    public Transaction(BankAccount from, BankAccount to, double amount) 
    {
        System.out.println("[LOG] Transaction constructor called with amount: " + amount);
        this.from = from;
        this.to = to;
        this.amount = amount;
        isValid = from.validate(amount) && to.validate(amount);
        this.state = State.PENDING;
    }
    
    public BankAccount getFrom() 
    {
        System.out.println("[LOG] getFrom() method called");
        System.out.println("[LOG] getFrom() method finished");
        return from;
    }

    public BankAccount getTo() 
    {
        System.out.println("[LOG] getTo() method called");
        System.out.println("[LOG] getTo() method finished");
        return to;
    }

    public double getAmount() 
    {
        System.out.println("[LOG] getAmount() method called");
        System.out.println("[LOG] getAmount() method finished");
        return amount;
    }

    public boolean isValid() 
    {
        System.out.println("[LOG] isValid() method called");
        System.out.println("[LOG] isValid() method finished");
        return isValid;
    }

    public State getState() 
    {
        System.out.println("[LOG] getState() method called");
        System.out.println("[LOG] getState() method finished");
        return state;
    }


    public void setAmount(double amount) 
    {
        System.out.println("[LOG] setAmount() method called with amount: " + amount);
        this.amount = amount;
        System.out.println("[LOG] setAmount() method finished");
    }

    public void setValid(boolean isValid) 
    {
        System.out.println("[LOG] setValid() method called with isValid: " + isValid);
        this.isValid = isValid;
        System.out.println("[LOG] setValid() method finished");
    }

    public void setState(State state) 
    {
        System.out.println("[LOG] setState() method called with state: " + state);
        this.state = state;
        System.out.println("[LOG] setState() method finished");
    }

    public boolean process() 
    {
        System.out.println("[LOG] process() method called");
        synchronized (this.getFrom()) 
        {
            synchronized (this.getTo()) 
            {
                if (!this.isValid()) 
                {
                    System.out.println("[LOG] Transaction is invalid, cannot process");
                    System.out.println("[LOG] process() method finished");
                    return false;
                }
                
                this.setState(State.EXECUTE);
                
                // Logic: Attempt to withdraw from sender and deposit to receiver
                if (this.getFrom().withdraw(this.getAmount())) 
                {
                    this.getTo().deposit(this.getAmount());
                    this.setState(State.FINISH);
                    System.out.println("[LOG] Transaction processed successfully");
                    System.out.println("[LOG] process() method finished");
                    return true;
                } else 
                {
                    this.setState(State.FAILED);
                    System.out.println("[LOG] Transaction failed - insufficient funds");
                    System.out.println("[LOG] process() method finished");
                    return false;
                }
            }
        }
        
    }

    // Overloaded process method with amount parameter 
    public boolean process(double customAmount) 
    {
        synchronized (this.getFrom()) 
        {
            synchronized (this.getTo()) 
            {
                if (customAmount < 0) customAmount = this.amount;
                System.out.println("The customAmount value (in process): " + customAmount);
                System.out.println("[LOG] process(double customAmount) method called with amount: " + customAmount);
                if (!this.isValid()) 
                {
                    System.out.println("[LOG] Transaction is invalid, cannot process");
                    System.out.println("[LOG] process(double customAmount) method finished");
                    return false;
                }
                
                // Validate the custom amount
                if (!this.getFrom().validate(customAmount) || !this.getTo().validate(customAmount)) 
                {
                    System.out.println("[LOG] Custom amount validation failed");
                    System.out.println("[LOG] process(double customAmount) method finished");
                    return false;
                }
                
                this.setState(State.EXECUTE);
                
                // Logic: Attempt to withdraw custom amount from sender and deposit to receiver
                if (this.getFrom().withdraw(customAmount)) 
                {
                    this.getTo().deposit(customAmount);
                    this.setState(State.FINISH);
                    System.out.println("[LOG] Transaction processed successfully with custom amount: " + customAmount);
                    // Try set custom amount = 0 to check with outside
                    System.out.println("[LOG] process(double customAmount) method finished");
                    return true;
                } else 
                {
                    this.setState(State.FAILED);
                    System.out.println("[LOG] Transaction failed - insufficient funds for custom amount: " + customAmount);
                    System.out.println("[LOG] process(double customAmount) method finished");
                    return false;
                }
            }
        }   
    }

    // Overloaded process method with two BankAccount parameters (from and to accounts)
    public boolean process(BankAccount fromAccount, BankAccount toAccount) 
    {
        System.out.println("[LOG] process(BankAccount fromAccount, BankAccount toAccount) method called");
        synchronized (this.getFrom()) 
        {
            synchronized (this.getTo()) 
            {
                // Validate both accounts are not null
                if (fromAccount == null || toAccount == null) 
                {
                    System.out.println("[LOG] From or To account is null, cannot process");
                    System.out.println("[LOG] process(BankAccount fromAccount, BankAccount toAccount) method finished");
                    return false;
                }
                
                if (!this.isValid()) 
                {
                    System.out.println("[LOG] Transaction is invalid, cannot process");
                    System.out.println("[LOG] process(BankAccount fromAccount, BankAccount toAccount) method finished");
                    return false;
                }
                
                // Validate amount with both provided accounts
                if (!fromAccount.validate(this.getAmount()) || !toAccount.validate(this.getAmount())) 
                {
                    System.out.println("[LOG] Amount validation failed for provided accounts");
                    System.out.println("[LOG] process(BankAccount fromAccount, BankAccount toAccount) method finished");
                    return false;
                }
                this.setState(State.EXECUTE);
                // Logic: Use provided accounts instead of the original transaction accounts
                if (fromAccount.withdraw(this.getAmount())) 
                {
                    toAccount.deposit(this.getAmount());
                    this.setState(State.FINISH);
                    System.out.println("[LOG] Transaction processed successfully between alternate accounts");
                    System.out.println("[LOG] process(BankAccount fromAccount, BankAccount toAccount) method finished");
                    return true;
                } else 
                {
                    this.setState(State.FAILED);
                    System.out.println("[LOG] Transaction failed - insufficient funds in provided from account");
                    System.out.println("[LOG] process(BankAccount fromAccount, BankAccount toAccount) method finished");
                    return false;
                }
            }
        }
    }

    @Override
    public String toString() {
        System.out.println("[LOG] toString() method called in Transaction");
        String result = "Transaction{" +
                "from=" + from.getAccountNo() +
                ", to=" + to.getAccountNo() +
                ", amount=" + amount +
                ", state=" + state +
                '}';
        System.out.println("[LOG] toString() method finished");
        return result;
    }
}
