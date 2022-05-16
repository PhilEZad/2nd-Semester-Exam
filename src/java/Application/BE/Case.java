package Application.BE;

import Application.DAL.TemplateMethod.Annotations.SQLGetter;
import Application.DAL.TemplateMethod.Annotations.SQLSetter;
import Application.DAL.TemplateMethod.Annotations.SQLTable;

@SQLTable(name = "cases")
public class Case
{
    private int id;
    private String inquiryReason;
    private String medicalDiagnose;
    private Inquiry inquiry;

    public Case (int id, String inquiryReason, String medicalDiagnose, Inquiry inquiry)
    {
        this.id = id;
        this.inquiryReason = inquiryReason;
        this.medicalDiagnose = medicalDiagnose;
        this.inquiry = inquiry;

    }

    @SQLGetter(name = "classId")
    public int getId() {
        return id;
    }

    @SQLSetter(name = "classId")
    public void setId(int id) {
        this.id = id;
    }

    @SQLGetter(name = "classId")
    public String getInquiryReason() {
        return inquiryReason;
    }

    @SQLSetter(name = "classId")
    public void setInquiryReason(String inquiryReason) {
        this.inquiryReason = inquiryReason;
    }

    @SQLGetter(name = "classId")
    public String getMedicalDiagnose() {
        return medicalDiagnose;
    }

    @SQLSetter(name = "classId")
    public void setMedicalDiagnose(String medicalDiagnose) {
        this.medicalDiagnose = medicalDiagnose;
    }

    @SQLGetter(name = "classId")
    public Inquiry getInquiry() {
        return inquiry;
    }

    @SQLSetter(name = "classId")
    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }
}
