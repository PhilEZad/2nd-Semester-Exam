package Application.BLL;

import Application.BE.Category;
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

    public Category load()
    {
        /// flat set of all categories.
        var allCategories =  categoryDAO.readAll();

        Category content = new Category("root");

        for (var element : allCategories)
        {
            if (element.getParentID() <= 0)
            {
                content.getChildren().add(element);
                element.setParent(content);
            }

            getImmediateChildren(element, allCategories);
        }

        return content;
    }

    public static void main(String[] args) {

        CategoryLoader loader = new CategoryLoader();

        System.out.println("/t/t/t" + " : " + loader.load());

        for (var Level0 : loader.load().getChildren()) {
            System.out.println(Level0.getName());

            for (var Level1 : Level0.getChildren()) {
                System.out.println("\t" + Level1.getName());

                for (var Level2 : Level1.getChildren()) {
                    System.out.println("\t\t" + Level2.getName());
                    System.out.println("\t\t\t" + Level2.getParent().getName());
                }
            }
        }
    }
}
