// File:BankAdmin
// Author:Nuttanun Muanraksa
// Description:Checking password and using with users class
import java.util.Arrays;
import java.util.List;


public class BankAdmin 
{
    public enum AccessLevel {ROOT, EDIT, VIEW};

    public static List<BankAdmin> adminList =
        Arrays.asList(new BankAdmin("admin", "1234", AccessLevel.ROOT));

    private String username;
    private String password;
    private AccessLevel accessLevel;

    public BankAdmin(String username, String password, AccessLevel accessLevel)
    {
        System.out.println("[LOG] BankAdmin constructor called for user: " + username);
        if (adminList != null) 
        {
            for (BankAdmin admin : adminList)
            {
                if (admin.username.equals(username)) 
                {
                    throw new IllegalArgumentException("Username already exists");
                }
            }
        }

        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public void setPassword(String newPassword, BankAdmin requester) 
    {
        System.out.println("[LOG] setPassword() method called");
        if (requester.accessLevel == AccessLevel.ROOT || requester.accessLevel == AccessLevel.EDIT) 
            this.password = newPassword;
        else throw new SecurityException("Access denied");
    }

    public void setAccessLevel(AccessLevel newLevel, BankAdmin requester) 
    {
        System.out.println("[LOG] setAccessLevel() method called with level: " + newLevel);
        if (requester.accessLevel == AccessLevel.ROOT || requester.accessLevel == AccessLevel.EDIT) this.accessLevel = newLevel;
        else throw new SecurityException("Access denied");
        
    }

    public static boolean validateAdmin(String username, String password)
    {
        System.out.println("[LOG] validateAdmin() method called for user: " + username);
        for (BankAdmin admin : adminList) 
            if (admin.username.equals(username) && admin.password.equals(password)) 
                return true;
        return false;
    }

    public AccessLevel getAccessLevel() 
    {
        System.out.println("[LOG] getAccessLevel() method called");
        return accessLevel;
    }

    @Override
    public String toString() 
    {
        System.out.println("[LOG] toString() method called in BankAdmin");
        return "BankAdmin{" +
                "username='" + username + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
