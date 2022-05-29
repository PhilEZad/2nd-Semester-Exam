
import Application.BE.*;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.ControllerModels.CitizenTemplateControllerModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class EditingTest {
    /*
    Cannot test classes which contain JavaFX components, such as the CategoryEntryModel. The test needs to
    use a JavaFX thread, for which the below rule is required.
     */
    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();


    CitizenTemplateControllerModel model;


    @Before
    public void setUp(){
        model = new CitizenTemplateControllerModel();

        //Citizen Template
        CitizenModel citizenTemplateModel = new CitizenModel(new Citizen(-1, new School(-1, "school", 0000, "City"), "Firstname", "Lastname", 0, true));

        //Lists
        ObservableList<CategoryEntryModel> healthConditionsRelevant = FXCollections.observableArrayList();
        ObservableList<CategoryEntryModel> funcAbilitiesRelevant = FXCollections.observableArrayList();
        ObservableList<CategoryEntryModel> healthConditionsNonRelevant = FXCollections.observableArrayList();
        ObservableList<CategoryEntryModel> funcAbilitiesNonRelevant = FXCollections.observableArrayList();


        //Category Structures
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
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subA1, "Assesment", "Cause", "Note", HealthLevels.RELEVANT.ordinal(), HealthLevels.NOT_RELEVANT.ordinal())));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subA2, "Assesment", "Cause", "Note", HealthLevels.RELEVANT.ordinal(), HealthLevels.RELEVANT.ordinal())));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subA3, "Assesment", "Cause", "Note", HealthLevels.RELEVANT.ordinal(), HealthLevels.NOT_RELEVANT.ordinal())));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subA4, "Assesment", "Cause", "Note", HealthLevels.POSSIBLE_RELEVANT.ordinal(), HealthLevels.RELEVANT.ordinal())));

        categoryEntries.add(new FunctionalEntry(-1, superB, -1));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subB1, "Assesment", "Cause", "Note", HealthLevels.NOT_RELEVANT.ordinal(), HealthLevels.NOT_RELEVANT.ordinal())));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subB2, "Assesment", "Cause", "Note", HealthLevels.NOT_RELEVANT.ordinal(), HealthLevels.NOT_RELEVANT.ordinal())));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subB3, "Assesment", "Cause", "Note", HealthLevels.NOT_RELEVANT.ordinal(), HealthLevels.NOT_RELEVANT.ordinal())));
        healthConditionsRelevant.add(new CategoryEntryModel(new HealthEntry(-1, -1, subB4, "Assesment", "Cause", "Note", HealthLevels.NOT_RELEVANT.ordinal(), HealthLevels.NOT_RELEVANT.ordinal())));

        categoryEntries.add(new FunctionalEntry(-1, superC, -1));
        funcAbilitiesRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subC1, FunctionalLevels.LEVEL_1.level)));
        funcAbilitiesRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subC2, FunctionalLevels.LEVEL_4.level)));
        funcAbilitiesRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subC3, FunctionalLevels.LEVEL_0.level)));
        funcAbilitiesRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subC4, FunctionalLevels.LEVEL_2.level)));

        categoryEntries.add(new FunctionalEntry(-1, superD, -1));
        funcAbilitiesNonRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subD1, FunctionalLevels.LEVEL_9.level)));
        funcAbilitiesNonRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subD2, FunctionalLevels.LEVEL_9.level)));
        funcAbilitiesNonRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subD3, FunctionalLevels.LEVEL_9.level)));
        funcAbilitiesNonRelevant.add(new CategoryEntryModel(new FunctionalEntry(-1, subD4, FunctionalLevels.LEVEL_9.level)));


        //Categories
        healthConditionsRelevant.add(new CategoryEntryModel("condition 1", HealthLevels.RELEVANT.ordinal(), "note"));
        healthConditionsRelevant.add(new CategoryEntryModel("condition 2", HealthLevels.RELEVANT.ordinal(), "note"));
        healthConditionsRelevant.add(new CategoryEntryModel("condition 3", HealthLevels.RELEVANT.ordinal(), "note"));
        healthConditionsRelevant.add(new CategoryEntryModel("condition 4", HealthLevels.RELEVANT.ordinal(), "note"));

        healthConditionsNonRelevant.add(new CategoryEntryModel("non-condition 1", HealthLevels.NOT_RELEVANT.ordinal(), "note"));
        healthConditionsNonRelevant.add(new CategoryEntryModel("non-condition 2", HealthLevels.NOT_RELEVANT.ordinal(), "note"));

        funcAbilitiesRelevant.add(new CategoryEntryModel("functional ability 1", FunctionalLevels.LEVEL_1.level, "note"));
        funcAbilitiesRelevant.add(new CategoryEntryModel("functional ability 2", FunctionalLevels.LEVEL_4.level, "note"));
        funcAbilitiesRelevant.add(new CategoryEntryModel("functional ability 3", FunctionalLevels.LEVEL_0.level, "note"));
        funcAbilitiesRelevant.add(new CategoryEntryModel("functional ability 4", FunctionalLevels.LEVEL_2.level, "note"));

        funcAbilitiesNonRelevant.add(new CategoryEntryModel("non-functional ability 1", FunctionalLevels.LEVEL_9.level, "note"));
        funcAbilitiesNonRelevant.add(new CategoryEntryModel("non-functional ability 2", FunctionalLevels.LEVEL_9.level, "note"));

        //Set the categories
        citizenTemplateModel.setRelevantHealthConditions(healthConditionsRelevant);
        citizenTemplateModel.setNonRelevantHealthConditions(healthConditionsNonRelevant);
        citizenTemplateModel.setRelevantFunctionalAbilities(funcAbilitiesRelevant);
        citizenTemplateModel.setNonRelevantFunctionalAbilities(funcAbilitiesNonRelevant);

        //Set the selected citizen like in the actual gui
        model.setSelectedCitizenTemplateModel(citizenTemplateModel);
        model.savePreEditState(); //Clone the pre-edit state of the selected model for later comparison
    }

    //assert that categories are marked as relevant and non-relevant
    @Test
    public void changedCategories(){
        CitizenModel selected = model.getSelectedCitizenTemplateModel(); //Selected citizen
        assertEquals(4, selected.getRelevantFunctionalAbilities().size());

        //Edits
        selected.getRelevantFunctionalAbilities().get(0).setLevel(FunctionalLevels.LEVEL_9.level);
        selected.getRelevantFunctionalAbilities().get(1).setLevel(FunctionalLevels.LEVEL_9.level);
        selected.getNonRelevantFunctionalAbilities().get(1).setLevel(FunctionalLevels.LEVEL_3.level);
        selected.getRelevantHealthConditions().get(1).setLevel(HealthLevels.NOT_RELEVANT.ordinal());
        selected.getRelevantHealthConditions().get(2).setLevel(HealthLevels.NOT_RELEVANT.ordinal());
        selected.getNonRelevantHealthConditions().get(1).setLevel(HealthLevels.POSSIBLE_RELEVANT.ordinal());


        model.saveEditedCitizenTemplate();

        assertEquals(5, selected.getRelevantFunctionalAbilities().size());
        assertEquals(3, selected.getNonRelevantFunctionalAbilities().size());
        assertEquals(3, selected.getRelevantFunctionalAbilities().size());
        assertEquals(5, selected.getNonRelevantFunctionalAbilities().size());


    }



}
