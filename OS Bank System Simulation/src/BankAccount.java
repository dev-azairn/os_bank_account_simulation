// File:
// Author:
// Description:


public abstract class BankAccount implements Transferable {


    public abstract boolean validate();

    @Override
    public boolean deposit() {
        // implement here!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean withdraw() {
        // implement here!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public double getBalance() {
        // implement here!
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }
    
}
