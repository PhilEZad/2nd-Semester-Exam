package Application.GUI.Controllers;


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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable
{
    @FXML public ToggleButton tglBtnDashboard;
    @FXML public ToggleButton tglBtnStudents;
    @FXML public ToggleButton tglBtnCitizenTemplates;
    @FXML public ToggleButton tglBtnCitizens;
    @FXML public BorderPane scene;
    @FXML public Pane viewPane;

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
    }


    private void initViewStates()
    {
       stateMachine.addState(tglBtnDashboard, new State(scene, "/Views/dashboard/Default.fxml", tglBtnDashboard)); // Dashboard
       stateMachine.addState(tglBtnStudents, new State(scene,"/Views/dashboard/Students.fxml", tglBtnStudents)); // Students
       stateMachine.addState(tglBtnCitizenTemplates, new State(scene, "/Views/dashboard/CitizenTemplate.fxml", tglBtnCitizenTemplates)); // Citizen Templates
       stateMachine.addState(tglBtnCitizens, new State(scene,"/Views/dashboard/Citizens.fxml", tglBtnCitizens)); // Citizens
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
    {
        toggleGroupViews.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
        {
            if(newValue != null)
            {
                stateMachine.change((ToggleButton) newValue);
            }
        });

    }



}
