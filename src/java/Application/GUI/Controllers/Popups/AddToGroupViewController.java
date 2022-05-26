package Application.GUI.Controllers.Popups;

import Application.BE.Account;
import Application.BLL.TeacherDataManager;
import Application.GUI.Models.AccountModel;
import Application.GUI.Models.CitizenModel;
import Application.Utility.GUIUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddToGroupViewController implements Initializable {


    TeacherDataManager dataManager;

    @FXML public Button btnSave;
    @FXML public Button btnCancel;
    @FXML public Button btnAddAccount;
    @FXML public Button btnRemoveAccount;
    @FXML public Button btnSearch;

    @FXML public TableView<AccountModel> tblAddedAccountsTable;
    @FXML public TableColumn<AccountModel, String> clmAddedAccountName;
    @FXML public TableColumn<AccountModel, String> clmAddedAccountClass;

    @FXML public TableView<AccountModel> tblAccountTable;
    @FXML public TableColumn<AccountModel, String> tblClmAccountName;
    @FXML public TableColumn<AccountModel, String> tblClmAccountClass;

    @FXML public TextField txtAccountSearch;
    @FXML public ComboBox<CitizenModel> comboBoxCitizen;

    public AddToGroupViewController()
    {
        dataManager = new TeacherDataManager();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initEventListeners();
        //comboBoxCases.setItems(mockCases());
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

        FilteredList<AccountModel> filteredData = new FilteredList<>(studentList(), b -> true);

        txtAccountSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(account -> {

                if (newValue == null || newValue.isEmpty())
                {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (account.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                } else if(account.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }else
                    return false;
            });
        });

        SortedList<AccountModel> sortedUsers = new SortedList<>(filteredData);

        sortedUsers.comparatorProperty().bind(tblAccountTable.comparatorProperty());

        tblAccountTable.setItems(sortedUsers);
    }

    private ObservableList<AccountModel> studentList()
    {
        ObservableList<AccountModel> returnList = FXCollections.observableArrayList();

        try {
            List<Account> accountList = dataManager.getAllStudents();

            for (Account account : accountList) {
                if (!account.getIsTeacher() && !account.getIsAdmin()) {
                    AccountModel accountModel = new AccountModel(account);
                    returnList.add(accountModel);
                }
            }
            return returnList;
        } catch (SQLException sqlException)
        {
            GUIUtils.alertCall(Alert.AlertType.WARNING, "Database fejl.");
            sqlException.printStackTrace();
            return returnList;
        }
    }
}
