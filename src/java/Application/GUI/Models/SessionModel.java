package Application.GUI.Models;

import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AccountManager;

import static Application.Utility.Strings.generateAccessToken;

public class SessionModel {

    private final AccountManager accountManager;

    public SessionModel()
    {
        accountManager = new AccountManager();
    }

    private static Account account;

    private static School school;

    /**
     * @return the currently logged-in user, may be null.
     * */
    public static Account getCurrent()
    {
        return account;
    }

    public static void setAccount(Account accountLoggedIn)
    {
        account = accountLoggedIn;
    }


    public boolean authenticate(String username, String password)
    {
        account = accountManager.authenticate(username, generateAccessToken(username, password));

        return account != null;
    }

    public static void setSchool(School school)
    {
        SessionModel.school = school;
    }

    public static School getSchool()
    {
        return school;
    }
}
