// File:User
// Author: Nuttanun 
// Description: Collecting user data

// TO DO: Implement the class following the User diagram
// all setter can be set by admin only
// also track all account that they belongs
import java.util.ArrayList;

public class User 
{

    private String citizenId;
    private String name;
    private String telephoneNo;
    private String address;
    private String devicePin;
    private ArrayList<BankAccount> accounts;

    public User(String citizenId, String name, String telephoneNo, String address) 
    {
        System.out.println("[LOG] User constructor called for: " + name);
        this.citizenId = citizenId;
        this.name = name;
        this.telephoneNo = telephoneNo;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public void setCitizenId(String citizenId) 
    {
        System.out.println("[LOG] setCitizenId() method called");
        this.citizenId = citizenId;
        System.out.println("[LOG] setCitizenId() method finished");
    }

    public void setName(String name) 
    {
        System.out.println("[LOG] setName() method called with name: " + name);
        this.name = name;
        System.out.println("[LOG] setName() method finished");
    }

    public void setTelephoneNo(String telephoneNo) 
    {
        System.out.println("[LOG] setTelephoneNo() method called");
        this.telephoneNo = telephoneNo;
        System.out.println("[LOG] setTelephoneNo() method finished");
    }

    public void setAddress(String address) 
    {
        System.out.println("[LOG] setAddress() method called");
        this.address = address;
        System.out.println("[LOG] setAddress() method finished");
    }


    public String getCitizenId() 
    {
        System.out.println("[LOG] getCitizenId() method called");
        return citizenId;
    }

    public String getName() 
    {
        System.out.println("[LOG] getName() method called");
        return name;
    }

    public String getTelephoneNo() 
    {
        System.out.println("[LOG] getTelephoneNo() method called");
        return telephoneNo;
    }

    public String getAddress() 
    {
        System.out.println("[LOG] getAddress() method called");
        return address;
    }

    public ArrayList<BankAccount> getAccounts() 
    {
        System.out.println("[LOG] getAccounts() method called");
        return accounts;
    }

    public void addAccount(BankAccount account) 
    {
        System.out.println("[LOG] addAccount() method called");
        accounts.add(account);
        System.out.println("[LOG] addAccount() method finished");
    }

    public boolean validatePin(String pin)
    {
        System.out.println("[LOG] validatePin() method called");
        boolean result = this.devicePin.equals(pin);
        System.out.println("[LOG] validatePin() method finished");
        return result;
    }

    @Override
    public String toString() 
    {
        System.out.println("[LOG] toString() method called");
        String result = "User [citizenId=" + citizenId +
               ", name=" + name +
               ", telephoneNo=" + telephoneNo +
               ", address=" + address + "]";
        System.out.println("[LOG] toString() method finished");
        return result;
    }
}
