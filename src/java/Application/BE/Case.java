package Application.BE;

import javafx.collections.ObservableList;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInquiryReason() {
        return inquiryReason;
    }

    public void setInquiryReason(String inquiryReason) {
        this.inquiryReason = inquiryReason;
    }

    public String getMedicalDiagnose() {
        return medicalDiagnose;
    }

    public void setMedicalDiagnose(String medicalDiagnose) {
        this.medicalDiagnose = medicalDiagnose;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }
}
