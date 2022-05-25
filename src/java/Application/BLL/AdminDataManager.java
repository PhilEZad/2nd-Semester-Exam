package Application.BLL;

import Application.BE.Account;
import Application.BE.School;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDataManager extends TeacherDataManager
{

    public AdminDataManager()
    {

    }

    public Account createTeacher(Account account) throws IllegalArgumentException, SQLException
    {
        if (account != null)
        {
            account.setIsAdmin(false);
            account.setIsTeacher(true);
            return accountDAO.createAccount(account);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public List<Account> getAllTeachers() throws SQLException
    {
        List<Account> accountList = accountDAO.getAllAccounts();
        List<Account> teacherList = new ArrayList<>();

        for (Account account: accountList)
        {
            if (account.getIsTeacher())
            {
                teacherList.add(account);
            }
        }

        return teacherList;
    }

    public Account updateTeacher(Account account) throws AccessDeniedException, SQLException
    {
        if (account.getIsTeacher())
        {
            accountDAO.updateAccount(account);
        } else
        {
            throw new AccessDeniedException("");
        }

    }

    public int deleteTeacher(Account account) throws IllegalArgumentException, SQLException
    {
        if (account.getIsTeacher())
        {
            account.deleteAccount(account.getID());
        } else
        {
            throw new IllegalArgumentException("");
        }

    }

    public School createSchool(School school) throws IllegalArgumentException, SQLException
    {
        if (school != null)
        {
            return schoolDO.createSchool(school);
        } else
        {
            throw new IllegalArgumentException("");
        }
    }

    public List<School> getAllSchools() throws SQLException
    {
        return schoolDAO.getAllSchools();
    }

    public void updateSchool(School school) throws IllegalArgumentException, SQLException
    {
        if (school != null)
        {
            schoolDAO.updateSchool(school);
        } else
        {
            throw new IllegalArgumentException("");
        }

    }

    public void deleteSchool(School school) throws IllegalArgumentException, SQLException
    {
        if (school != null)
        {
            schoolDAO.deleteSchool(school.getID());
        } else
        {
            throw new IllegalArgumentException("");
        }
    }

    public Account createAdmin(Account account) throws IllegalArgumentException, SQLException
    {
        if (account != null)
        {
            account.setIsTeacher(false);
            account.setIsAdmin(true);
            accountDAO.createAccount(account);
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }

    public List<Account> getAllAdmins() throws SQLException
    {
        List<Account> accountList = accountDAO.getAllAccounts();
        List<Account> adminList = new ArrayList<>();

        for (Account account: accountList)
        {
            if (account.getIsAdmin())
            {
                adminList.add(account);
            }
        }

        return admintList;
    }

    public void updateAdmin(Account account) throws IllegalAccessException, SQLException
    {
        if (account.getIsAdmin())
        {
            account.updateAccount(account.getID());
        }
        else
        {
            throw new IllegalAccessException("");
        }
    }

    public void deleteAdmin(Account account) throws AccessDeniedException, SQLException
    {
        if (account.getIsAdmin())
        {
            accountDAO.deleteAccount(account.getID());
        }
        else
        {
            throw new AccessDeniedException("");
        }
    }
}
