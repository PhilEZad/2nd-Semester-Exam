package Application.BLL;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BE.GeneralJournal;
import Application.DAL.*;
import Application.GUI.Models.CitizenModel;
import com.github.javafaker.Faker;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


/**
 *
 * @author Mads Mandahl-Barth
 * */
public class CitizenManager
{
    private static final HealthEntriesManager healthEntriesManager = new HealthEntriesManager();
    private static final FunctionalEntriesManager functionalEntriesManager = new FunctionalEntriesManager();

    private static final CitizenDAO citizenDAO = new CitizenDAO();

    private static final GeneralInformationDAO generalInfoDAO = new GeneralInformationDAO();

    public List<Citizen> getAllTemplates()
    {
        var citizens = citizenDAO.readAll(SessionManager.getCurrent().getSchool()).stream().filter(Citizen::getTemplate).collect(java.util.stream.Collectors.toList());

        citizens.forEach(citizen -> {
            for (var entry : healthEntriesManager.getEntriesFor(citizen.getID()))
            {
                citizen.addHealthConditions(entry);
            }

            for (var entry : functionalEntriesManager.getEntriesFor(citizen.getID()))
            {
                citizen.addFunctionalAbility(entry);
            }
        });

        return citizens;
    }


    public Citizen createCitizenTemplate()
    {
        var baseData = generateBaseData();

        // create citizen in db
        Citizen citizen = citizenDAO.create(new Citizen(-1, SessionManager.getCurrent().getSchool(), (String) baseData[0], (String) baseData[1], Integer.parseInt((String) baseData[2]), true));

        // create general journal
        GeneralJournal generalJournal = new GeneralJournal();
        generalJournal.setCitizenID(citizen.getID());
        generalInfoDAO.create(generalJournal);

        citizen.setGeneralJournal(generalJournal);

        for (var entry : healthEntriesManager.getEntriesFor(-1))
        {
            citizen.addHealthConditions(entry);
        }

        for (var entry : functionalEntriesManager.getEntriesFor(-1))
        {
            citizen.addFunctionalAbility(entry);
        }

        return citizen;
    }

    public void updateCitizen(Citizen citizen)
    {
        // update citizen in db
        citizenDAO.update(citizen);

        // update journals in db
        new GeneralInformationDAO().update(citizen.getGeneralInfo());
        new FunctionalAbilityDAO().updateAll(citizen.getFunctionalAbilities());
        new HealthConditionDAO().updateAll(citizen.getHealthConditions());
    }

    public boolean removeStudentCitizenRelation(Account account, Citizen citizen)
    {
        // check is there a relation between citizen and account
            // if not return false

        // if template citizen
            // return false

        // remove Citizen <-> Account relation
            // if last account on citizen
                // delete citizen from db


        return true;
    }

    public void addStudentCitizenRelation(Account account, Citizen citizen)
    {
        Citizen newCitizen = citizen;

        // if template
        if (citizen.getTemplate())
        {
            try
            {
                newCitizen = (Citizen) citizen.clone();
                newCitizen.setTemplate(false);

                // create citizen in db
                // get concrete citizen (i.e. clone of template with id from db)
                newCitizen = citizenDAO.create(newCitizen);

                // clone general/health/functional journals
                newCitizen.setGeneralJournal(generalInfoDAO.create(newCitizen.getGeneralInfo()));


                for (var entry : healthEntriesManager.getEntriesFor(citizen.getID()))
                {
                    newCitizen.addHealthConditions(entry);
                }

                for (var entry : functionalEntriesManager.getEntriesFor(citizen.getID()))
                {
                    newCitizen.addFunctionalAbility(entry);
                }

                updateCitizen(newCitizen);
            }
            catch (CloneNotSupportedException e)
            {
                throw new RuntimeException(e);
            }
        }

        // add account to citizen
        new AssignedAccountsDAO().create(new Pair<>(newCitizen, new ArrayList<>(List.of(account))));
    }

    public Citizen createCitizenTemplateCopy(CitizenModel convert)
    {
        Citizen citizen = citizenDAO.create(convert.getBeCitizen());

        // create general journal
        GeneralJournal generalJournal = convert.getBeCitizen().getJournal();
        generalJournal.setCitizenID(citizen.getID());

        generalInfoDAO.create(generalJournal);

        citizen.setGeneralJournal(generalJournal);

        for (var entry : healthEntriesManager.getEntriesFor(citizen.getID()))
        {
            citizen.addHealthConditions(entry);
        }

        for (var entry : functionalEntriesManager.getEntriesFor(citizen.getID()))
        {
            citizen.addFunctionalAbility(entry);
        }

        return citizen;
    }

    public void deleteCitizenTemplate(Citizen beCitizen)
    {
        // delete journals in db
        generalInfoDAO.delete(beCitizen.getJournal().getID());

        // delete entries in db
        new FunctionalAbilityDAO().delete(beCitizen.getID());
        new HealthConditionDAO().delete(beCitizen.getID());

        // delete citizen in db
        citizenDAO.delete(beCitizen.getID());
    }

    /**
     * Use the Java Faker library to generate a random name and Java.Random to generate a random age.
     * @return An Object Array of Strings with the generated date.
     */
    public static Object[] generateBaseData() {
        Faker faker = new Faker(new Locale("da-DK"));
        Object[] baseData = new Object[3];
        baseData[0] = faker.name().firstName();
        baseData[1] = faker.name().lastName();
        baseData[2] = 55 + new Random().nextInt(45) + "";

        return baseData;
    }

}
