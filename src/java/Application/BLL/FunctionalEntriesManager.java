package Application.BLL;

import Application.BE.Category;
import Application.BE.FunctionalEntry;
import Application.DAL.FunctionalAbilityDAO;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 *
 * @author Mads Mandahl-Barth
 * */
public class FunctionalEntriesManager extends AbstractEntryManager<FunctionalEntry>
{
    @Override
    public List<FunctionalEntry> getEntriesFor(int citizenId)
    {
        var data = citizenId == -1 ? new ArrayList<FunctionalEntry>() : new FunctionalAbilityDAO().readAll(citizenId);

        if (data == null)
            data = new ArrayList<FunctionalEntry>();

        // TODO: 28-05-2022 - use enum instead of int constant.
        var root = categoriesCache.stream().filter(category -> category.getID() == 2).findFirst().orElse(new Category("Funktionsevnetilstande"));

        // create default health entries for each category that does not have an instance in the database.
        // does not update the database.
        for (Category intermediate : root.getChildren())
        {
            for (Category leaf : intermediate.getChildren())
            {
                if (data.stream().noneMatch(healthEntry -> Objects.equals(healthEntry.getCategory().getID(), leaf.getID())))
                {
                    var healthEntry = new FunctionalEntry(citizenId);
                    healthEntry.setCategory(leaf);
                    data.add(healthEntry);
                }
            }
        }

        // assign the category to each health entry that has been loaded from the database. and had its references updated.
        for (FunctionalEntry healthCondition : data)
        {
            Category found = healthCondition.getCategory();

            for (Category category : categoriesCache)
            {
                if (Objects.equals(category.getID(), healthCondition.getCategory().getID()))
                {
                    found = category;
                    break;
                }
            }

            healthCondition.setCategory(found);
        }

        return data;
    }
}
