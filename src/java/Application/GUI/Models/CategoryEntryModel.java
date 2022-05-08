package Application.GUI.Models;

import Application.BE.CategoryEntry;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CategoryEntryModel {


    private int id;
    private StringProperty categoryName;
    private StringProperty superCategory;
    private IntegerProperty level;
    private ObjectProperty<ComboBox<FunctionalLevels>> levelImageComboBox;
    private StringProperty assessment;
    private StringProperty cause;
    private StringProperty implications;
    private StringProperty citizenGoals;
    private StringProperty expectedCondition;
    private StringProperty note;
    private boolean isFunctionAbility;
    private CategoryEntry categoryEntry;

    public CategoryEntryModel(CategoryEntry categoryEntry) {
        categoryName = new SimpleStringProperty(categoryEntry.getCategoryName());
        superCategory = new SimpleStringProperty(categoryEntry.getSuperCategory());
        level = new SimpleIntegerProperty(categoryEntry.getLevel());
        assessment = new SimpleStringProperty(categoryEntry.getAssessment());
        cause = new SimpleStringProperty(categoryEntry.getCause());
        implications = new SimpleStringProperty(categoryEntry.getImplications());
        citizenGoals = new SimpleStringProperty(categoryEntry.getCitizenGoals());
        expectedCondition = new SimpleStringProperty(categoryEntry.getExpectedCondition());
        note = new SimpleStringProperty(categoryEntry.getNote());
        isFunctionAbility = categoryEntry.isFunctionAbility();
        levelImageComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        this.categoryEntry = categoryEntry;
        this.id = categoryEntry.getId();

        initImageComboBox();

        onImageLevelChanged();

        if (level.get() == 9){
            levelImageComboBox.get().setValue(FunctionalLevels.LEVEL_9);
        } else levelImageComboBox.get().setValue(FunctionalLevels.values()[level.get()]);

    }

    public CategoryEntryModel(String categoryName){
        this.categoryName = new SimpleStringProperty(categoryName);
    }

    private void initImageComboBox(){
        ObservableList<FunctionalLevels> data = FXCollections.observableArrayList(FunctionalLevels.values());
        levelImageComboBox.get().setItems(data);

        levelImageComboBox.get().setCellFactory(e -> comboBoxCell());
        levelImageComboBox.get().setButtonCell(comboBoxCell());
    }

    private ListCell<FunctionalLevels> comboBoxCell() {
        return new ListCell<FunctionalLevels>() {
            ImageView imageView = new ImageView();

            @Override
            public void updateItem(FunctionalLevels level, boolean empty) {
                super.updateItem(level, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    imageView.setImage(level.image);
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(50);
                    setGraphic(imageView);
                }
            }
        };
    }

    private void onImageLevelChanged(){
        levelImageComboBox.get().selectionModelProperty().get().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setLevel(newValue.level);
        });
    }

    public ComboBox<FunctionalLevels> getLevelImageComboBox(){
        return levelImageComboBox.get();
    }

    public ObjectProperty<ComboBox<FunctionalLevels>> getLevelImageComboBoxProperty(){
        return levelImageComboBox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public StringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getSuperCategory() {
        return superCategory.get();
    }

    public StringProperty superCategoryProperty() {
        return superCategory;
    }

    public void setSuperCategory(String superCategory) {
        this.superCategory.set(superCategory);
    }

    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public String getAssessment() {
        return assessment.get();
    }

    public StringProperty assessmentProperty() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment.set(assessment);
    }

    public String getCause() {
        return cause.get();
    }

    public StringProperty causeProperty() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause.set(cause);
    }

    public String getImplications() {
        return implications.get();
    }

    public StringProperty implicationsProperty() {
        return implications;
    }

    public void setImplications(String implications) {
        this.implications.set(implications);
    }

    public String getCitizenGoals() {
        return citizenGoals.get();
    }

    public StringProperty citizenGoalsProperty() {
        return citizenGoals;
    }

    public void setCitizenGoals(String citizenGoals) {
        this.citizenGoals.set(citizenGoals);
    }

    public String getExpectedCondition() {
        return expectedCondition.get();
    }

    public StringProperty expectedConditionProperty() {
        return expectedCondition;
    }

    public void setExpectedCondition(String expectedCondition) {
        this.expectedCondition.set(expectedCondition);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public boolean isFunctionAbility() {
        return isFunctionAbility;
    }

    public void setFunctionAbility(boolean functionAbility) {
        isFunctionAbility = functionAbility;
    }

    public CategoryEntry getCategoryEntry() {
        return categoryEntry;
    }

    public void setCategoryEntry(CategoryEntry categoryEntry) {
        this.categoryEntry = categoryEntry;
    }
}
