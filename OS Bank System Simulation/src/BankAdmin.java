// File:
// Author:
// Description:
import java.util.Arrays;
import java.util.List;


// Implement BankAdmin
public class BankAdmin {
    public enum AccessLevel {ROOT, EDIT, VIEW};
    public static List<BankAdmin> adminList = Arrays.asList(new BankAdmin("admin", "1234", AccessLevel.ROOT));

    private String username;
    private String password;
    private AccessLevel accessLevel;

    // Task 1: Implement constructor, username need to be unique
    public BankAdmin(String username, String password, AccessLevel accessLevel)
    {
        
    }

    // Task 2: add setter, can be set by admin with edit and root level only


    // Task 3: use to validate for update interest
    // Hint: Check all username, password need to match.
    public static boolean validateAdmin(String username, String password)
    {
        return false;
    }

}
