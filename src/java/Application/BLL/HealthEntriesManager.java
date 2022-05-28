package Application.BLL;

import Application.BE.Category;
import Application.BE.HealthEntry;
import Application.DAL.HealthConditionDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HealthEntriesManager extends ContentEntryManager<HealthEntry>
{


    public List<HealthEntry> getEntriesFor(int citizenId)
    {
        // get data from database.

        var data = citizenId == -1 ? new ArrayList<HealthEntry>() : new HealthConditionDAO().readAll(citizenId);

        // TODO: 28-05-2022 - use enum instead of int constant.
        var root = categoriesCache.stream().filter(category -> category.getID() == 3).findFirst().orElse(new Category("Helbredstilstande"));

        // create default health entries for each category that does not have an instance in the database.
        // does not update the database.
        for (Category intermediate : root.getChildren())
        {
            for (Category leaf : intermediate.getChildren())
            {
                if (data.stream().noneMatch(healthEntry -> Objects.equals(healthEntry.getCategory().getID(), leaf.getID())))
                {
                    var healthEntry = new HealthEntry(citizenId);
                    healthEntry.setCategory(leaf);
                    data.add(healthEntry);
                }
            }
        }

        // assign the category to each health entry that has been loaded from the database. and had its references updated.
        for (HealthEntry healthCondition : data)
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
