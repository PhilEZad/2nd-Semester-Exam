package Application.BLL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.AccountDAO;
import Application.DAL.SchoolDAO;
import Application.DAL.TemplatePatternDAO;

import java.util.List;

public class AdminDataManager {

    private AccountDAO accountDAO;
    private TemplatePatternDAO schoolDAO;


    public AdminDataManager() {
        accountDAO = new AccountDAO();
        schoolDAO = new SchoolDAO();
    }

    public boolean createAccount(String username, String password, String firstName, String lastName, String email, School school, int auth) {
        return accountDAO.create(new Account(-1, username, password, firstName, lastName, email, school, auth));
    }

    public void updateAccount(Account student){
        accountDAO.update(student);
    }

    public void changePassword(String newPassword, Account account)
    {
        accountDAO.changePassword(newPassword,account);
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

    public boolean createSchool(String schoolName, int zipCode)
    {
        return schoolDAO.create(new School(-1, schoolName, zipCode, ""));
    }

}
