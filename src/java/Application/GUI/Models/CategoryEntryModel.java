package Application.GUI.Models;

import Application.BE.Category;
import Application.BE.FunctionalEntry;
import Application.BE.CategoryType;
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
    private IntegerProperty level;
    private ObjectProperty<ComboBox<FunctionalLevels>> levelFuncComboBox;
    private ObjectProperty<ComboBox<HealthLevels>> levelHealthComboBox;
    private ObjectProperty<FunctionalLevels> levelFunc;
    private ObjectProperty<HealthLevels> levelHealth;
    private StringProperty assessment;
    private StringProperty cause;
    private StringProperty implications;
    private StringProperty citizenGoals;
    private IntegerProperty expectedCondition;
    private ObjectProperty<ComboBox<FunctionalLevels>> exConFuncComboBox;
    private ObjectProperty<ComboBox<HealthLevels>> exConHealthComboBox;
    private ObjectProperty<FunctionalLevels> exConFunc;
    private ObjectProperty<HealthLevels> exConHealth;
    private StringProperty note;
    private CategoryType type;
    private FunctionalEntry contentEntry;



    public CategoryEntryModel(FunctionalEntry contentEntry) {
        this.contentEntry = contentEntry;
        this.id = contentEntry.getId();

        initProperties();
        categoryName.set(contentEntry.getCategoryName());
        if (contentEntry.getCurrentStatus() != null)
        level.set(contentEntry.getCurrentStatus());
        else level.set(-1);
        assessment.set(contentEntry.getAssessment());
        cause.set(contentEntry.getCause());
        implications.set(contentEntry.getImplications());
        citizenGoals.set(contentEntry.getCitizenGoals());
        if (contentEntry.getExpectedStatus() != null)
        expectedCondition.set(contentEntry.getExpectedStatus());
        else expectedCondition.set(-1);
        note.set(contentEntry.getNote());
        type = contentEntry.getCategory().getType();
        initBinds();

        levelFuncComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        levelHealthComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        levelFunc = new SimpleObjectProperty<>();
        levelHealth = new SimpleObjectProperty<>();

        exConFuncComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        exConHealthComboBox = new SimpleObjectProperty<>(new ComboBox<>());
        exConFunc = new SimpleObjectProperty<>();
        exConHealth = new SimpleObjectProperty<>();

        initLevelComboBox();
        initLevelFuncAndLevelHealth();
        initExConFuncAndLevelHealth();

        if (level.get() == 9){
            levelFuncComboBox.get().setValue(FunctionalLevels.LEVEL_9);
        } else levelFuncComboBox.get().setValue(FunctionalLevels.values()[level.get()]);

    }

    public CategoryEntryModel(String categoryName){
        initProperties();
        this.categoryName.set(categoryName);
    }

    public CategoryEntryModel(String categoryName, int level, String note) {
        initProperties();
        this.contentEntry = new FunctionalEntry(-1, new Category(categoryName), level);
        this.categoryName.set(categoryName);
        this.level.set(level);
        assessment.set(" ");
        cause.set(" ");
        implications.set(" ");
        citizenGoals.set(" ");
        expectedCondition.set(0);
        this.note.set(note);
        this.type = contentEntry.getCategory().getType();
        initBinds();

        initLevelFuncAndLevelHealth();
        initExConFuncAndLevelHealth();
    }


    /**
     * Initializes the property objects of this model.
     */
    private void initProperties() {
        categoryName = new SimpleStringProperty("");
        level = new SimpleIntegerProperty();
        levelFuncComboBox = new SimpleObjectProperty<>();
        levelHealthComboBox = new SimpleObjectProperty<>();
        levelFunc = new SimpleObjectProperty<>();
        levelHealth = new SimpleObjectProperty<>();
        assessment = new SimpleStringProperty("");
        cause = new SimpleStringProperty("");
        implications = new SimpleStringProperty("");
        citizenGoals = new SimpleStringProperty("");
        expectedCondition = new SimpleIntegerProperty();
        exConFuncComboBox = new SimpleObjectProperty<>();
        exConHealthComboBox = new SimpleObjectProperty<>();
        exConFunc = new SimpleObjectProperty<>();
        exConHealth = new SimpleObjectProperty<>();
        note = new SimpleStringProperty("");
    }

    private void initBinds() {
        categoryName.bindBidirectional(new SimpleStringProperty(contentEntry.getCategoryName()));
        level.bindBidirectional(new SimpleIntegerProperty(contentEntry.getCurrentStatus()));

        assessment.bindBidirectional(new SimpleStringProperty(contentEntry.getAssessment()));
        cause.bindBidirectional(new SimpleStringProperty(contentEntry.getCause()));
        implications.bindBidirectional(new SimpleStringProperty(contentEntry.getImplications()));
        citizenGoals.bindBidirectional(new SimpleStringProperty(contentEntry.getCitizenGoals()));
        expectedCondition.bindBidirectional(new SimpleIntegerProperty(contentEntry.getExpectedStatus()));
        note.bindBidirectional(new SimpleStringProperty(contentEntry.getNote()));
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

        exConFuncComboBox.get().setItems(funcData);
        exConFuncComboBox.get().setCellFactory(e -> comboBoxImageCell());
        exConFuncComboBox.get().setButtonCell(comboBoxImageCell());
        exConFuncComboBox.get().setDisable(true);

        ObservableList<HealthLevels> healthData = FXCollections.observableArrayList(HealthLevels.values());
        levelHealthComboBox.get().setItems(healthData);
        levelHealthComboBox.get().setCellFactory(e -> comboBoxHealthDescCell());
        levelHealthComboBox.get().setButtonCell(comboBoxHealthDescCell());
        levelHealthComboBox.get().setDisable(true);

        exConHealthComboBox.get().setItems(healthData);
        exConHealthComboBox.get().setCellFactory(e -> comboBoxHealthDescCell());
        exConHealthComboBox.get().setButtonCell(comboBoxHealthDescCell());
        exConHealthComboBox.get().setDisable(true);

        if (this.type == CategoryType.FUNCTIONAL_ABILITY) {
            if (level.get() == 9 || level.get() == -1) {
                levelFuncComboBox.get().setValue(FunctionalLevels.LEVEL_9);
            } else levelFuncComboBox.get().setValue(FunctionalLevels.values()[level.get()]);
            if (expectedCondition.get() == 9 || expectedCondition.get() == -1){
                exConFuncComboBox.get().setValue(FunctionalLevels.LEVEL_9);
            } else exConFuncComboBox.get().setValue(FunctionalLevels.values()[expectedCondition.get()]);
        }
        else {
            if (level.get() == -1) {
                levelHealthComboBox.get().setValue(HealthLevels.NOT_RELEVANT);
                exConHealthComboBox.get().setValue(HealthLevels.NOT_RELEVANT);
            }
           levelHealthComboBox.get().setValue(HealthLevels.values()[level.get()]);
           exConHealthComboBox.get().setValue(HealthLevels.values()[expectedCondition.get()]);
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

        exConFuncComboBox.get().selectionModelProperty().get().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setExpectedCondition(newValue.level);
        });
    }

    /**
     * Sets the level of this category entry entity to the value of the selected item in the combo box
     **/
    private void onHealthLevelChanged(){
        levelHealthComboBox.get().selectionModelProperty().get().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setLevel(newValue.level);
        });
        exConHealthComboBox.get().selectionModelProperty().get().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setExpectedCondition(newValue.level);
        });
    }

    public ComboBox<FunctionalLevels> getLevelFuncLevelComboBox(){
        return levelFuncComboBox.get();
    }

    public ObjectProperty<ComboBox<FunctionalLevels>> getLevelFuncComboBoxProperty(){
        return levelFuncComboBox;
    }

    public ComboBox<HealthLevels> getLevelHealthLevelComboBox(){
        return levelHealthComboBox.get();
    }

    public ObjectProperty<ComboBox<HealthLevels>> getLevelHealthComboBoxProperty(){
        return levelHealthComboBox;
    }


    public ComboBox<FunctionalLevels> getExConFuncComboBox(){
        return exConFuncComboBox.get();
    }

    public ObjectProperty<ComboBox<FunctionalLevels>> getExConFuncComboBoxProperty(){
        return exConFuncComboBox;
    }

    public ComboBox<HealthLevels> getExConHealthLevelComboBox(){
        return exConHealthComboBox.get();
    }

    public ObjectProperty<ComboBox<HealthLevels>> getExConHealthComboBoxProperty(){
        return exConHealthComboBox;
    }



    /**
     * Sets the level of this category entry entity to the value of the selected item in the combo box
     **/
    private void initLevelFuncAndLevelHealth(){
        if (this.type == CategoryType.FUNCTIONAL_ABILITY){
            if (level.get() == 9 || level.get() == -1)
                this.levelFunc.set(FunctionalLevels.LEVEL_9);
            else this.levelFunc.set(FunctionalLevels.values()[level.get()]);
        }
        else {
            if (level.get() == -1)
           this.levelHealth.set(HealthLevels.values()[level.get()]);
        }
    }

    /**
     * Sets the expected condition of this category entry entity to the value of the selected item in the combo box
     **/
    private void initExConFuncAndLevelHealth(){
        if (this.type == CategoryType.FUNCTIONAL_ABILITY){
            if (expectedCondition.get() == 9 || expectedCondition.get() == -1)
                this.exConFunc.set(FunctionalLevels.LEVEL_9);
            else this.exConFunc.set(FunctionalLevels.values()[level.get()]);
        }
        else {
            if (expectedCondition.get() == -1)
                this.exConHealth.set(HealthLevels.NOT_RELEVANT);
            this.exConHealth.set(HealthLevels.values()[level.get()]);
        }
    }

    public FunctionalLevels getLevelFunc() {
        return levelFunc.get();
    }

    public ObjectProperty<ImageView> levelFuncProperty() {
       Image funcImage = levelFunc.get().image;
       ImageView imageView = new ImageView(funcImage);
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

    public FunctionalLevels getExConFunc() {
        return exConFunc.get();
    }

    public ObjectProperty<ImageView> exConFuncProperty() {
        Image exConimage = exConFunc.get().image;
        ImageView imageView = new ImageView(exConimage);
        imageView.setFitWidth(60);
        imageView.setFitHeight(50);

        return new SimpleObjectProperty<ImageView>(imageView);
    }

    public void setExConFunc(FunctionalLevels exConFunc) {
        this.levelFunc.set(exConFunc);
    }

    public HealthLevels getExConHealth() {
        return exConHealth.get();
    }

    public StringProperty exConHealthProperty() {
        return new SimpleStringProperty(exConHealth.get().description);
    }

    public void setExConHealth(HealthLevels exConHealth) {
        this.exConHealth.set(exConHealth);
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


    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        if (this.type == CategoryType.FUNCTIONAL_ABILITY && levelFunc != null) {
            if (level == 9)
                levelFunc.set(FunctionalLevels.LEVEL_9);
            else levelFunc.set(FunctionalLevels.values()[level]);
        }
        if (this.type == CategoryType.HEALTH_CONDITION && levelHealth != null) {
            levelHealth.set(HealthLevels.values()[level]);
        }

        this.level.set(level);
    }

    public boolean isRelevant() {
        if (this.type == CategoryType.FUNCTIONAL_ABILITY && level.get() == FunctionalLevels.LEVEL_9.level ||
                this.type == CategoryType.FUNCTIONAL_ABILITY && level.get() == FunctionalLevels.LEVEL_9.ordinal())
            return false;
        if (this.type == CategoryType.HEALTH_CONDITION && level.get() == 0)
            return false;

        return true;
    }

    public String getAssessment() {
        if (assessment.get() == null)
            return "";
        else return assessment.get();
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
        if (cause.get() == null)
            return "";
        else return cause.get();
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
        if (implications.get() == null)
            return "";
        else return implications.get();
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
        if (citizenGoals.get() == null)
            return "";
        else return citizenGoals.get();
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

    public int getExpectedCondition() {
        if (expectedCondition.get() == -1)
            return 0;
        else return expectedCondition.get();
    }

    public IntegerProperty expectedConditionProperty() {
        return expectedCondition;
    }

    public void setExpectedCondition(int expectedCondition) {
        this.expectedCondition.set(expectedCondition);
    }

    public void setExpectedConditionProperty(IntegerProperty expectedCondition) {
        this.expectedCondition = expectedCondition;
    }

    public String getNote() {
        if (note.get() == null)
            return "";
        else return note.get();
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

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public FunctionalEntry getContentEntry() {
        return contentEntry;
    }

    public void setContentEntry(FunctionalEntry contentEntry) {
        this.contentEntry = contentEntry;
    }

    public TreeItem<CategoryEntryModel> getAsTreeItem(){
        return new TreeItem<>(this);
    }


    @Override
    public int compareTo(CategoryEntryModel o) {
        if(o.getId() != this.getId()){
            return this.getId() - o.getId();
        }
        int name = this.getContentEntry().getCategoryName().compareTo(o.getContentEntry().getCategoryName());
        int levelCompare = this.getLevel() - o.getLevel();
        int assessmentCompare = this.getAssessment().compareTo(o.getAssessment());
        int causeCompare = this.getCause().compareTo(o.getCause());
        int implicationsCompare = this.getImplications().compareTo(o.getImplications());
        int citizenGoalsCompare = this.getCitizenGoals().compareTo(o.getCitizenGoals());
        int expectedConditionCompare = this.getExpectedCondition() - (o.getExpectedCondition());
        int noteCompare = this.getNote().compareTo(o.getNote());

        return name + levelCompare + assessmentCompare + causeCompare + implicationsCompare + citizenGoalsCompare + expectedConditionCompare + noteCompare;
    }
}
