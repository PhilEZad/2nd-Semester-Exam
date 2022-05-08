package Application.GUI.Models;

import Application.BE.Inquiry;
import Application.BLL.TeacherDataManager;
import Application.DAL.InquiryDAO;
import Application.DAL.TemplatePatternDAO;

import java.util.List;

public class CaseModel {

    TeacherDataManager teacherBLL;

    public CaseModel()
    {
        teacherBLL = new TeacherDataManager();
    }

    public List getAllInquries()
    {
        return teacherBLL.getAllInquries();
    }

    public void createCase(int id, String inquiryReason, String medicalDiagnose, Inquiry inquiry)
    {
        teacherBLL.createCase(id,inquiryReason, medicalDiagnose, inquiry);
    }
}
