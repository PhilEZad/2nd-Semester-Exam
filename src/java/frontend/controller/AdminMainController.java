package frontend.controller;

import backend.logic.SessionManager;
import frontend.view.DashboardStateMachine;
import frontend.view.NavigationItemControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable
{
    public Pane dashboard;
    public NavigationItemControl tglBtnDashboard;
    public NavigationItemControl tglBtnStudents;
    public NavigationItemControl tglBtnCitizens;
    public NavigationItemControl tglBtnCitizenTemplates;
    public NavigationItemControl tglBtnAdminPanel;

    ToggleGroup toggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        DashboardStateMachine.setTarget(dashboard);

        DashboardStateMachine.HOME.setTransition(tglBtnDashboard.selectedProperty());
        DashboardStateMachine.CITIZEN.setTransition(tglBtnCitizens.selectedProperty());
        DashboardStateMachine.STUDENT.setTransition(tglBtnStudents.selectedProperty());
        DashboardStateMachine.TEMPLATE.setTransition(tglBtnCitizenTemplates.selectedProperty());
        DashboardStateMachine.ADMIN.setTransition(tglBtnAdminPanel.selectedProperty());

        this.toggleGroup = new ToggleGroup();

        toggleGroup.getToggles().add(this.tglBtnDashboard);
        toggleGroup.getToggles().add(this.tglBtnCitizens);
        toggleGroup.getToggles().add(this.tglBtnCitizenTemplates);
        toggleGroup.getToggles().add(this.tglBtnStudents);
        toggleGroup.getToggles().add(this.tglBtnAdminPanel);

        toggleGroup.selectToggle(tglBtnCitizens);
    }

    public void logoutAction(ActionEvent actionEvent) throws IOException {
        SessionManager.tryEndSession();

        // TODO: 22-05-2022 reset state

        Parent root = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/fxml/LoginScreen.fxml")));
        dashboard.getScene().setRoot(root);
    }
}
