package Application.GUI.Controllers;


import Application.BLL.SessionManager;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.AccountModel;

import Application.Utility.StateMachine.State;

import javafx.application.Platform;
import Application.Utility.StateMachine.StateMachine;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Rasmus Sandbæk
 * @author Mads Mandahl-Barth
 * */
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


    private ToggleGroup toggleGroupViews;
    private StateMachine<ToggleButton> stateMachine = new StateMachine<>();

    private TeacherDataManager dataManager = new TeacherDataManager();
    

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initToggleGroup();
        viewChangedListener();
        initViewStates();

        tglBtnDashboard.setSelected(true);
        Platform.runLater(this::initVisible);
    }


    private void initViewStates()
    {
        if (SessionManager.getCurrent().getIsAdmin())
        stateMachine.addState(tglBtnDashboard, new State(scene, "/Views/dashboard/AdminDashboard.fxml", tglBtnDashboard)); // Dashboard

        else stateMachine.addState(tglBtnDashboard, new State(scene, "/Views/dashboard/Default.fxml", tglBtnDashboard)); // Dashboard

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


    private void initVisible()
    {
        anchorPaneDashboard.setVisible(true);
        anchorPaneStudents.setVisible(false);
        anchorPaneCitizenTemplate.setVisible(false);
        anchorPaneCitizens.setVisible(false);
    }


}
