import Application.BE.Category;
import Application.BE.CategoryType;
import Application.BE.FunctionalEntry;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.FunctionalLevels;
import Application.Utility.GUIUtils;
import javafx.scene.control.TreeItem;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
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



    private HashMap<Category, CategoryEntryModel> categoryEntryModelHashMap;

    @Before
    public void setUp(){
        categoryEntryModelHashMap = new HashMap<>();

        //Categories with parents and children
        Category superSuperCategory = new Category(-1, "All Conditions", -1,  CategoryType.UNKNOWN);

        Category superA = new Category(-1, "Conditions A", -1, CategoryType.UNKNOWN);
        Category subA1 = new Category(-1, "Condition A.1", -1,  CategoryType.UNKNOWN);
        Category subA2 = new Category(-1, "Condition A.2", -1,  CategoryType.UNKNOWN);
        Category subA3 = new Category(-1, "Condition A.3", -1,  CategoryType.UNKNOWN);
        Category subA4 = new Category(-1, "Condition A.4", -1,  CategoryType.UNKNOWN);

        Category superB = new Category(-1, "Conditions B", -1,  CategoryType.UNKNOWN);
        Category subB1 = new Category(-1, "Condition B.1", -1,  CategoryType.UNKNOWN);
        Category subB2 = new Category(-1, "Condition B.2", -1,  CategoryType.UNKNOWN);
        Category subB3 = new Category(-1, "Condition B.3", -1,  CategoryType.UNKNOWN);
        Category subB4 = new Category(-1, "Condition B.4", -1,  CategoryType.UNKNOWN);

        Category superC = new Category(-1, "Conditions C", -1,  CategoryType.UNKNOWN);
        Category subC1 = new Category(-1, "Condition C.1", -1,  CategoryType.UNKNOWN);
        Category subC2 = new Category(-1, "Condition C.2", -1,  CategoryType.UNKNOWN);
        Category subC3 = new Category(-1, "Condition C.3", -1,  CategoryType.UNKNOWN);
        Category subC4 = new Category(-1, "Condition C.4", -1,  CategoryType.UNKNOWN);

        Category superD = new Category(-1, "Conditions D", -1,  CategoryType.UNKNOWN);
        Category subD1 = new Category(-1, "Condition D.1", -1,  CategoryType.UNKNOWN);
        Category subD2 = new Category(-1, "Condition D.2", -1,  CategoryType.UNKNOWN);
        Category subD3 = new Category(-1, "Condition D.3", -1,  CategoryType.UNKNOWN);
        Category subD4 = new Category(-1, "Condition D.4", -1,  CategoryType.UNKNOWN);

        superSuperCategory.setParent(null);
        superSuperCategory.addChild(superA);
        superSuperCategory.addChild(superB);
        superSuperCategory.addChild(superC);
        superSuperCategory.addChild(superD);

        superA.setParent(superSuperCategory);
        superA.addChild(subA1);
        superA.addChild(subA2);
        superA.addChild(subA3);
        superA.addChild(subA4);
        subA1.setParent(superA);
        subA2.setParent(superA);
        subA3.setParent(superA);
        subA4.setParent(superA);

        superB.setParent(superSuperCategory);
        superB.addChild(subB1);
        superB.addChild(subB2);
        superB.addChild(subB3);
        superB.addChild(subB4);
        subB1.setParent(superB);
        subB2.setParent(superB);
        subB3.setParent(superB);
        subB4.setParent(superB);

        superC.setParent(superSuperCategory);
        superC.addChild(subC1);
        superC.addChild(subC2);
        superC.addChild(subC3);
        superC.addChild(subC4);
        subC1.setParent(superC);
        subC2.setParent(superC);
        subC3.setParent(superC);
        subC4.setParent(superC);

        superD.setParent(superSuperCategory);
        superD.addChild(subD1);
        superD.addChild(subD2);
        superD.addChild(subD3);
        superD.addChild(subD4);
        subD1.setParent(superD);
        subD2.setParent(superD);
        subD3.setParent(superD);
        subD4.setParent(superD);

        //Create ContentEntrys from Categories in a list
        List<FunctionalEntry> categoryEntries = new ArrayList<>();
        categoryEntries.add(new FunctionalEntry(-1, superA, -1));
        categoryEntries.add(new FunctionalEntry(-1, subA1, FunctionalLevels.LEVEL_1.level));
        categoryEntries.add(new FunctionalEntry(-1, subA2, FunctionalLevels.LEVEL_4.level));
        categoryEntries.add(new FunctionalEntry(-1, subA3, FunctionalLevels.LEVEL_0.level));
        categoryEntries.add(new FunctionalEntry(-1, subA4, FunctionalLevels.LEVEL_2.level));

        categoryEntries.add(new FunctionalEntry(-1, superB, -1));
        categoryEntries.add(new FunctionalEntry(-1, subB1, FunctionalLevels.LEVEL_1.level));
        categoryEntries.add(new FunctionalEntry(-1, subB2, FunctionalLevels.LEVEL_4.level));
        categoryEntries.add(new FunctionalEntry(-1, subB3, FunctionalLevels.LEVEL_0.level));
        categoryEntries.add(new FunctionalEntry(-1, subB4, FunctionalLevels.LEVEL_2.level));

        categoryEntries.add(new FunctionalEntry(-1, superC, -1));
        categoryEntries.add(new FunctionalEntry(-1, subC1, FunctionalLevels.LEVEL_1.level));
        categoryEntries.add(new FunctionalEntry(-1, subC2, FunctionalLevels.LEVEL_4.level));
        categoryEntries.add(new FunctionalEntry(-1, subC3, FunctionalLevels.LEVEL_0.level));
        categoryEntries.add(new FunctionalEntry(-1, subC4, FunctionalLevels.LEVEL_2.level));

        categoryEntries.add(new FunctionalEntry(-1, superD, -1));
        categoryEntries.add(new FunctionalEntry(-1, subD1, FunctionalLevels.LEVEL_1.level));
        categoryEntries.add(new FunctionalEntry(-1, subD2, FunctionalLevels.LEVEL_4.level));
        categoryEntries.add(new FunctionalEntry(-1, subD3, FunctionalLevels.LEVEL_0.level));
        categoryEntries.add(new FunctionalEntry(-1, subD4, FunctionalLevels.LEVEL_2.level));

        //Create CategoryEntryModels from the FunctionalEntry list and put the in a map with the category as key
        categoryEntryModelHashMap.put(superSuperCategory, new CategoryEntryModel(new FunctionalEntry(-1, superSuperCategory, -1)));
        for (FunctionalEntry contentEntry : categoryEntries) {
            categoryEntryModelHashMap.put(contentEntry.getCategory(), new CategoryEntryModel(contentEntry));
        }

    }

    //assert that the tree structure is correct
    @Test
    public void setTreeStructureHierachy(){

        TreeItem<CategoryEntryModel> root = GUIUtils.mapToTreeItem(categoryEntryModelHashMap);

        CategoryEntryModel rootValue = root.getValue();
        TreeItem<CategoryEntryModel> superCategory = root.getChildren().get(0);
        TreeItem<CategoryEntryModel> subCategory = superCategory.getChildren().get(0);

        assertEquals("All Conditions", rootValue.getCategoryName());
        assertEquals("Conditions A", superCategory.getValue().getCategoryName());
        assertEquals("Condition A.1", subCategory.getValue().getCategoryName());
        assertEquals(0, subCategory.getChildren().size());
    }

}
