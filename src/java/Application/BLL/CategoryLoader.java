package Application.BLL;

import Application.BE.Category;
import Application.BE.CategoryType;
import Application.DAL.CategoryDAO;

import java.util.ArrayList;
import java.util.List;

public class CategoryLoader
{
    CategoryDAO categoryDAO = new CategoryDAO();

    private void getImmediateChildren(Category root, List<Category> completeList)
    {
        for (var category : completeList)
        {
            if (category.getParentID() == root.getID())
            {
                root.getChildren().add(category);
                category.setParent(root);
            }
        }
    }

    // Loads all categories from the database
    // Returns a Category object

    public List<Category> load()
    {
        /// flat set of all categories.
        var allCategories =  categoryDAO.readAll();

        for (var element : allCategories)
        {
            if (element.getParentID() == 0)
            {
                element.setParent(new Category("root"));
                element.getParent().setID(0);
            }

            for (var category : allCategories)
            {
                if (category.getParentID() == element.getID())
                {
                    element.getChildren().add(category);
                    category.setParent(element);
                }
            }
        }

        return allCategories;
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
