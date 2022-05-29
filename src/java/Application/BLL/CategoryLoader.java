package Application.BLL;

import Application.BE.Category;
import Application.BE.CategoryType;
import Application.BE.ContentEntry;
import Application.DAL.CategoryDAO;

import java.util.HashMap;
import java.util.List;

public class CategoryLoader
{
    CategoryDAO categoryDAO = new CategoryDAO();

    private void getImmediateChildren(Category root, List<Category> completeList) {
        for (var category : completeList) {
            if (category.getParentID() == root.getId()) {
                root.getChildren().add(category);
                category.setParent(root);
            }
        }
    }

    public Category load() {
        /// flat set of all categories.
        var allCategories = categoryDAO.readAll();
        Category content = new Category("root");
        for (var element : allCategories) {
            if (element.getParentID() <= 0) {
                content.getChildren().add(element);
            }
            getImmediateChildren(element, allCategories);
        }
        return content;
    }

    public HashMap<String, HashMap<Category, ContentEntry>> loadContent()
    {
        var allCategories = categoryDAO.readAll();
        Category content = new Category("root");
        for (var element : allCategories) {
            if (element.getParentID() <= 0) {
                content.getChildren().add(element);
            }
            getImmediateChildren(element, allCategories);
        }

        HashMap<String, HashMap<Category, ContentEntry>> returnMap = new HashMap<>();


        HashMap<Category, ContentEntry> healthMap = new HashMap<>();
        HashMap<Category, ContentEntry> funcMap = new HashMap<>();


        for (var cat : content.getChildren())
        {
            if (cat.getType().equals(CategoryType.FUNCTIONAL_ABILITY))
            funcMap.put(cat, new ContentEntry(cat.getId(), cat, 9));
            else if (cat.getType().equals(CategoryType.HEALTH_CONDITION))
            healthMap.put(cat, new ContentEntry(cat.getId(), cat, 0));
        }

        returnMap.put("health", healthMap);
        returnMap.put("func", funcMap);

        return returnMap;
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
                }
            }
        }
    }
}
