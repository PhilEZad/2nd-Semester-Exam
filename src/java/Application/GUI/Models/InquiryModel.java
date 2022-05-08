package Application.GUI.Models;

import Application.BE.Inquiry;
import Application.BLL.TeacherDataManager;
import Application.DAL.TemplatePatternDAO;
import javafx.beans.property.StringProperty;

import java.util.List;

public class InquiryModel
{
    TeacherDataManager teacherDataManager = new TeacherDataManager();

    private StringProperty inquiryName;

    public InquiryModel(Inquiry inquiry)
    {
        this.inquiryName.setValue(inquiry.getInquiry());
    }

    public StringProperty getInquiryName()
    {
        return inquiryName;
    }

    public void setInquiryName(String inquiryName) {
        this.inquiryName.set(inquiryName);
    }
}
