package Application.GUI.Controllers;

import Application.GUI.Models.AccountModel;
import Application.GUI.StateMachine.State;

import javafx.application.Platform;
import Application.GUI.StateMachine.StateMachine;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
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


    @FXML public BorderPane TeacherScene;

    /**
     *  passed by reference through a resource bundle from the login controller
     *
     * @see LoginController
     * */
    private AccountModel account;

    private ToggleGroup toggleGroup;

    private final StateMachine<ToggleButton> stateMachine = new StateMachine<>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.account = (AccountModel) resources.getObject("account");

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
        toggleGroup = new ToggleGroup();
        tglBtnDashboard.setToggleGroup(toggleGroup);
        tglBtnStudents.setToggleGroup(toggleGroup);
        tglBtnCitizenTemplates.setToggleGroup(toggleGroup);
        tglBtnCitizens.setToggleGroup(toggleGroup);
        tglBtnCases.setToggleGroup(toggleGroup);
        tglBtnAssignments.setToggleGroup(toggleGroup);
        tglBtnJournals.setToggleGroup(toggleGroup);
    }

    private void viewChangedListener()
    {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
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
