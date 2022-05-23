package Application.BLL;

import Application.BE.Account;
import Application.BE.Location;
import Application.BE.School;
import Application.DAL.AccountDAO;
import Application.DAL.SchoolDAO;
import Application.DAL.TemplatePatternDAO;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.SchoolModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
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
        return (School) schoolDAO.create(new School(-1, schoolName, new Location(zipCode)));
    }

    public School getSchool(int id) throws SQLException {
        return (School) schoolDAO.read(id);
    }

    public ObservableList<SchoolModel> getAllSchools()
    {
        List<School> schoolListBE = schoolDAO.readAll();
        ObservableList<SchoolModel> schoolModelsList = FXCollections.observableArrayList();

        for (School school : schoolListBE)
        {
            SchoolModel schoolModel = new SchoolModel(school);
            schoolModelsList.add(schoolModel);
        }
        return schoolModelsList;
    }

    public void editSchool(School school)
    {
        schoolDAO.update(school);
    }

    public void deleteSchool(int id)
    {
        schoolDAO.delete(id);
    }

    // CREATE/READ/UPDATE/DELETE teacher
        // read one / all

    public Account createAccount(String username, String password, String firstName, String lastName, String email, School school, int auth)
    {
        return (Account) accountDAO.create(new Account(-1, username, password, firstName, lastName, email, school, auth));
    }

    public Account getStudent(int id) throws SQLException {
        return (Account) accountDAO.read(id);
    }

    public ObservableList<AccountModel> getAllTeachers()
    {
        List<Account> accountListBE = accountDAO.readAll();
        ObservableList<AccountModel> accountModelsList = FXCollections.observableArrayList();

        for (Account account : accountListBE)
        {
            if (account.getAuthorization() == 1)
            {
                AccountModel accountModel = new AccountModel(account);
                accountModelsList.add(accountModel);
            }
        }
        return accountModelsList;
    }

    public ObservableList<AccountModel> getAllStudents()
    {
        List<Account> accountListBE = accountDAO.readAll();
        ObservableList<AccountModel> accountModelsList = FXCollections.observableArrayList();

        for (Account account : accountListBE)
        {
            if (account.getAuthorization() == 2)
            {
            AccountModel accountModel = new AccountModel(account);
            accountModelsList.add(accountModel);
            }
        }
        return accountModelsList;
    }

    public void updateAccount(Account account)
    {
        accountDAO.update(account);
    }

    public void deleteAccount(int id)
    {
        accountDAO.delete(id);
    }

    // access to Teacher data operations

    public void deleteSchool(School school)
    {
        schoolDAO.delete(school.getSchoolID());
    }

    public List<Account> getAllStudents2(){
        return accountDAO.readAll();
    }
}
