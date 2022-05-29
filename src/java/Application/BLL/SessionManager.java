package Application.BLL;

import Application.BE.Account;
import Application.DAL.AccountDAO;
import Application.Utility.AccountType;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


/**
 * @author Mads Mandahl-Barth
 * */
public class SessionManager
{
    private static Account LoggedInUser;

    /**
     * @return the currently logged-in user, may be null.
     * */
    public static Account getCurrent()
    {
        return LoggedInUser;
    }


    public static void tryEndSession()
    {
        LoggedInUser = null;
    }

    public static boolean tryBeginSession(AccountType type, String username, String password)
    {
        Account account = null;

        try
        {
            account = new AccountDAO().read(username);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if (account != null && account.getPassword().equals(createToken(username, password)))
        {
            boolean correctAuthLevel = account.getIsAdmin() == type.isAdmin() &&  account.getIsTeacher() == type.isTeacher();

            LoggedInUser = correctAuthLevel ? account : null;
        }

        return LoggedInUser != null;
    }


    /**
     * @param input the original string that needs to be expanded
     * @param length the desired length of the result, negative sign is ignored (absolute value)
     * @return the input repeated until the length of the string equals the length parameter
     * */
    protected static String extendToLength(String input, int length)
    {
        if (length <= -1)
        {
            length = Math.abs(length);
        }

        if (input == null)
            return " ".repeat(length);

        int cycles = (length + 1) / Math.max(1, input.length()) + 1;

        String result = input.repeat(cycles);

        return result.substring(0, Math.min(result.length(), length));
    }

    /**
     * @param username .
     * @param password .
     * @return a base64 encoded hash string composed of the raw password and the username (as salt)
     * */
    public static String createToken(String username, String password)
    {
        var hashed = BCrypt.with(BCrypt.Version.VERSION_2Y).hash(BCrypt.MIN_COST, extendToLength(username, 16).getBytes(), password.getBytes(StandardCharsets.UTF_8));

        return new String(Base64.getEncoder().encode(hashed));
    }
}