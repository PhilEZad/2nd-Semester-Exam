package Application.BLL;

import Application.BE.*;
import Application.DAL.AccountDAO;
import Application.DAL.AssignedAccountsDAO;
import Application.DAL.AssignedCitizensDAO;
import Application.DAL.CitizenDAO;
import javafx.util.Pair;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherDataManager extends StudentDataManager
{
    AccountDAO accountDAO = new AccountDAO();

    AssignedCitizensDAO assignedCitizensDAO = new AssignedCitizensDAO();
    AssignedAccountsDAO assignedAccountsDAO = new AssignedAccountsDAO();

    public TeacherDataManager()
    {

    }

    public Account createStudent(Account account) throws IllegalArgumentException, AccessDeniedException, SQLException
    {
        if (account == null)
        {
            throw new IllegalArgumentException("");

        } else
        {
            if (!account.getIsTeacher() && !account.getIsAdmin())
            {
                return accountDAO.create(account);
            }
            else
            {
                throw new AccessDeniedException("");
            }
        }
    }

    public List<Account> getAllStudents() throws SQLException
    {
        List<Account> accountList = accountDAO.readAll();
        List<Account> studentList = new ArrayList<>();

        for (Account account: accountList)
        {
            if (!account.getIsTeacher() && !account.getIsAdmin())
            {
                studentList.add(account);
            }
        }

        return studentList;
    }

    public Account updateStudent(Account account) throws IllegalArgumentException, SQLException
    {
        if (!account.getIsTeacher() && !account.getIsAdmin())
        {
            accountDAO.update(account);
        } else
        {
            throw new IllegalArgumentException("");
        }

        return account;
    }

    public void deleteStudent(Account account) throws IllegalArgumentException, SQLException
    {
        if (!account.getIsAdmin() && !account.getIsTeacher())
        {
            accountDAO.delete(account.getID());
        } else
        {
            throw new IllegalArgumentException("");
        }
    }

    public Citizen createCitizen(Citizen citizen)
    {
        if (citizen != null)
        {
            return citizenDAO.create(citizen);
        }

        return new Citizen();
    }

    public void deleteCitizen(Citizen citizen) throws IllegalArgumentException, SQLException
    {
        if (citizen != null)
        {
            citizenDAO.delete(citizen.getID());
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }

    public void assignToCitizen(Account student, Citizen citizen) throws IllegalArgumentException, AccessDeniedException, SQLException
    {
        if (student == null || citizen == null)
        {
            throw new IllegalArgumentException("");
        }
        else
        {
            if (!student.getIsTeacher() && !student.getIsAdmin())
            {
                assignedCitizensDAO.create(new Pair<>(student, List.of(citizen)));
            }
            else
            {
                throw new AccessDeniedException("");
            }
        }
    }

    public void removeFromCitizen(Account student, Citizen citizen) throws IllegalArgumentException, AccessDeniedException, SQLException
    {
        if (student == null || citizen == null)
        {
            throw new IllegalArgumentException("");
        }
        else
        {
            if (!student.getIsTeacher() && !student.getIsAdmin())
            {
                assignedCitizensDAO.delete(student.getID(), citizen.getID());
            }
            else
            {
                throw new AccessDeniedException("");
            }
        }
    }

    public void updateCitizen(Citizen beCitizen, HashMap<Category, FunctionalEntry> beHealthConditions, HashMap<Category, FunctionalEntry> beFuncConditions)
    {

    }
}