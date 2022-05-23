package Application.GUI.Controllers;


import Application.BLL.TeacherDataManager;
import Application.GUI.Controllers.dashboard.DefaultController;
import Application.GUI.Models.AccountModel;

import Application.Utility.StateMachine.DashboardStateMachine;
import Application.Utility.StateMachine.State;

import javafx.application.Platform;
import Application.Utility.StateMachine.StateMachine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherAdminViewController implements Initializable
{
    @FXML public AnchorPane anchorPaneDashboard;
    @FXML public AnchorPane anchorPaneStudents;
    @FXML public AnchorPane anchorPaneCitizenTemplate;
    @FXML public AnchorPane anchorPaneCitizens;

    @FXML public ToggleButton tglBtnDashboard;
    @FXML public ToggleButton tglBtnStudents;
    @FXML public ToggleButton tglBtnCitizenTemplates;
    @FXML public ToggleButton tglBtnCitizens;
    @FXML public BorderPane scene;
    @FXML public StackPane pane;

    private ToggleGroup toggleGroupViews;
    private StateMachine<ToggleButton> stateMachine = new StateMachine<>();
    

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

        DashboardStateMachine.setTarget(pane);

        DashboardStateMachine.HOME.setTransition(tglBtnDashboard.selectedProperty());
        DashboardStateMachine.CITIZEN.setTransition(tglBtnCitizens.selectedProperty());
        DashboardStateMachine.STUDENT.setTransition(tglBtnStudents.selectedProperty());
        DashboardStateMachine.TEMPLATE.setTransition(tglBtnCitizenTemplates.selectedProperty());


        //stateMachine.addState(tglBtnDashboard, new State(anchorPaneDashboard, tglBtnDashboard)); // Dashboard
        //stateMachine.addState(tglBtnStudents, new State(anchorPaneStudents, tglBtnStudents)); // Students
        //stateMachine.addState(tglBtnCitizenTemplates, new State(anchorPaneCitizenTemplate, tglBtnCitizenTemplates)); // Citizen Templates
        //stateMachine.addState(tglBtnCitizens, new State(anchorPaneCitizens, tglBtnCitizens)); // Citizens
    }

    private void initToggleGroup()
    {
        toggleGroupViews = new ToggleGroup();
        tglBtnDashboard.setToggleGroup(toggleGroupViews);
        tglBtnStudents.setToggleGroup(toggleGroupViews);
        tglBtnCitizenTemplates.setToggleGroup(toggleGroupViews);
        tglBtnCitizens.setToggleGroup(toggleGroupViews);
    }

    private void viewChangedListener()
    {/*
        toggleGroupViews.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                DashboardStateMachine.makeCurrent((ToggleButton) newValue);
            }
        });
        */
    }


    private void initVisible()
    {
        anchorPaneDashboard.setVisible(false);
        anchorPaneStudents.setVisible(false);
        anchorPaneCitizenTemplate.setVisible(false);
        anchorPaneCitizens.setVisible(false);
    }


}
