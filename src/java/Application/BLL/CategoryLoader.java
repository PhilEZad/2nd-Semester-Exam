package Application.BLL;

import Application.BE.Category;
import Application.DAL.CategoryDAO;
import Application.Utility.GUIUtils;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * loads categories from the database, and creates the relation between them.
 *
 * @author Mads Mandahl-Barth
 * */
public class CategoryLoader
{
    CategoryDAO categoryDAO = new CategoryDAO();

    // Loads all categories from the database
    // Returns a Category object


    public List<Category> load()
    {
        Callable<List<Category>> callable = () -> {

            /// flat set of all categories.
            var allCategories = categoryDAO.readAll();

            var root = new Category("root");

            for (var element : allCategories) {
                if (element.getParentID() == 0) {
                    element.setParent(root);
                    element.getParent().setID(0);
                }

                for (var category : allCategories) {
                    if (category.getParentID() == element.getID()) {
                        element.getChildren().add(category);
                        category.setParent(element);
                    }
                }
            }
            return allCategories;
        };


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            return executorService.submit(callable).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            GUIUtils.alertCall(Alert.AlertType.ERROR, "Fejl ved læsning af kategorier");
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {

        CategoryLoader loader = new CategoryLoader();

        int lastChange = 0;

        for (var Level0 : loader.load())
        {
            if (Level0.getParent().getID() != lastChange)
            {
                System.out.println();
                lastChange = Level0.getParent().getID();
            }

            System.out.println("parent: [" + Level0.getParent().getID() + "] " + Level0.getParent().getName() + " -> current: [" + Level0.getID() + "] " + Level0.getName());
        }
    }
}
