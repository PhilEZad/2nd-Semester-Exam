package Application.BLL;

import Application.BE.*;
import Application.DAL.*;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO accountDAO;
    TemplatePatternDAO citizenDAO;
    TemplatePatternDAO citizenTemplateDAO;
    TemplatePatternDAO categoryDAO;
    GroupDAO groupDAO;

    public TeacherDataManager()
    {
        citizenTemplateDAO = new CitizenDAO();
        accountDAO = new AccountDAO();
        citizenDAO = new CitizenDAO();
        categoryDAO = new CategoryDAO();
        groupDAO = new GroupDAO();
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

    public AccountModel getStudent(Account account) throws SQLException {
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
    public List<Account> getAllStudents()
    {
        return accountDAO.readAll();
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

    public CitizenModel getCitizen(Citizen citizen) throws SQLException {
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
                            -1,
                            1
                    ));

            return citizenModel;
        }
    }

    public List<Citizen> getAllCitizens()
    {
        List<Citizen> citizenList = citizenDAO.readAll();

        for (Citizen citizen : citizenList)
        {
            if (citizen.getTemplate() == 0)
            {
                citizenList.remove(citizen);
            }
        }

        return citizenList;
    }

    public void deleteCitizen(Citizen citizen)
    {
            citizenDAO.delete(citizen.getId());
    }

    public void citizenFromTemplate(Citizen citizen)
    {
        citizen.setTemplate(1);
        citizenDAO.create(citizen);
    }


    // copy citizen (clone template - new ID)

    // Create/Read/Update/Delete - group (GroupDAO)
        // [delete/archive citizen]
        // single / all

    public Group createGroup(Group group)
    {
        return (Group) groupDAO.create(group);
    }

    public Group getGroup(Group group) throws SQLException {
        return (Group) groupDAO.read(group.getId());
    }

    // TODO: 22-05-2022 List of all Groups in ObservableList form 
    
    public Boolean updateGroup(Group group)
    {
        if (group != null)
        {
            groupDAO.update(group);
            return true;
        } else
        {
            return false;
        }
    }

    public void deleteGroup(Group group)
    {
            groupDAO.delete(group.getId());
    }

    // FIXME: 22-05-2022 Not correct list
    // get student(s) in a group (members)
   // public ObservableList<AccountModel> getGroupMembers(Group group)
  //  {
  //      List<Account> accountList = groupDAO.readAll();
  //      ObservableList<AccountModel> returnList = FXCollections.observableArrayList();
  //   for (Account account: accountList)
  //      {
  //          AccountModel accountModel = new AccountModel(
  //                  account
  //          );
  //          returnList.add(accountModel);
  //      }
  //      return returnList;
  //  }

    // get all groups that contains a specific student

    public void assignToGroup(Citizen citizen, Group group)
    {

    }

    public void assignToGroup(Account student, Group group)
    {

    }

    public void unassignFromGroup(Account student, Group group)
    {

    }


    // utility:
        // generate random names / data





    public List getAllCitizenTemplates() {
        List<Citizen> citizenList = citizenTemplateDAO.readAll();
        List<Citizen> citizenTemplatesList = new ArrayList<>();


        for (Citizen citizenTemplate: citizenList)
        {
            if (citizenTemplate.getTemplate() == 0)
            {
                citizenTemplatesList.add(citizenTemplate);
            }
        }

        return citizenTemplatesList;
    }

    public Citizen newCitizenTemplate() {
        //Template
        //Citizen newTemplate = new Citizen(-1);
        //Generate blank category entries?

       // return (Citizen) citizenTemplateDAO.create(newTemplate);
        return null;
    }

    public void deleteCitizenTemplate(Citizen template)
    {
        citizenDAO.delete(template.getId());
    }

    public CitizenModel copyCitizenTemplate(Citizen template)
    {
        CitizenModel citizenModel = new CitizenModel
                (
                        (Citizen) citizenDAO.create(template)
                );
        return citizenModel;
    }

    public void updateCitizenTemplate(Citizen template, HashMap<Category, ContentEntry> beHealthConditions, HashMap<Category, ContentEntry> beFunctionalAbilities)
    {
        template.setContent(beHealthConditions);
        template.setHealthConditions(beHealthConditions);
        template.setFunctionalAbilities(beFunctionalAbilities);

        citizenDAO.update(template);
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