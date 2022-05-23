
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
/*
        //Citizen Template
        CitizenModel citizenTemplateModel = new CitizenModel("First Name", "Last Name", 90);

        //Lists
        ObservableList<CategoryEntryModel> healthConditionsRelevant = FXCollections.observableArrayList();
        ObservableList<CategoryEntryModel> funcAbilitiesRelevant = FXCollections.observableArrayList();
        ObservableList<CategoryEntryModel> healthConditionsNonRelevant = FXCollections.observableArrayList();
        ObservableList<CategoryEntryModel> funcAbilitiesNonRelevant = FXCollections.observableArrayList();

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
        selected.getRelevantHealthConditions().get(1).setNote("A different note");
        selected.getNonRelevantHealthConditions().get(1).setLevel(HealthLevels.POSSIBLE_RELEVANT.ordinal());

        model.saveEditedCitizenTemplate();

        assertEquals(3, selected.getRelevantFunctionalAbilities().size());

 */
    }


    //assert that changes made to model are reflected in BE
    @Test
    public void ModelAndBEFields(){
        CitizenModel selected = model.getSelectedCitizenTemplateModel();
        assertEquals("First Name", selected.getFirstName());
        assertEquals("First Name", selected.getFirstName());
        assertEquals("First Name", selected.getBeCitizen().getFirstname());


        selected.setFirstName("New First Name");
        assertEquals("New First Name", selected.getFirstName());
        assertFalse("Name is changed", !"First Name".equals(selected.getFirstName()));

    }

}
