package Application.BLL;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BE.GeneralJournal;
import Application.DAL.CitizenDAO;

import java.util.List;

public class CitizenManager
{
    private static final HealthEntriesManager healthEntriesManager = new HealthEntriesManager();

    private static final CitizenDAO citizenDAO = new CitizenDAO();

    public List<Citizen> getAllTemplates()
    {
        return citizenDAO.readAll(SessionManager.getCurrent().getSchool()).stream().filter(Citizen::getTemplate).collect(java.util.stream.Collectors.toList());
    }



    public Citizen createCitizenTemplate()
    {
        Citizen citizen = new Citizen(-1, new GeneralJournal(), SessionManager.getCurrent().getSchool(), "fornavn", "efternavn", 70, true);

        for (var entry : healthEntriesManager.getHealthEntries(-1))
        {
            citizen.addHealthConditions(entry);
        }

        return citizenDAO.create(citizen);
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
