package Application.GUI.Models;

import Application.BE.Account;
import Application.BLL.AccountManager;
import Application.Utility.Strings;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static Application.Utility.Strings.generateAccessToken;

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


    public boolean authenticate(String username, String password)
    {
        this.account = accountManager.authenticate(username, generateAccessToken(username, password));

        return this.account != null;
    }

}
