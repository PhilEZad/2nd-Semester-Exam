package Application.GUI.Models.ControllerModels;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.BLL.StudentDataManager;
import Application.GUI.Models.*;
import Application.Utility.GUIUtils;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Map;

public class StudentViewControllerModel {

    private final ObjectProperty<CitizenModel> selected = new SimpleObjectProperty<>();

    private final StudentDataManager studentDataManager = new StudentDataManager();;

    public StudentViewControllerModel() {}

    public void onStudentCitizensSearch() {
        //TODO
    }

    public void onOpenJournal() {
        //TODO
    }


    public void onAddObservation() {
        //TODO
    }

    public ObservableList<CitizenModel> getAllCitizens()
    {
        ObservableList<CitizenModel> citizens = FXCollections.observableArrayList();
        for (Citizen c : studentDataManager.getAssignedCitizens(0)){ //TODO: 0 is a placeholder for the current user
            citizens.add(CitizenModel.convert(c));
        }
      return citizens;
    }

    public ObjectProperty<CitizenModel> selectedCitizenProperty()
    {
        return this.selected;
    }

    public CitizenModel getSelectedCitizen()
    {
        return selectedCitizenProperty().get();
    }

    public void setSelectedCitizen(CitizenModel select) {
        this.selectedCitizenProperty().set(select);
    }

    ObjectProperty<TreeItem<CategoryEntryModel>> functionalAbilityTreeItem = new SimpleObjectProperty<>();

    public ObjectProperty<TreeItem<CategoryEntryModel>> functionalAbilityTreeItemProperty()
    {
        return this.functionalAbilityTreeItem;
    }


    public TreeItem<CategoryEntryModel> getAllFuncCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(this.selected.get().getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditionsAsTreeItem() {
        return GUIUtils.mapToTreeItem(this.selected.get().getAllHealthConditions());
    }

    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(this.selected.get().getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(this.selected.get().getRelevantHealthConditions());
    }


    public void updateObservation(CategoryEntryModel value)
    {
        studentDataManager.updateObservation(CitizenModel.convert(this.getSelectedCitizen()), value.getContentEntry());
    }

    public void recalculateRelevantCategories()
    {
        /*
        HashMap<Category, CategoryEntryModel> functionalAbilities = new HashMap<>(selectedCitizen.getAllFuncCategories());
        HashMap<Category, CategoryEntryModel> healthConditions = new HashMap<>(selectedCitizen.getAllHealthConditions());

        HashMap<Category, CategoryEntryModel> relevantFunctionalAbilities = new HashMap<>();
        HashMap<Category, CategoryEntryModel> relevantHealthConditions = new HashMap<>();
        HashMap<Category, CategoryEntryModel> nonRelevantFunctionalAbilities = new HashMap<>();
        HashMap<Category, CategoryEntryModel> nonRelevantHealthConditions = new HashMap<>();

        Thread thread = new Thread(() -> {
            for (CategoryEntryModel categoryEntryModel : functionalAbilities.values()) {
                    if (categoryEntryModel.getContentEntry().getRelevant()) {
                        relevantFunctionalAbilities.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                    }
                    else {
                        nonRelevantFunctionalAbilities.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                    }
            }
            for (CategoryEntryModel categoryEntryModel : healthConditions.values()) {
                if (categoryEntryModel.getContentEntry().getRelevant()) {
                    relevantHealthConditions.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                }
                else {
                    nonRelevantHealthConditions.put(categoryEntryModel.getContentEntry().getCategory(), categoryEntryModel);
                }
            }
        });
        thread.start();
        while (thread.isAlive()) {
            //wait for the thread to finish
        }

        selectedCitizen.setRelevantFunctionalAbilities(relevantFunctionalAbilities);
        selectedCitizen.setRelevantHealthConditions(relevantHealthConditions);
        selectedCitizen.setNonRelevantFunctionalAbilities(nonRelevantFunctionalAbilities);
        selectedCitizen.setNonRelevantHealthConditions(nonRelevantHealthConditions);
    */

    }

    
}
