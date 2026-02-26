// File:User
// Author: Nuttanun 
// Description: Collecting user data

// TO DO: Implement the class following the User diagram
// all setter can be set by admin only
// also track all account that they belongs
import java.util.ArrayList;

public class User {

    private String citizenId;
    private String name;
    private String telephoneNo;
    private String address;
    private String devicePin;

    private ArrayList<BankAccount> accounts;


    public User(String citizenId, String name, String telephoneNo, String address) {
        this.citizenId = citizenId;
        this.name = name;
        this.telephoneNo = telephoneNo;
        this.address = address;
        this.accounts = new ArrayList<>();
    }



    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCitizenId() {
        return citizenId;
    }

    public String getName() {
        return name;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }



    public void addAccount(BankAccount account) {
        accounts.add(account);
    }



    @Override
    public String toString() {
        return "User [citizenId=" + citizenId +
               ", name=" + name +
               ", telephoneNo=" + telephoneNo +
               ", address=" + address + "]";
    }
}
