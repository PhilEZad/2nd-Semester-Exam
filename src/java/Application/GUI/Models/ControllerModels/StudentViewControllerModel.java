package Application.GUI.Models.ControllerModels;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.BLL.StudentDataManager;
import Application.GUI.Models.*;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Map;

public class StudentViewControllerModel {

    private CitizenModel selectedCitizen;
    private StudentDataManager studentDataManager;

    public StudentViewControllerModel() {
        studentDataManager = new StudentDataManager();
    }

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
            citizens.add(new CitizenModel(c));
        }
      return citizens;
    }

    public CitizenModel getSelectedCitizen() {
        return selectedCitizen;
    }

    public void setSelectedCitizen(CitizenModel selectedCitizen) {
        this.selectedCitizen = selectedCitizen;
    }

    public TreeItem<CategoryEntryModel> getAllFuncCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(selectedCitizen.getAllFuncCategories());
    }

    public TreeItem<CategoryEntryModel> getAllHealthConditionsAsTreeItem() {
        return GUIUtils.mapToTreeItem(selectedCitizen.getAllHealthConditions());
    }


    public TreeItem<CategoryEntryModel> getRelevantFuncCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(selectedCitizen.getRelevantFunctionalAbilities());
    }

    public TreeItem<CategoryEntryModel> getRelevantHealthCategoriesAsTreeItem() {
        return GUIUtils.mapToTreeItem(selectedCitizen.getRelevantHealthConditions());
    }

    public ObservableList<CategoryEntryModel> getRelevantFuncCategoriesAsList() {
        ObservableList<CategoryEntryModel> list = FXCollections.observableArrayList(selectedCitizen.getRelevantFunctionalAbilities().values());
        return list;
    }

    public ObservableList<CategoryEntryModel> getRelevantHealthCategoriesAsList() {
        ObservableList<CategoryEntryModel> list = FXCollections.observableArrayList(selectedCitizen.getRelevantHealthConditions().values());
        return list;
    }



    public void updateObservation(CategoryEntryModel value) {
        studentDataManager.updateObservation(selectedCitizen.getBeCitizen(), value.getContentEntry());
    }

    public void recalculateRelevantCategories() {
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

    }
    
}
