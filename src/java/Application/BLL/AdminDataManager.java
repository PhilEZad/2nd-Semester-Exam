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

    public Account getAccount(int id) {
        return (Account) accountDAO.read(id);
    }

    public void updateAccount(Account student){
        accountDAO.update(student);
    }

    public void deleteAccount(Account student){
        accountDAO.delete(student.getId());
    }

    public School createSchool(String schoolName, int zipCode)
    {
        return (School) schoolDAO.create(new School(-1, schoolName, zipCode, ""));
    }

    public void editSchool(School school)
    {
        schoolDAO.update(school);
    }

    public void deleteSchool(School school)
    {
        schoolDAO.delete(school.getSchoolID());
    }

    public List<Account> getAllStudents(){
        return accountDAO.readAll();
    }
}
