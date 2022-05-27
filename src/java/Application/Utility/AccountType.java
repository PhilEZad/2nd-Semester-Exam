package Application.Utility;

import java.util.Arrays;

public enum AccountType
{
    NONE(-1),
    ADMIN(0),
    TEACHER(1),
    STUDENT(2);

    private final int id;

    private boolean isAdmin = false;
    private boolean isTeacher = false;
    private boolean isStudent = false;

    AccountType(int id)
    {
        this.id = id;

        switch (id) {
            case 0 -> this.isAdmin = true;
            case 1 -> this.isTeacher = true;
            case 2 -> this.isStudent = true;
        }
    }

    public int getId() {
        return id;
    }

    public static AccountType getByID(int id)
    {
        return Arrays.stream(AccountType.values()).filter(accountType -> accountType.id == id).findFirst().orElse(NONE);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public boolean isStudent() {
        return isStudent;
    }
}