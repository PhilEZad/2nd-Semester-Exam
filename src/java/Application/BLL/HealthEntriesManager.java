package Application.BLL;

import Application.BE.Category;
import Application.BE.HealthEntry;
import Application.DAL.HealthConditionDAO;
import Application.Utility.GUIUtils;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * @author Mads Mandahl-Barth
 * */
public class HealthEntriesManager extends AbstractEntryManager<HealthEntry>
{
    public List<HealthEntry> getEntriesFor(int citizenId) {
        // get data from database.
        Callable<List<HealthEntry>> callable = () -> {

        var data = citizenId == -1 ? new ArrayList<HealthEntry>() : new HealthConditionDAO().readAll(citizenId);

        if (data == null)
            data = new ArrayList<HealthEntry>();

        // TODO: 28-05-2022 - use enum instead of int constant.
        var root = categoriesCache.stream().filter(category -> category.getID() == 3).findFirst().orElse(new Category("Helbredstilstande"));

        // create default health entries for each category that does not have an instance in the database.
        // does not update the database.
        for (Category intermediate : root.getChildren()) {
            for (Category leaf : intermediate.getChildren()) {
                if (data.stream().noneMatch(healthEntry -> Objects.equals(healthEntry.getCategory().getID(), leaf.getID()))) {
                    var healthEntry = new HealthEntry(citizenId);
                    healthEntry.setCategory(leaf);
                    data.add(healthEntry);
                }
            }
        }


        // assign the category to each health entry that has been loaded from the database. and had its references updated.
        for (HealthEntry healthCondition : data) {
            Category found = healthCondition.getCategory();

            for (Category category : categoriesCache) {
                if (Objects.equals(category.getID(), healthCondition.getCategory().getID())) {
                    found = category;
                    break;
                }
            }

            healthCondition.setCategory(found);
        }
        return data;
    };


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            return executorService.submit(callable).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            GUIUtils.alertCall(Alert.AlertType.ERROR, "Fejl ved hentning af FS3 data");
            return new ArrayList<>();
        }
    }
}
