
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TreeHierachyTest {
    /*
  Cannot test classes which contain JavaFX components, such as the CategoryModel. The test needs to
  use a JavaFX thread, for which the below rule is required.
   */
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
/*

    ObservableList<CategoryModel> CategoryModels;

    @Before
    public void setUp(){
        //Lists
        CategoryModels = FXCollections.observableArrayList();


        //Categories
        Category superSuperCategory = new Category(-1, "All Conditions", null);

        Category superA = new Category(-1, "Conditions A",superSuperCategory);
        Category subA1 = new Category(-1, "Condition A.1", superA);
        Category subA2 = new Category(-1, "Condition A.2", superA);
        Category subA3 = new Category(-1, "Condition A.3", superA);
        Category subA4 = new Category(-1, "Condition A.4", superA);

        Category superB = new Category(-1, "Conditions B", superSuperCategory);
        Category subB1 = new Category(-1, "Condition B.1", superB);
        Category subB2 = new Category(-1, "Condition B.2", superB);
        Category subB3 = new Category(-1, "Condition B.3", superB);
        Category subB4 = new Category(-1, "Condition B.4", superB);

        Category superC = new Category(-1, "Conditions C", superSuperCategory);
        Category subC1 = new Category(-1, "Condition C.1", superC);
        Category subC2 = new Category(-1, "Condition C.2", superC);
        Category subC3 = new Category(-1, "Condition C.3", superC);
        Category subC4 = new Category(-1, "Condition C.4", superC);

        Category superD = new Category(-1, "Conditions D", superSuperCategory);
        Category subD1 = new Category(-1, "Condition D.1", superD);
        Category subD2 = new Category(-1, "Condition D.2", superD);
        Category subD3 = new Category(-1, "Condition D.3", superD);
        Category subD4 = new Category(-1, "Condition D.4", superD);

        List<Category> categoryEntries = new ArrayList<>();
        categoryEntries.add(new Category(-1, superA));
        categoryEntries.add(new Category(-1, subA1));
        categoryEntries.add(new Category(-1, subA2));
        categoryEntries.add(new Category(-1, subA3));
        categoryEntries.add(new Category(-1, subA4));

        categoryEntries.add(new Category(-1, superB));
        categoryEntries.add(new Category(-1, subB1));
        categoryEntries.add(new Category(-1, subB2));
        categoryEntries.add(new Category(-1, subB3));
        categoryEntries.add(new Category(-1, subB4));

        categoryEntries.add(new Category(-1, superC));
        categoryEntries.add(new Category(-1, subC1));
        categoryEntries.add(new Category(-1, subC2));
        categoryEntries.add(new Category(-1, subC3));
        categoryEntries.add(new Category(-1, subC4));

        categoryEntries.add(new Category(-1, superD));
        categoryEntries.add(new Category(-1, subD1));
        categoryEntries.add(new Category(-1, subD2));
        categoryEntries.add(new Category(-1, subD3));
        categoryEntries.add(new Category(-1, subD4));


        CategoryModels.add(new CategoryModel(new Category(-1, superSuperCategory)));
        categoryEntries.forEach(Category -> CategoryModels.add(new CategoryModel(Category)));
    }

    //assert that the tree structure is correct
    @Test
    public void setTreeStructureHierachy(){
        //Collections.shuffle(CategoryModels);
        TreeItem<CategoryModel> root = GUIUtils.setCategoryHierachy2(CategoryModels);

        CategoryModel rootValue = root.getValue();
        TreeItem<CategoryModel> superSuperCategory = root.getChildren().get(0);
        TreeItem<CategoryModel> superCategory = superSuperCategory.getChildren().get(0);
        TreeItem<CategoryModel> subCategory = superCategory.getChildren().get(0);

        System.out.println(rootValue.getCategoryName() + " SIZE: " + root.getChildren().size());
        System.out.println("");
        System.out.println(superSuperCategory.getValue().getCategoryName() + " SIZE: " + superSuperCategory.getChildren().size());
        System.out.println(superCategory.getValue().getCategoryName() + " SIZE: " + superCategory.getChildren().size());
        System.out.println(subCategory.getValue().getCategoryName() + " SIZE: " + subCategory.getChildren().size());


        assertEquals("Tilstande", rootValue.getCategoryName());
        assertEquals("All Conditions", superSuperCategory.getValue().getCategoryName());
        assertEquals("Conditions A", superCategory.getValue().getCategoryName());
        assertEquals("Condition A.1", subCategory.getValue().getCategoryName());
        assertEquals(0, subCategory.getChildren().size());
    }

 */
}
