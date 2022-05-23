package backend.logic;

import at.favre.lib.crypto.bcrypt.BCrypt;
import backend.entity.User;
import util.AccountType;

import java.io.InvalidClassException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SessionManager
{
    private static User LoggedInUser;

    /**
     * @return the currently logged-in user, may be null.
     * */
    public static User getCurrent()
    {
        return LoggedInUser;
    }


    public static void tryEndSession()
    {
        LoggedInUser = null;
    }

    public static boolean tryBeginSession(int schoolID, AccountType type, String username, String password) throws InvalidClassException
    {
        User account = (User) DataAccessFactory.get(User.class).getAccessObject().getByString(username);

        if (account != null && account.getAuthorization().equals(type) && account.getPassword().equals(createToken(username, password)))
        {
            LoggedInUser = account;
        }

        return LoggedInUser != null;
    }


    /**
     * @param input the original string that needs to be expanded
     * @param length the desired length of the result
     * @return the input repeated until the length of the string equals the length parameter
     * */
    private static String extendToLength(String input, int length) {
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
