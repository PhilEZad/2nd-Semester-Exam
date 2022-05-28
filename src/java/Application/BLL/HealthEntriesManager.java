package Application.BLL;

import Application.BE.Category;
import Application.BE.HealthEntry;
import Application.DAL.HealthConditionDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HealthEntriesManager
{
    public List<HealthEntry> getHealthEntries(int citizenId)
    {
        // get data from database.
        var categories = new CategoryLoader().load();

        var data = citizenId == -1 ? new ArrayList<HealthEntry>() : new HealthConditionDAO().readAll(citizenId);

        // create default health entries for each category that does not have an instance in the database.
        // does not update the database.
        for (Category category : categories)
        {
            if (data.stream().noneMatch(healthEntry -> Objects.equals(healthEntry.getCategory().getID(), category.getID())))
            {
                var healthEntry = new HealthEntry(citizenId);
                healthEntry.setCategory(category);
                data.add(healthEntry);
            }
        }

        // assign the category to each health entry that has been loaded from the database. and had its references updated.
        for (HealthEntry healthCondition : data)
        {
            Category found = healthCondition.getCategory();

            for (Category category : categories)
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
