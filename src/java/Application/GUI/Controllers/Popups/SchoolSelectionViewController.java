package Application.GUI.Controllers.Popups;

import Application.GUI.Models.AccountModel;
import Application.GUI.Models.SchoolModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

public class SchoolSelectionViewController implements Initializable {


    public Button btnContinue;
    @FXML
    private TableView<SchoolModel> tblViewSchool;
    @FXML
    private TableColumn<SchoolModel, String> tblClmSchoolName;
    @FXML
    private TableColumn<SchoolModel, Number> tblClmSchoolZipCode;
    @FXML
    private TableColumn<SchoolModel, String> tblClmSchoolCity;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableView();
    }


    private void initTableView() {
        tblClmSchoolName.setCellValueFactory(param -> param.getValue().getName());
        tblClmSchoolZipCode.setCellValueFactory(param -> param.getValue().getZipCode());
        tblClmSchoolCity.setCellValueFactory(param -> param.getValue().getCity());

    }

    public void onNewSchool(ActionEvent event) {
    }

    public void onEditSchool(ActionEvent event) {
    }

    public void onDeleteSchool(ActionEvent event) {
    }

    public void onContinue(ActionEvent event) {
        Stage stage = (Stage) btnContinue.getScene().getWindow();
        //AccountModel.setSchool(tblViewSchool.getSelectionModel().getSelectedItem());

        Parent root = null;

        try {
                root = FXMLLoader.load(getClass().getResource("/Views/StudentView.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
        }

        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
