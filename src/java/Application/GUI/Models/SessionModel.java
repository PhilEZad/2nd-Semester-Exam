package Application.GUI.Models;

import Application.BE.Account;
import Application.BLL.AccountManager;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class SessionModel {

    private final AccountManager accountManager;

    public SessionModel()
    {
        accountManager = new AccountManager();
    }

    private Account account;

    /**
     * @return the currently logged-in user, may be null.
     * */
    public Account getCurrent()
    {
        return this.account;
    }

    /**
     * @param username .
     * @param password .
     * @return a base64 encoded hash string composed of the raw password and the username (as salt)
     * */
    private String generateAccessToken(String username, String password) {
        var hashed = BCrypt.with(BCrypt.Version.VERSION_2Y).hash(BCrypt.MIN_COST, extendStringToLength(username, 16).getBytes(), password.getBytes(StandardCharsets.UTF_8));

        return new String(Base64.getEncoder().encode(hashed));
    }

    /**
     * @param input the original string that needs to be expanded
     * @param length the desired length of the result
     * @return the input repeated until the length of the string equals the length parameter
     * */
    private String extendStringToLength(String input, int length) {
        int cycles = (length + 1) / Math.max(1, input.length()) + 1;

        String result = input.repeat(cycles);

        return result.substring(0, Math.min(result.length(), length));
    }


    public boolean authenticate(String username, String password)
    {
        this.account = accountManager.authenticate(username, generateAccessToken(username, password));

        return this.account != null;
    }

}
