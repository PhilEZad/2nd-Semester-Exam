package Application.BLL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.AccountDAO;
import Application.DAL.SchoolDAO;
import Application.DAL.TemplatePatternDAO;

import java.util.List;

public class AdminDataManager {

    private TemplatePatternDAO accountDAO;
    private TemplatePatternDAO schoolDAO;


    public AdminDataManager() {
        accountDAO = new AccountDAO();
        schoolDAO = new SchoolDAO();
    }

    public Account createAccount(String username, String password, String firstName, String lastName, String email, School school, int auth) {
        return (Account) accountDAO.create(new Account(-1, username, password, firstName, lastName, email, school, auth));
    }

    public void updateAccount(Account student){
        accountDAO.update(student);
    }

    public void deleteAccount(Account student){
        accountDAO.delete(student.getId());
    }

    public Account getStudent(int id) {
        return (Account) accountDAO.read(id);
    }

    public List<Account> getAllStudents(){
        return accountDAO.readAll();
    }

    public School createSchool(String schoolName, int zipCode)
    {
        return (School) schoolDAO.create(new School(-1, schoolName, zipCode, ""));
    }

}
