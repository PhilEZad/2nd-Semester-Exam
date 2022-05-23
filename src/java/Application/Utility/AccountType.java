package Application.Utility;

import java.util.Arrays;

public enum AccountType
{
    NONE(-1),
    ADMIN(0),
    TEACHER(1),
    STUDENT(2);

    private final int id;

    AccountType(int id)
    {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AccountType getByID(int id)
    {
        return Arrays.stream(AccountType.values()).filter(accountType -> accountType.id == id).findFirst().orElse(NONE);
    }

}
