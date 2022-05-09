package Application.BE.HealthJournal.Builder;

import Application.BE.HealthJournal.Journal;
import Application.DAL.FunctionalJournalDAO;
import Application.DAL.GeneralJournalDAO;
import Application.DAL.HealthJournalDAO;

public class JournalBuilder {

    public Journal buildJournal(int generalJournalId, int healthJournalId, int functionalJournalId)
    {
        Journal journal = new Journal();
        FunctionalJournalDAO funDAO = new FunctionalJournalDAO();
        GeneralJournalDAO genDAO = new GeneralJournalDAO();
        HealthJournalDAO heaDAO = new HealthJournalDAO();


        journal.setFunctionalJournal(funDAO.readFunctionalJournal(functionalJournalId));
        journal.setGeneralJournal(genDAO.readGeneralJournal(generalJournalId));
        journal.setHealthJournal(heaDAO.readHealthJournal(healthJournalId));

        return journal;
    }

}
