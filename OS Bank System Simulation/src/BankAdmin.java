// File:BankAdmin
// Author:Nuttanun Muanraksa
// Description:Checking password and using with users class
import java.util.Arrays;
import java.util.List;


public class BankAdmin {

    public enum AccessLevel {ROOT, EDIT, VIEW};

    public static List<BankAdmin> adminList =
        Arrays.asList(new BankAdmin("admin", "1234", AccessLevel.ROOT));

    private String username;
    private String password;
    private AccessLevel accessLevel;

    public BankAdmin(String username, String password, AccessLevel accessLevel)
    {
        if (adminList != null) {
            for (BankAdmin admin : adminList) {
                if (admin.username.equals(username)) {
                    throw new IllegalArgumentException("Username already exists");
                }
            }
        }

        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public void setPassword(String newPassword, BankAdmin requester) {
        if (requester.accessLevel == AccessLevel.ROOT ||
            requester.accessLevel == AccessLevel.EDIT) {
            this.password = newPassword;
        } else {
            throw new SecurityException("Access denied");
        }
    }

    public void setAccessLevel(AccessLevel newLevel, BankAdmin requester) {
        if (requester.accessLevel == AccessLevel.ROOT ||
            requester.accessLevel == AccessLevel.EDIT) {
            this.accessLevel = newLevel;
        } else {
            throw new SecurityException("Access denied");
        }
    }

    public static boolean validateAdmin(String username, String password)
    {
        for (BankAdmin admin : adminList) {
            if (admin.username.equals(username) &&
                admin.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
}
