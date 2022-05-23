package frontend.controller;

import frontend.view.DashboardStateMachine;
import frontend.view.NavigationItemControl;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMainController implements Initializable
{
    public Pane dashboard;
    public NavigationItemControl tglBtnDashboard;
    public NavigationItemControl tglBtnCitizens;

    ToggleGroup toggleGroup = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        DashboardStateMachine.setTarget(dashboard);

        DashboardStateMachine.HOME.setTransition(tglBtnDashboard.selectedProperty());
        DashboardStateMachine.CITIZEN.setTransition(tglBtnCitizens.selectedProperty());

        toggleGroup.getToggles().add(this.tglBtnDashboard);
        toggleGroup.getToggles().add(this.tglBtnCitizens);

        toggleGroup.selectToggle(tglBtnCitizens);
    }
}
