package frontend.controller;

import frontend.view.NavigationItemControl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import frontend.view.DashboardStateMachine;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherMainController implements Initializable
{
    @FXML
    public Pane dashboard;

      public NavigationItemControl tglBtnDashboard;

    @FXML public NavigationItemControl tglBtnStudents;
    @FXML public NavigationItemControl tglBtnCitizens;
    @FXML public NavigationItemControl tglBtnCitizenTemplates;

    private final ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        DashboardStateMachine.setTarget(dashboard);

        DashboardStateMachine.HOME.setTransition(tglBtnDashboard.selectedProperty());
        DashboardStateMachine.CITIZEN.setTransition(tglBtnCitizens.selectedProperty());
        DashboardStateMachine.STUDENT.setTransition(tglBtnStudents.selectedProperty());
        DashboardStateMachine.TEMPLATE.setTransition(tglBtnCitizenTemplates.selectedProperty());

        toggleGroup.getToggles().add(this.tglBtnDashboard);
        toggleGroup.getToggles().add(this.tglBtnCitizens);
        toggleGroup.getToggles().add(this.tglBtnCitizenTemplates);
        toggleGroup.getToggles().add(this.tglBtnStudents);

        toggleGroup.selectToggle(tglBtnCitizens);
    }

}
