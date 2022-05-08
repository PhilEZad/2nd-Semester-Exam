package Application.BE.HealthJournal;

import java.util.HashMap;

public class HealthJournal {

    /*
    http://www.fs3.nu/filer/Dokumenter/Metode/FSIII-Metodeh%C3%A5ndbog.pdf?t=1651696577
    health state requires 5 points of documentation


     */
    HashMap<String, JournalEntry> journal;

    int id;

    public HealthJournal() {
    }

    public void addJournal(String name, JournalEntry entry) {
        this.journal.put(name,entry);
    }
}
