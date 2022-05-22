package Application.BLL;

import Application.BE.*;
import Application.DAL.*;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO accountDAO;
    TemplatePatternDAO inquiryDAO;
    TemplatePatternDAO healthCategoryDAO;
    TemplatePatternDAO functionalAbilityDAO;
    TemplatePatternDAO citizenDAO;
    TemplatePatternDAO citizenTemplateDAO;
    TemplatePatternDAO categoryDAO;
    TemplatePatternDAO generalInfoDAO;

    public TeacherDataManager()
    {
        accountDAO = new AccountDAO();
        citizenDAO = new CitizenDAO();
        categoryDAO = new CategoryDAO();
    }


    // Create/Read/Update/Delete - student (AccountDAO)
    // single / all

    public AccountModel createStudent(Account account)
    {
       AccountModel accountModel = new AccountModel(
            (Account) accountDAO.create(account)
        );

       return accountModel;
    }

    /**
     * Fetches an Account model from Database and returns it as an AccountModel object.
     * If account is null, makes a new AccountModel object with ID -1.
     * @param account
     * @return
     */

    public AccountModel getStudent(Account account)
    {
        AccountModel accountModel;

        if(account != null)
        {
            accountModel = new AccountModel(
                    (Account) accountDAO.read(account.getId())
                    );

            return accountModel;
        } else {
            accountModel = new AccountModel(
                    new Account(
                            -1,
                            "",
                            "",
                            "",
                            "",
                            "",
                            new School(),
                            2
                    ));
            return accountModel;
        }
    }

    /**
     * Fetches a list of all accounts, sorts them by privilege and returns an ObservableList of only student AccountModel objects.
     * @return ObservableList<AccountModel>
     */
    public ObservableList<AccountModel> getAllStudents()
    {
        ObservableList<AccountModel> returnList = FXCollections.observableArrayList();
        List<Account> accountList = accountDAO.readAll();

        for (Account account : accountList)
        {
            if (account.getAuthorization() == 2)
            {
                AccountModel accountModel = new AccountModel(account);
                returnList.add(accountModel);
            }
        }
        return returnList;
    }

    public Boolean updateStudent(Account account)
    {
        if (account != null)
        {
            accountDAO.update(account);
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * Deletes an account based on account ID, returns true if not null, false if null.
     * @param account
     * @return Boolean
     */
    public Boolean deleteStudent(Account account)
    {
        if (account != null)
        {
            accountDAO.delete(account.getId());
            return true;
        } else {
            return false;
        }
    }

    // Create/Read/Update/Delete - citizen (template) (CitizenDAO)
        // single / all

    public CitizenModel createCitizenTemplate (CitizenModel citizen)
    {
        CitizenModel citizenModel = new CitizenModel
                (
                    (Citizen) citizenDAO.create(citizen)
                );
        return citizenModel;
    }

    public Boolean updateCitizen (Citizen citizen)
    {
        if (citizen != null)
        {
        citizenDAO.update(citizen);
        return true;
        } else
        {
            return false;
        }
    }

    public CitizenModel getCitizen(Citizen citizen)
    {
        CitizenModel citizenModel;

        if(citizen != null)
        {
            citizenModel = new CitizenModel(
                    (Citizen) accountDAO.read(citizen.getId())
            );

            return citizenModel;

        } else {
            citizenModel = new CitizenModel(
                    new Citizen(
                            -1,
                            new GeneralJournal(
                                    -1,
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    ""
                            ),
                            new School(
                                    -1,
                                    "",
                                    new Location(
                                            -1,
                                            ""
                                    )
                            ),
                            "",
                            "",
                            -1
                    ));

            return citizenModel;
        }
    }

    public ObservableList<CitizenModel> getAllCitizens()
    {
        ObservableList<CitizenModel> returnList = FXCollections.observableArrayList();
        List<Citizen> citizenList = citizenDAO.readAll();

        for (Citizen citizen : citizenList)
        {
            {
                CitizenModel citizenModel = new CitizenModel(citizen);
                returnList.add(citizenModel);
            }
        }
        return returnList;
    }

    public Boolean deleteCitizen(Citizen citizen)
    {
        if(citizen != null)
        {
            citizenDAO.update(citizen.getId());
            return true;
        } else
        {
            return false;
        }
    }

    // copy citizen (clone template - new ID)

    // Create/Read/Update/Delete - group (GroupDAO)
        // [delete/archive citizen]
        // single / all

    // get student(s) in a group (members)

    // get all groups that contains a specific student

    public void assignToGroup(Citizen citizen, Group group)
    {
    // assign citizen to group (GroupDAO)
        // create clone of template (CitizenDAO)
    }

    public void assignToGroup(Account student, Group group)
    {
       // assign student to group (GroupDAO)
    }

    public void unassignFromGroup(Account student, Group group)
    {
       // unassign student to group (GroupDAO)
    }


    // utility:
        // generate random names / data





    public List getAllCitizenTemplates() {
        List<Citizen> citizenTemplates = citizenTemplateDAO.readAll();


        return citizenTemplates;
    }

    public Citizen newCitizenTemplate() {
        //Template
        Citizen newTemplate = new Citizen(-1);
        //Generate blank category entries?

        return (Citizen) citizenTemplateDAO.create(newTemplate);
    }

    public void deleteCitizenTemplate(Citizen template) {
    }

    public void copyCitizenTemplate(Citizen template) {
    }

    public void updateCitizenTemplate(Citizen template, List<ContentEntry> beHealthConditions, List<ContentEntry> beFunctionalAbilities) {
    }

    public void newCitizenEntity(Citizen template) {
        try {
            citizenDAO.create(template.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void updateCitizenEntity(Citizen citizen) {
        citizenDAO.update(citizen);
    }

    public void deleteCitizenEntity(Citizen citizen) {
        citizenDAO.delete(citizen.getId());
    }
}