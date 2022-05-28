package Application.BLL;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BE.FunctionalEntry;
import Application.BE.GeneralJournal;
import Application.DAL.CitizenDAO;
import Application.DAL.GeneralInformationDAO;

import java.util.List;

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
        // create citizen in db
        Citizen citizen = citizenDAO.create(new Citizen(-1, SessionManager.getCurrent().getSchool(), "fornavn", "efternavn", 70, true));

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
        // update journals in db

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
        // if template
            // create citizen in db
            // get concrete citizen (i.e. clone of template with id from db)
            // clone general/health/functional journals


        // add citizen to account
    }
}
