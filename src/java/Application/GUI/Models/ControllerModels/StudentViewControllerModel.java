package Application.GUI.Models.ControllerModels;

import Application.BE.Category;
import Application.BE.Citizen;
import Application.GUI.Models.*;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import java.util.HashMap;

public class StudentViewControllerModel {

    private CitizenModel selectedCitizen;

    public StudentViewControllerModel() {

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
        // TODO: 26-05-2022  for (Citizen c : studentDataManager.getAssignedCitizens(0)){ //TODO: 0 is a placeholder for the current user
        for (Citizen c : new Citizen[]{}){ //TODO: 0 is a placeholder for the current user
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
        return FXCollections.observableArrayList(selectedCitizen.getRelevantFunctionalAbilities().values());
    }

    public ObservableList<CategoryEntryModel> getRelevantHealthCategoriesAsList() {
        return FXCollections.observableArrayList(selectedCitizen.getRelevantHealthConditions().values());
    }



    public void updateObservation(CategoryEntryModel value) {
        // TODO: 26-05-2022  studentDataManager.updateObservation(selectedCitizen.getBeCitizen(), value.getContentEntry());
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
