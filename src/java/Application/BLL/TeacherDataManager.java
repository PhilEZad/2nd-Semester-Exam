package Application.BLL;

import Application.BE.*;
import Application.DAL.AccountDAO;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDataManager extends StudentDataManager
{
    AccountDAO accountDAO = new AccountDAO();

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
            } else
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
            citizen.create
        }

        // TODO: 25-05-2022 return default
        return citizen; 
    }

    public void deleteCitizen(Citizen citizen) throws IllegalArgumentException, SQLException
    {
        if (citizen != null)
        {
            citizenDAO.deleteCitizen(citizen.getID());
        }
        else
        {
            throw new IllegalArgumentException("");
        }

    }

    public void assignToCitizen(Account student, Citizen citizen) throws IllegalArgumentException, AccessDeniedException, SQLException
    {
        if (student != null && citizen != null)
        {
            throw new IllegalArgumentException("");
        }
        else
        {
            if (!student.getIsTeacher() && !student.getIsAdmin())
            {
                // FIXME: 25/05/2022 Use correct method from DAO
                assignedCitizenDAO.someDAOmethoc(student.getID(), citizen.getID());
            }
            else
            {
                throw new AccessDeniedException("");
            }
        }
    }

    public void removeFromCitizen(Account student, Citizen citizen) throws IllegalArgumentException, AccessDeniedException, SQLException
    {
        if (student != null && citizen != null)
        {
            throw new IllegalArgumentException("");
        }
        else
        {
            if (!student.getIsTeacher() && !student.getIsAdmin())
            {
                // FIXME: 25/05/2022 Use correct method from DAO
                assignedCitizenDAO.someDAOmethod(student.getID(), citizen.getID());
            }
            else
            {
                throw new AccessDeniedException("");
            }
        }
    }

}