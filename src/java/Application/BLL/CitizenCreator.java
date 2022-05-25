package Application.BLL;

import Application.BE.*;
import Application.DAL.CitizenDAO;
import Application.DAL.ContentDAO;

import java.util.HashMap;
import java.util.stream.Collectors;

public class CitizenCreator
{
    ContentDAO contentDAO = new ContentDAO();
    CitizenDAO citizenDAO = new CitizenDAO();

    CitizenContentLinkDAO binder = new CitizenContentLinkDAO();

    public Citizen create (Citizen citizen, HashMap<Category, FunctionalEntry> entries)
    {
        // create citizen in db
        var qualified = citizenDAO.create(citizen);

        // create all entries from list in db
        for (var entry : entries.values())
        {
            entry.setID(contentDAO.create(entry).getID());
        }

        // setup binding
        var binding = new CitizenContentBinding();

        binding.citizenID = qualified.getID();
        binding.contentIDs = entries.values().stream().map(FunctionalEntry::getID).collect(Collectors.toList());
        // commit bind of one citizen and all entries
        binder.create(binding);

        // make it available to the citizen object instance
        qualified.setContent(entries);

        return qualified;
    }

    //public static void main(String[] args) {
    //    CitizenCreator creator = new CitizenCreator();
//
    //    Citizen citizen = new Citizen(0, new GeneralJournal(1), new School(1), "hello", "world", 1970);
//
    //    HashMap<Category, FunctionalEntry> entries = new HashMap<>();
//
//
    //    var cat = new Category(14, "Problemer med hukommelse", 0);
    //    var entry =  new FunctionalEntry(99, cat);
    //    entry.setCause("amnesia");
//
    //    entries.put(cat, entry);
//
    //    creator.create(citizen, entries);
    //}

}
