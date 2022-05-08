package Application.GUI.Models;

import Application.BLL.AccountManager;
import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.LongPasswordStrategy;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;


import Application.BE.Account;
import Application.BE.School;
import Application.BLL.AdminDataManager;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AccountModel
{
    private final AccountManager accountManager;

    public AccountModel() {
        accountManager = new AccountManager();
        accounts = FXCollections.observableArrayList();
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


    /*
    * todo: move below code to a different model (some model for the admin view ?)
    * */



    private AdminDataManager adminDataManager = new AdminDataManager();

    ObservableList<Account> accounts = new SimpleListProperty<>();

    public void createAccount(String text, String text1, String text2, String text3, String text4, School school, int i)
    {
           var account = adminDataManager.createAccount(text, text1, text2, text3, text4, school, i);
           accounts.add(account);
    }
}
