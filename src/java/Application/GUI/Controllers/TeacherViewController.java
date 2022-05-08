package Application.GUI.Controllers;

import Application.GUI.Models.CategoryEntryModel;
import Application.GUI.Models.CitizenTemplateModel;
import Application.GUI.Models.ControllerModels.TeacherViewModel;
import Application.GUI.Models.FunctionalLevels;
import Application.GUI.Models.AccountModel;

import Application.GUI.StateMachine.State;

import javafx.application.Platform;
import Application.GUI.StateMachine.StateMachine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {
    @FXML public AnchorPane anchorPaneDashboard;
    @FXML public AnchorPane anchorPaneStudents;
    @FXML public AnchorPane anchorPaneCitizenTemplate;
    @FXML public AnchorPane anchorPaneCitizens;
    @FXML public AnchorPane anchorPaneCases;
    @FXML public AnchorPane anchorPaneAssignments;
    @FXML public AnchorPane anchorPaneJournals;

    @FXML public ToggleButton tglBtnDashboard;
    @FXML public ToggleButton tglBtnStudents;
    @FXML public ToggleButton tglBtnCitizenTemplates;
    @FXML public ToggleButton tglBtnCitizens;
    @FXML public ToggleButton tglBtnCases;
    @FXML public ToggleButton tglBtnAssignments;
    @FXML public ToggleButton tglBtnJournals;



    private ToggleGroup toggleGroupViews;
    private StateMachine<ToggleButton> stateMachine = new StateMachine<>();
    private TeacherViewModel teacherViewModel = new TeacherViewModel();

    @FXML public BorderPane TeacherScene;

    /**
     *  passed by reference through a resource bundle from the login controller
     *
     * @see LoginController
     * */
    private AccountModel account;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //this.account = (AccountModel) resources.getObject("account");

        initToggleGroup();
        viewChangedListener();
        initViewStates();

        tglBtnDashboard.setSelected(true);
        Platform.runLater(this::initVisible);
    }


    private void initViewStates()
    {
        System.out.println(anchorPaneDashboard.getChildren());
        stateMachine.addState(tglBtnDashboard, new State(anchorPaneDashboard, tglBtnDashboard)); // Dashboard
        stateMachine.addState(tglBtnStudents, new State(anchorPaneStudents, tglBtnStudents)); // Students
        stateMachine.addState(tglBtnCitizenTemplates, new State(anchorPaneCitizenTemplate, tglBtnCitizenTemplates)); // Citizen Templates
        stateMachine.addState(tglBtnCitizens, new State(anchorPaneCitizens, tglBtnCitizens)); // Citizens
        stateMachine.addState(tglBtnCases, new State(anchorPaneCases, tglBtnCases)); // Cases
        stateMachine.addState(tglBtnAssignments, new State(anchorPaneAssignments, tglBtnAssignments)); // Assignments
        stateMachine.addState(tglBtnJournals, new State(anchorPaneJournals, tglBtnJournals)); // Journals
    }

    private void initToggleGroup()
    {
        toggleGroupViews = new ToggleGroup();
        tglBtnDashboard.setToggleGroup(toggleGroupViews);
        tglBtnStudents.setToggleGroup(toggleGroupViews);
        tglBtnCitizenTemplates.setToggleGroup(toggleGroupViews);
        tglBtnCitizens.setToggleGroup(toggleGroupViews);
        tglBtnCases.setToggleGroup(toggleGroupViews);
        tglBtnAssignments.setToggleGroup(toggleGroupViews);
        tglBtnJournals.setToggleGroup(toggleGroupViews);
    }

    private void viewChangedListener()
    {
        toggleGroupViews.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                stateMachine.change((ToggleButton) newValue);
            }
        });
    }


    private void initVisible()
    {
        anchorPaneDashboard.setVisible(false);
        anchorPaneStudents.setVisible(false);
        anchorPaneCitizenTemplate.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneCases.setVisible(false);
        anchorPaneAssignments.setVisible(false);
        anchorPaneJournals.setVisible(false);
    }


}
