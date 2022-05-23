package Application.GUI.Controllers.dashboard;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BE.Location;
import Application.BE.School;
import Application.GUI.Models.StudentModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Students implements Initializable {
    public AnchorPane anchorPaneStudents;
    public ListView listViewStudents;
    public TextField txtFieldStudentsSearch;
    public Label lblStudentsStudentName;
    public Label lblStudentEmail;
    public Button btnViewStudentCases;
    public Button btnStudentSettings;
    public ListView listViewCitizensForStudents;
    public Button btnViewStudentsWork;
    public Button btnAddCitizenToStudent;
    public Button btnRemoveCitizenToStudent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search();
    }

    public void onViewStudentCases(ActionEvent event) {
    }

    public void onStudentSettings(ActionEvent event) {
    }

    public void onViewStudentsWork(ActionEvent event) {
    }

    public void onAddCitizenToStudent(ActionEvent event) {
    }

    public void onRemoveCitizenToStudent(ActionEvent event) {
    }

    private void search()
    {
        //Wrap ObservableList of UserInfo in a FilteredList.
        FilteredList<StudentModel> filteredData = new FilteredList<>(mockUp(), b -> true);

        //Sets the filter predict when filter changes.

        txtFieldStudentsSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(data -> {

                //If filter is empty, display all accounts.
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //Compare Account name with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (data.getFullName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else return false;
            });
        });

        SortedList<StudentModel> sortedUsers = new SortedList<>(filteredData);

        listViewStudents.setItems(sortedUsers);
    }

    public ObservableList<StudentModel> mockUp(){

        School school = new School(1, "SOSU", new Location(6710, "Esbjerg"));
        Account account = new Account(1, "", "","Stine","Rasmussen" ,"stin543@SOSU.dk", school, 2);
        StudentModel stine = new StudentModel(account);
        StudentModel emilie = new StudentModel(account);
        StudentModel julie = new StudentModel(account);
        julie.setFirstName("Julie");
        julie.setLastName("Jepsen");
        julie.setEmail("juli413@SUS.dk");
        emilie.setFirstName("Emilie");
        emilie.setLastName("Dahl");
        emilie.setEmail("emil643@SOSU.dk");
        stine.setEmail("stin543@SOSU.dk");
        lblStudentsStudentName.setText(stine.getFirstName() + " " + stine.getLastName());
        lblStudentEmail.setText(stine.getEmail());

      //  Citizen arne = new Citizen(1, "Arne", "Johansen", 68, 0, 5, 6710, 1);
      //  Citizen bodil = new Citizen(2,"Bodil", "Stender", 57, 0,53, 6715,1);
      //  Citizen karsten = new Citizen(3, "Karsten", "Andersen", 47, 0, 32, 6700, 1);
      //  ObservableList citizenList = FXCollections.observableArrayList();
        ObservableList studedntList = FXCollections.observableArrayList();
      //  studedntList.add(stine);
      //  studedntList.add(emilie);
      //  studedntList.add(julie);
      //  citizenList.add(arne);
      //  citizenList.add(bodil);
      //  citizenList.add(karsten);
      //  listViewCitizensForStudents.setItems(citizenList);
        return studedntList;
    }

}
