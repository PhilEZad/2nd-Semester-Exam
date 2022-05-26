package Application.BLL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.SchoolDAO;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDataManager extends TeacherDataManager
{
    SchoolDAO schoolDAO = new SchoolDAO();

    public AdminDataManager()
    {

    }

    public Account createTeacher(Account account) throws IllegalArgumentException, SQLException
    {
        if (account != null)
        {
            account.setIsAdmin(false);
            account.setIsTeacher(true);
            return accountDAO.create(account);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public List<Account> getAllTeachers() throws SQLException
    {
        List<Account> accountList = accountDAO.readAll();
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

    public void updateTeacher(Account account) throws AccessDeniedException, SQLException
    {
        if (account.getIsTeacher())
        {
           accountDAO.update(account);
        } else
        {
            throw new AccessDeniedException("");
        }

    }

    public void deleteTeacher(Account account) throws IllegalArgumentException, SQLException
    {
        if (account.getIsTeacher())
        {
           accountDAO.delete(account.getID());
        } else
        {
            throw new IllegalArgumentException("");
        }
    }

    public School createSchool(School school) throws IllegalArgumentException, SQLException
    {
        if (school != null)
        {
            return schoolDAO.create(school);
        } else
        {
            throw new IllegalArgumentException("");
        }
    }

    public List<School> getAllSchools() throws SQLException
    {
        return schoolDAO.readAll();
    }

    public void updateSchool(School school) throws IllegalArgumentException, SQLException
    {
        if (school != null)
        {
            schoolDAO.update(school);
        } else
        {
            throw new IllegalArgumentException("");
        }

    }

    public void deleteSchool(School school) throws IllegalArgumentException, SQLException
    {
        if (school != null)
        {
            schoolDAO.delete(school.getID());
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
            return accountDAO.create(account);
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }

    public List<Account> getAllAdmins() throws SQLException
    {
        List<Account> accountList = accountDAO.readAll();
        List<Account> adminList = new ArrayList<>();

        for (Account account: accountList)
        {
            if (account.getIsAdmin())
            {
                adminList.add(account);
            }
        }

        return adminList;
    }

    public void updateAdmin(Account account) throws IllegalAccessException, SQLException
    {
        if (account.getIsAdmin())
        {
            accountDAO.update(account);
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
            accountDAO.delete(account.getID());
        }
        else
        {
            throw new AccessDeniedException("");
        }
    }
}
