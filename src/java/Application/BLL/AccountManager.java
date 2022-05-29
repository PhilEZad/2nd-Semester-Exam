package Application.BLL;

import Application.BE.Account;
import Application.DAL.AccountDAO;


public class AccountManager
{
    private AccountDAO accountData;

    public AccountManager()
    {
        accountData = new AccountDAO();
    }

    /**
     * @return null if the account doesn't exist or if the password is wrong
     * */
    public Account authenticate(String username, String accessToken)
    {
        var account = accountData.read(username);

        if (account != null && account.getPassword().equals(accessToken))
        {
            return account;
        }

        return null;
    }
}
