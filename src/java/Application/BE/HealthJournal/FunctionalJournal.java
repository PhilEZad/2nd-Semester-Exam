package Application.BE.HealthJournal;

import java.util.HashMap;

public class FunctionalJournal {

    /*
    http://www.fs3.nu/filer/Dokumenter/Metode/FSIII-Metodeh%C3%A5ndbog.pdf?t=1651696577
    funktional State reguire 5 points of dokumentation:
    condition classification: 7.2.3.6
         the sub cateogry of funktional states.
    state of condition: 7.2.3.7
         from a scale of 0 - 4 + 9
    assessment: 7.2.3.8
         ?? same scale as "state of condition" ??
    cause: 7.2.3.9
        documents by freewriting a comment
    Professional note: 7.2.3.10
        documents by freewriting a comment
     */

    HashMap<String, JournalEntry> journal;


    public FunctionalJournal() {

    }

    public void addJournal(String name, JournalEntry entry)
    {
        this.journal.put(name,entry);
    }
}
