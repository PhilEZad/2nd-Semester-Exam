package Application.GUI.Models;

import Application.BE.Case;
import Application.BE.Inquiry;
import Application.BLL.TeacherDataManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class CaseModel {

    TeacherDataManager teacherBLL = new TeacherDataManager();

    private IntegerProperty id;
    private StringProperty caseName;
    private StringProperty inquiryReason;
    private StringProperty medicalDiagnose;

    public CaseModel(Case caseInfo)
    {

        this.id = new SimpleIntegerProperty();
        this.caseName = new SimpleStringProperty();
        this.inquiryReason = new SimpleStringProperty();
        this.medicalDiagnose = new SimpleStringProperty();

        this.id.set(caseInfo.getId());
        this.caseName.set(caseInfo.getInquiry().getInquiry());
        this.inquiryReason.set(caseInfo.getInquiryReason());
        this.medicalDiagnose.set(caseInfo.getMedicalDiagnose());
    }

    public CaseModel()
    {

    }

    public List getAllInquries()
    {
        return teacherBLL.getAllInquiries();
    }

    public void createCase(int id, String inquiryReason, String medicalDiagnose, Inquiry inquiry)
    {
        teacherBLL.createCase(id,inquiryReason, medicalDiagnose, inquiry);
    }
}
