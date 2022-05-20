package Application.BLL;

import Application.BE.*;
import Application.DAL.CitizenContentLinkDAO;
import Application.DAL.CitizenDAO;
import Application.DAL.ContentDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CitizenCreator
{
    ContentDAO contentDAO = new ContentDAO();
    CitizenDAO citizenDAO = new CitizenDAO();

    CitizenContentLinkDAO binder = new CitizenContentLinkDAO();

    public Citizen create (Citizen citizen, List<ContentEntry> entries)
    {
        var qualified = citizenDAO.create(citizen);
        var content = contentDAO.create(entries);

        var binding = new CitizenContentBinding();

        binding.citizenID = qualified.getId();
        binding.contentIDs = content.stream().map(ContentEntry::getId).collect(Collectors.toList());

        binder.create(binding);

        qualified.setContent(content);

        return qualified;
    }

    public static void main(String[] args) {
        CitizenCreator creator = new CitizenCreator();

        Citizen citizen = new Citizen(0, new GeneralJournal(1), new School(1), "hello", "world", 1970);

        List<ContentEntry> entries = new ArrayList<>();
        var con1 = new ContentEntry(new Category(14, "Problemer med hukommelse", 0));

        con1.setCause("amnesia");

        entries.add(con1);


        creator.create(citizen, entries);
    }

}
