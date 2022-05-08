package Application.BE.HealthJournal;

import java.util.HashMap;

public class GeneralJournal {
    HashMap<String, JournalEntry> journal;
    int id;

    public GeneralJournal()
    {

    }

    public void addJournal(String name, JournalEntry entry)
    {
        journal.put(name, entry);
    }

}
