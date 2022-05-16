package Application.GUI.Models;

import Application.BE.CategoryEntry;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CategoryEntryModel implements Comparable<CategoryEntryModel> {


    private int id;
    private StringProperty categoryName;
    private StringProperty superCategory;
    private IntegerProperty level;
    private ObjectProperty<ComboBox<FunctionalLevels>> levelFuncComboBox;
    private ObjectProperty<ComboBox<HealthLevels>> levelHealthComboBox;
    private ObjectProperty<FunctionalLevels> levelFunc;
    private ObjectProperty<HealthLevels> levelHealth;
    private StringProperty assessment;
    private StringProperty cause;
    private StringProperty implications;
    private StringProperty citizenGoals;
    private StringProperty expectedCondition;
    private StringProperty note;
    private boolean isFunctionAbility;
    private CategoryEntry categoryEntry;



    public CategoryEntryModel(CategoryEntry categoryEntry) {
        initProperties();
        categoryName.set(categoryEntry.getCategoryName());
        superCategory.set(categoryEntry.getSuperCategory());
        level.set(categoryEntry.getLevel());
        assessment.set(categoryEntry.getAssessment());
        cause.set(categoryEntry.getCause());
        implications.set(categoryEntry.getImplications());
        citizenGoals.set(categoryEntry.getCitizenGoals());
        expectedCondition.set(categoryEntry.getExpectedCondition());
        note.set(categoryEntry.getNote());
        isFunctionAbility = categoryEntry.isFunctionAbility();

        this.categoryEntry = categoryEntry;
        this.id = categoryEntry.getId();

        levelFuncComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        levelHealthComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        levelFunc = new SimpleObjectProperty<>();
        levelHealth = new SimpleObjectProperty<>();

        initLevelComboBox();
        initLevelFuncAndLevelHealth();

        if (level.get() == 9){
            levelFuncComboBox.get().setValue(FunctionalLevels.LEVEL_9);
        } else levelFuncComboBox.get().setValue(FunctionalLevels.values()[level.get()]);

    }

    public CategoryEntryModel(String categoryName){
        initProperties();
        this.categoryName.set(categoryName);
    }

    public CategoryEntryModel(String categoryName, int level, String note, boolean isFunctionAbility) {
        initProperties();
        this.categoryName.set(categoryName);
        this.level.set(level);
        this.note.set(note);
        this.isFunctionAbility = isFunctionAbility;

        initLevelFuncAndLevelHealth();
    }

    /**
     * Initializes the property objects of this model.
     */
    private void initProperties() {
        categoryName = new SimpleStringProperty();
        superCategory = new SimpleStringProperty();
        level = new SimpleIntegerProperty();
        levelFuncComboBox = new SimpleObjectProperty<>();
        levelHealthComboBox = new SimpleObjectProperty<>();
        levelFunc = new SimpleObjectProperty<>();
        levelHealth = new SimpleObjectProperty<>();
        assessment = new SimpleStringProperty();
        cause = new SimpleStringProperty();
        implications = new SimpleStringProperty();
        citizenGoals = new SimpleStringProperty();
        expectedCondition = new SimpleStringProperty();
        note = new SimpleStringProperty();
    }

    /**
     * Sets the data of the combo box and the custom list cells
     */
    private void initLevelComboBox(){

        onFuncLevelChanged();
        onHealthLevelChanged();

        ObservableList<FunctionalLevels> funcData = FXCollections.observableArrayList(FunctionalLevels.values());
        levelFuncComboBox.get().setItems(funcData);
        levelFuncComboBox.get().setCellFactory(e -> comboBoxImageCell());
        levelFuncComboBox.get().setButtonCell(comboBoxImageCell());
        levelFuncComboBox.get().setDisable(true);

        ObservableList<HealthLevels> healthData = FXCollections.observableArrayList(HealthLevels.values());
        levelHealthComboBox.get().setItems(healthData);
        levelHealthComboBox.get().setCellFactory(e -> comboBoxHealthDescCell());
        levelHealthComboBox.get().setButtonCell(comboBoxHealthDescCell());
        levelHealthComboBox.get().setDisable(true);

        if (isFunctionAbility) {
            if (level.get() == 9){
                levelFuncComboBox.get().setValue(FunctionalLevels.LEVEL_9);
            } else levelFuncComboBox.get().setValue(FunctionalLevels.values()[level.get()]);

        }
        else {
            levelHealthComboBox.get().setValue(HealthLevels.values()[level.get()]);
        }
    }

    /**
     * A custom list cell for the combo box, allowing for the image to be displayed
     * @return
     */
    private ListCell<FunctionalLevels> comboBoxImageCell() {
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

    /**
     * A custom list cell for the combo box, allowing for the health description to be displayed
     **/
    private ListCell<HealthLevels> comboBoxHealthDescCell() {
        return new ListCell<HealthLevels>() {
            @Override
            public void updateItem(HealthLevels level, boolean empty) {
                super.updateItem(level, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setText(level.description);
                }
            }
        };
    }

    /**
     * Sets the level of this category entry entity to the value of the selected item in the combo box
     */
    private void onFuncLevelChanged(){
        levelFuncComboBox.get().selectionModelProperty().get().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setLevel(newValue.level);
        });
    }

    /**
     * Sets the level of this category entry entity to the value of the selected item in the combo box
     **/
    private void onHealthLevelChanged(){
        levelHealthComboBox.get().selectionModelProperty().get().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setLevel(newValue.level);
        });
    }

    public ComboBox<FunctionalLevels> getFuncLevelComboBox(){
        return levelFuncComboBox.get();
    }

    public ObjectProperty<ComboBox<FunctionalLevels>> getFuncComboBoxProperty(){
        return levelFuncComboBox;
    }

    public ComboBox<HealthLevels> getHealthLevelComboBox(){
        return levelHealthComboBox.get();
    }

    public ObjectProperty<ComboBox<HealthLevels>> getHealthComboBoxProperty(){
        return levelHealthComboBox;
    }



    /**
     * Sets the level of this category entry entity to the value of the selected item in the combo box
     **/
    private void initLevelFuncAndLevelHealth(){
        if (isFunctionAbility){
            this.levelFunc.set(FunctionalLevels.values()[level.get()]);
        }
        else {
            this.levelHealth.set(HealthLevels.values()[level.get()]);
        }
    }

    public FunctionalLevels getLevelFunc() {
        return levelFunc.get();
    }

    public ObjectProperty<ImageView> levelFuncProperty() {
        Image image = levelFunc.get().image;
        ImageView imageView = new ImageView(levelFunc.get().image);
        imageView.setFitWidth(60);
        imageView.setFitHeight(50);

        return new SimpleObjectProperty<ImageView>(imageView);
    }

    public void setLevelFunc(FunctionalLevels levelFunc) {
        this.levelFunc.set(levelFunc);
    }

    public HealthLevels getLevelHealth() {
        return levelHealth.get();
    }

    public StringProperty levelHealthProperty() {
        return new SimpleStringProperty(levelHealth.get().description);
    }

    public void setLevelHealth(HealthLevels levelHealth) {
        this.levelHealth.set(levelHealth);
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

    public void setAssessmentProperty(StringProperty assessment) {
        this.assessment = assessment;
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

    public void setCauseProperty(StringProperty cause) {
        this.cause = cause;
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

    public void setImplicationsProperty(StringProperty implications) {
        this.implications = implications;
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

    public void setCitizenGoalsProperty(StringProperty citizenGoals) {
        this.citizenGoals = citizenGoals;
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

    public void setExpectedConditionProperty(StringProperty expectedCondition) {
        this.expectedCondition = expectedCondition;
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
    public void setNoteProperty(StringProperty note) {
        this.note = note;
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

    public TreeItem<CategoryEntryModel> getAsTreeItem(){
        return new TreeItem<>(this);
    }


    @Override
    public int compareTo(CategoryEntryModel o) {
        if(o.getId() != this.getId()){
            return this.getId() - o.getId();
        }
        int name = this.getCategoryEntry().getCategoryName().compareTo(o.getCategoryEntry().getCategoryName());
        int levelCompare = this.getLevel() - o.getLevel();
        int assessmentCompare = this.getAssessment().compareTo(o.getAssessment());
        int causeCompare = this.getCause().compareTo(o.getCause());
        int implicationsCompare = this.getImplications().compareTo(o.getImplications());
        int citizenGoalsCompare = this.getCitizenGoals().compareTo(o.getCitizenGoals());
        int expectedConditionCompare = this.getExpectedCondition().compareTo(o.getExpectedCondition());
        int noteCompare = this.getNote().compareTo(o.getNote());

        return name + levelCompare + assessmentCompare + causeCompare + implicationsCompare + citizenGoalsCompare + expectedConditionCompare + noteCompare;
    }
}
