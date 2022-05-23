package Application.GUI.Controllers.Popups;

import Application.BE.*;
import Application.GUI.Models.AccountModel;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.lang.Class;
import java.net.URL;
import java.util.ResourceBundle;

public class AddToTaskViewController implements Initializable {


    // FIXME: 06/05/2022 -- DAO for testing purpose, decouple
   // CasesDAO casesDAO = new CasesDAO();

    public Button btnSave;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnAddAccount;
    @FXML
    public Button btnRemoveAccount;
    @FXML
    public Button btnSearch;
    @FXML
    public TableView<AccountModel> tblAccountTable;
    @FXML
    public TableView<AccountModel> tblAddedAccountsTable;
    @FXML
    public TableColumn<AccountModel, String> clmAddedAccountName;
    @FXML
    public TableColumn<AccountModel, String> clmAddedAccountClass;
    @FXML
    public TableColumn<AccountModel, String> tblClmAccountName;
    @FXML
    public TableColumn<AccountModel, String> tblClmAccountClass;
    @FXML
    public TextField txtAccountSearch;
   // @FXML
   // public ComboBox<CaseModel> comboBoxCases;
    @FXML
    public ComboBox<Class> comboBoxClasses;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initEventListeners();
        //comboBoxCases.setItems(mockCases());
    }

    public AddToTaskViewController()
    {
    }

    public void onSave(ActionEvent actionEvent)
    {
        // FIXME: 06-05-2022 Change getAccountList to list of added users

        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    public void onCancel(ActionEvent actionEvent)
    {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void addAccountToList(ActionEvent actionEvent) {
        AccountModel selection = tblAccountTable.getSelectionModel().getSelectedItem();

        if (selection != null) {
            tblAddedAccountsTable.getItems().add(selection);
        }
    }

    public void removeAccountFromList(ActionEvent actionEvent) 
    {
        AccountModel selection = tblAddedAccountsTable.getSelectionModel().getSelectedItem();

        if(selection != null)
        {
            tblAddedAccountsTable.getItems().remove(selection);
        }
    }

    public void initEventListeners()
    {
        tblClmAccountName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        tblClmAccountClass.setCellValueFactory(param -> param.getValue().getClassNameProperty());

        clmAddedAccountName.setCellValueFactory(param -> param.getValue().getFullNameProperty());
        clmAddedAccountClass.setCellValueFactory(param -> param.getValue().getClassNameProperty());

        //Wrap ObservableList of UserInfo in a FilteredList.
        FilteredList<AccountModel> filteredData = new FilteredList<>(mockAccounts(), b -> true);

        //Sets the filter predict when filter changes.

        txtAccountSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(account -> {

                //If filter is empty, display all accounts.
                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                //Compare Account name with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (account.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else return false;
            });
        });

        SortedList<AccountModel> sortedUsers = new SortedList<>(filteredData);

        sortedUsers.comparatorProperty().bind(tblAccountTable.comparatorProperty());

        tblAccountTable.setItems(sortedUsers);
    }

   //// FIXME: 06-05-2022 Mock data, hook up to DAO
   //private ObservableList<CaseModel> mockCases()
   //    {
   //        ObservableList<CaseModel> mockCaseList = FXCollections.observableArrayList();

   //      // mockCaseList.add(new CaseModel(new Case(1, "This is the reason", "1234", new Inquiry(1, "Læge"))));
   //      // mockCaseList.add(new CaseModel(new Case (2, "This is the reason", "1234", new Inquiry(2, "Mor"))));
   //      // mockCaseList.add(new CaseModel(new Case (3, "This is the reason", "1234", new Inquiry(3, "Bror"))));
   //      // mockCaseList.add(new CaseModel(new Case (4, "This is the reason", "1234", new Inquiry(4, "Far"))));
   //      // mockCaseList.add(new CaseModel(new Case (5, "This is the reason", "1234", new Inquiry(5, "Dolf"))));

   //        return mockCaseList;
   //    }

    // FIXME: 06-05-2022 Mock data, hook up to DAO
    private ObservableList<AccountModel> mockAccounts()
    {
        ObservableList<AccountModel> mockAccountList = FXCollections.observableArrayList();

        School school = new School(1, "EASV", new Location(0));

        mockAccountList.add(new AccountModel(new Account(1, "", "", "Kasper", "Rasmussen", "", school, 0)));
        mockAccountList.add(new AccountModel(new Account(1, "", "", "Rasmus", "Sandbæk", "", school, 0)));
        mockAccountList.add(new AccountModel(new Account(1, "", "", "Philip", "Zadeh", "", school, 0)));
        mockAccountList.add(new AccountModel(new Account(1, "", "", "Mads", "Bath", "", school, 0)));

        return mockAccountList;
    }
}
