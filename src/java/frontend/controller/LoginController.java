package frontend.controller;

import backend.entity.School;
import backend.logic.DataAccessFactory;
import backend.logic.SessionManager;
import frontend.model.SchoolModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import util.AccountType;

import java.io.IOException;
import java.io.InvalidClassException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable
{
    @FXML public BorderPane root;
    public TableView<SchoolModel> tblSchools;
    public TableColumn<SchoolModel, String> clmSchool;
    public RadioButton rbStudent;
    public RadioButton rbTeacher;
    public RadioButton rbAdmin;

    public TextField username;
    public TextField password;

    ToggleGroup toggleGroup;

    private ObservableList<SchoolModel> schoolModels;

    public void onSubmit(ActionEvent actionEvent) throws IOException
    {
        if (tblSchools.getSelectionModel().getSelectedItem() == null)
            return;

        AccountType accountType = rbAdmin.isSelected() ? AccountType.ADMIN : rbTeacher.isSelected() ? AccountType.TEACHER : rbStudent.isSelected() ? AccountType.STUDENT : AccountType.NONE;

        if (SessionManager.tryBeginSession(this.tblSchools.getSelectionModel().getSelectedItem().getId(), accountType, this.username.getText(), this.password.getText()))
        {
            String url = switch (accountType)
            {
                case ADMIN -> "/fxml/dashboard/Admin.fxml";
                case TEACHER -> "/fxml/dashboard/Teacher.fxml";
                case STUDENT -> "/fxml/dashboard/Student.fxml";
                case NONE -> null;
            };

            if (url != null) root.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource(url))));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        schoolModels = FXCollections.observableArrayList();
        toggleGroup = new ToggleGroup();

        toggleGroup.getToggles().add(rbStudent);
        toggleGroup.getToggles().add(rbAdmin);
        toggleGroup.getToggles().add(rbTeacher);

        toggleGroup.selectToggle(rbStudent);
        tblSchools.itemsProperty().bindBidirectional(new SimpleObjectProperty<>(schoolModels));

        clmSchool.setCellValueFactory(param -> param.getValue().nameProperty());

        tblSchools.getSelectionModel().selectFirst();

        try {
            populateSchoolList();
        } catch (InvalidClassException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateSchoolList() throws InvalidClassException
    {
        for (School school : (List<School>) DataAccessFactory.get(School.class).getAccessObject().getAll())
        {
            this.schoolModels.add(new SchoolModel(school.getId(), school.getName(), school.getLocation().getZipcode(), school.getLocation().getName()));
        }
    }
}
