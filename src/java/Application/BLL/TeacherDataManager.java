package Application.BLL;

import Application.BE.Account;
import Application.BE.Category;
import Application.BE.Citizen;
import Application.BE.FunctionalEntry;
import Application.DAL.AccountDAO;
import Application.DAL.AssignedAccountsDAO;
import Application.DAL.AssignedCitizensDAO;
import javafx.util.Pair;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Philip Zadeh
 * @author Rasmus Sandbæk
 * @author Mads Mandahl-Barth
 * */
public class TeacherDataManager extends StudentDataManager
{
    AccountDAO accountDAO;

    AssignedCitizensDAO assignedCitizensDAO;
    AssignedAccountsDAO assignedAccountsDAO;

    public TeacherDataManager()
    {
        accountDAO = new AccountDAO();

        assignedCitizensDAO = new AssignedCitizensDAO();
        assignedAccountsDAO = new AssignedAccountsDAO();
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
        List<Citizen> citizenList = new ArrayList<>();
        citizenList.add(citizen);

        if (student == null || citizen == null)
        {
            throw new IllegalArgumentException("");
        }
        else
        {
            if (!student.getIsTeacher() && !student.getIsAdmin())
            {
                assignedCitizensDAO.create(new Pair<>(student, citizenList));
            }
            else
            {
                throw new AccessDeniedException("");
            }
        }
    }

    public List<Citizen> assignedCitizens (Account account) throws SQLException
    {

        Pair<Account, List<Citizen>> pair = assignedCitizensDAO.read(account.getID());

        return pair.getValue();
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

    public List<Citizen> getAllCitizenTemplates()
    {
        List<Citizen> citizenListDAO = citizenDAO.readAll();
        List<Citizen> citizenTemplatesList = new ArrayList<>();

        for (Citizen citizen: citizenListDAO)
        {
            if (citizen.getTemplate())
            {
                citizenTemplatesList.add(citizen);
            }
        }
        return citizenTemplatesList;
    }
}