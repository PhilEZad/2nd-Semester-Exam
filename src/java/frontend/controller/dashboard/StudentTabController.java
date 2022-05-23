package frontend.controller.dashboard;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import frontend.model.UserInfoModel;

public class StudentTabController
{
    public ListView<UserInfoModel> listViewStudents;
    public TextField txtFieldStudentsSearch;
    public Label lblStudentsStudentName;
    public Label lblStudentEmail;
    public ListView listViewCitizensForStudents;
}
