package Application.GUI.Models.ControllerModels;

import Application.BE.Category;
import Application.BE.FunctionalEntry;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.HealthLevels;
import Application.Utility.GUIUtils;
import com.github.javafaker.Faker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class CitizenTemplateControllerModel {

    private TeacherDataManager teacherDataManager;

    private CitizenModel selectedCitizenTemplateModel;

    //Copy from before the editMode is activated
    private CitizenModel preEditCitizenTemplateModel;

    public CitizenTemplateControllerModel() {
        teacherDataManager = new TeacherDataManager();
    }


    /**
     * Get all the citizen templates from the DB and put them in a list.
     * @return
     */
    public ObservableList<CitizenModel> getCitizenTemplates() {
        ObservableList<CitizenModel> citizenTemplates = FXCollections.observableArrayList(teacherDataManager.getAllCitizenTemplates());
        return citizenTemplates;
    }

    /**
     * Set the selected citizen template.
     **/
    public void setSelectedCitizenTemplateModel(CitizenModel selectedCitizenTemplateModel) {
        this.selectedCitizenTemplateModel = selectedCitizenTemplateModel;
    }

    /**
     * Get the selected citizen template.
     **/
    public CitizenModel getSelectedCitizenTemplateModel() {
        return selectedCitizenTemplateModel;
    }

    public TreeItem<CategoryEntryModel> getAllFuncCategoriesAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Functional Ability Categories"));
        return GUIUtils.mapToTreeItem(selectedCitizenTemplateModel.getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditionsAsTreeItem() {
        TreeItem<CategoryEntryModel> treeItem = new TreeItem<>(new CategoryEntryModel("All Health Categories"));
        return GUIUtils.mapToTreeItem(selectedCitizenTemplateModel.getAllHealthConditions());
    }


    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(selectedCitizenTemplateModel.getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(selectedCitizenTemplateModel.getRelevantHealthConditions());
    }



    /**
     * Create a new citizen template and write it to the DB.
     * Returns the instance for it to be added to the list in the GUI.
     * @return
     */
    public CitizenModel newCitizenTemplate() {
        CitizenModel CitizenTemplateModel = new CitizenModel();

        return CitizenTemplateModel;
    }

    /**
     * Delete the selected citizen template.
     **/
    public void deleteCitizenTemplate() {
        teacherDataManager.deleteCitizenTemplate(selectedCitizenTemplateModel.getBeCitizen());
    }

    /**
     * Creates a copy of the citizen template and writes it to the DB.
     */
    public CitizenModel copyCitizenTemplate() {
        CitizenModel clone = null;
        try {
            clone = (CitizenModel) selectedCitizenTemplateModel.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //teacherDataManager.copyTemplate(clone.getTemplate());
        return clone;
    }


    /**
     * Creates a copy of the citizen template and stores it in the preEditCitizenTemplateModel variable for later user.
     */
    public void savePreEditState() {
        try {
            this.preEditCitizenTemplateModel = (CitizenModel) selectedCitizenTemplateModel.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save all the edits to the citizen template to the DB.
     */
    public void saveEditedCitizenTemplate() {
        if (preEditCitizenTemplateModel != null) {

            HashMap<Category, CategoryEntryModel> newHealthRoot = new HashMap<>();
            newHealthRoot.putAll(selectedCitizenTemplateModel.getAllHealthConditions());

            HashMap<Category, CategoryEntryModel> newFuncRoot = new HashMap<>();
            newFuncRoot.putAll(selectedCitizenTemplateModel.getAllFuncCategories());

            HashMap<Category, CategoryEntryModel> newRelevantHealthConditions = new HashMap<>();
            HashMap<Category, CategoryEntryModel> newRelevantFunctionalAbilities = new HashMap<>();
            HashMap<Category, CategoryEntryModel> newNonRelevantHealthConditions = new HashMap<>();
            HashMap<Category, CategoryEntryModel> newNonRelevantFunctionalAbilities = new HashMap<>();

            //Put relevant and non-relevant health categories into their respective lists.
            for (CategoryEntryModel newHealth : newHealthRoot.values()) {
                if (newHealth.getLevel() != HealthLevels.NOT_RELEVANT.ordinal()) {
                    newRelevantHealthConditions.put(newHealth.getContentEntry().getCategory(), newHealth);
                }
                else {
                    newNonRelevantHealthConditions.put(newHealth.getContentEntry().getCategory(), newHealth);
                }
            }

            //Put relevant and non-relevant functional categories into their respective lists.
            for (CategoryEntryModel newFunc : newFuncRoot.values()) {
                if (newFunc.getLevel() != FunctionalLevels.LEVEL_9.ordinal() && newFunc.getLevel() != FunctionalLevels.LEVEL_9.level) {
                    newRelevantFunctionalAbilities.put(newFunc.getContentEntry().getCategory(), newFunc);
                }
                else {
                    newNonRelevantFunctionalAbilities.put(newFunc.getContentEntry().getCategory(), newFunc);
                }
            }

            selectedCitizenTemplateModel.setRelevantHealthConditions(newRelevantHealthConditions); //Relevant health
            selectedCitizenTemplateModel.setRelevantFunctionalAbilities(newRelevantFunctionalAbilities); //Relevant Functional

            selectedCitizenTemplateModel.setNonRelevantHealthConditions(newNonRelevantHealthConditions); //Non-Relevant Health
            selectedCitizenTemplateModel.setNonRelevantFunctionalAbilities(newNonRelevantFunctionalAbilities); //Non-Relevant Functional

            HashMap<Category, CategoryEntryModel> allOldHealth = new HashMap<>();
            allOldHealth.putAll(preEditCitizenTemplateModel.getAllHealthConditions());
            HashMap<Category, CategoryEntryModel> allOldFunc = new HashMap<>();
            allOldFunc.putAll(preEditCitizenTemplateModel.getAllFuncCategories());


            HashMap<Category, CategoryEntryModel> dbWriteHealthConditions = new HashMap<>();
            dbWriteHealthConditions.putAll(selectedCitizenTemplateModel.getAllHealthConditions());
            HashMap<Category, CategoryEntryModel> dbWriteFunctionalAbilities = new HashMap<>();
            dbWriteFunctionalAbilities.putAll(selectedCitizenTemplateModel.getAllFuncCategories());


            //List of changed health conditions. If no changes are made to the item, it will be removed from the list.
            for (Category health : dbWriteHealthConditions.keySet()) {
                CategoryEntryModel oldIndex = allOldHealth.get(health);
                CategoryEntryModel newIndex = dbWriteHealthConditions.get(health);
                if (oldIndex != null && newIndex != null) {
                    int compare = oldIndex.compareTo(newIndex);
                    if (compare == 0) {
                        dbWriteHealthConditions.remove(health);
                    }
                }
            }


            //List of changed functional abilities. If no changes are made to the item, it will be removed from the list.
            for (Category func : dbWriteFunctionalAbilities.keySet()) {
                CategoryEntryModel oldIndex = allOldFunc.get(func);
                CategoryEntryModel newIndex = dbWriteFunctionalAbilities.get(func);
                if (oldIndex != null && newIndex != null) {
                    int compare = oldIndex.compareTo(newIndex);
                    if (compare == 0) {
                        dbWriteHealthConditions.remove(func);
                    }
                }
            }

            //Unwrap BE
            HashMap<Category, FunctionalEntry> beHealthConditions = new HashMap<>();
            for (Category cat : dbWriteHealthConditions.keySet()) {
                FunctionalEntry entry = dbWriteHealthConditions.get(cat).getContentEntry();
                beHealthConditions.put(cat, entry);
            }

            HashMap<Category, FunctionalEntry> beFuncConditions = new HashMap<>();
            for (Category cat : dbWriteFunctionalAbilities.keySet()) {
                FunctionalEntry entry = dbWriteFunctionalAbilities.get(cat).getContentEntry();
                beFuncConditions.put(cat, entry);
            }

            teacherDataManager.updateCitizenTemplate(selectedCitizenTemplateModel.getBeCitizen(), beHealthConditions, beFuncConditions);
        }
    }



    /**
     * Gets the pre edit citizen template.
     * @return
     *
     */
    public CitizenModel getPreEditState() {
        return preEditCitizenTemplateModel;
    }


    public void newCitizenEntity() {
        teacherDataManager.newCitizenEntity(selectedCitizenTemplateModel.getBeCitizen());
    }

    /**
     * Use the Java Faker library to generate a random name and Java.Random to generate a random age.
     * @return An Object Array of Strings with the generated date.
     */
    public Object[] generateBaseData() {
        Faker faker = new Faker(new Locale("da-DK"));
        Object[] baseData = new Object[3];
        baseData[0] = faker.name().firstName();
        baseData[1] = faker.name().lastName();
        baseData[2] = 55 + new Random().nextInt(45) + "";

        return baseData;
    }

}
