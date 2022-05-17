package Application.BLL;

import Application.BE.*;
import Application.DAL.*;

import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO inquiryDAO;
    TemplatePatternDAO caseDAO;
    TemplatePatternDAO healthCategoryDAO;
    TemplatePatternDAO functionalAbilityDAO;
    TemplatePatternDAO citizenDAO;
    TemplatePatternDAO citizenTemplateDAO;
    TemplatePatternDAO generalInfoDAO;

    public TeacherDataManager()
    {
        inquiryDAO = new InquiryDAO();
        caseDAO = new CasesDAO();
        citizenDAO = new CitizenDAO();
        citizenTemplateDAO = new CitizenTemplateDAO();
    }

    public Case createCase(int id, String inquiryReason, String medicalDiagnose, Inquiry inquiry)
    {
        return (Case) caseDAO.create(new Case(id, inquiryReason, medicalDiagnose, inquiry));
    }

    public List getAllInquiries()
    {
        return inquiryDAO.readAll();
    }

    public List getAllCitizenTemplates() {
        List<CitizenTemplate> citizenTemplates = citizenTemplateDAO.readAll();


        return citizenTemplates;
    }

    public CitizenTemplate newCitizenTemplate() {
        //Template
        CitizenTemplate newTemplate = (CitizenTemplate) citizenTemplateDAO.create(new CitizenTemplate());

        //General Info


        //Set categories

        return newTemplate;
    }

    public void deleteCitizenTemplate(CitizenTemplate template) {
    }

    public void copyCitizenTemplate(CitizenTemplate template) {
    }

    public void updateCitizenTemplate(CitizenTemplate template, List<CategoryEntry> beHealthConditions, List<CategoryEntry> beFunctionalAbilities) {
    }

    public void newCitizenEntity(CitizenTemplate template) {
        citizenDAO.create(new Citizen());
    }

    public void updateCitizenEntity(Citizen citizen) {
        citizenDAO.update(citizen);
    }

    public void deleteCitizenEntity(Citizen citizen) {
        citizenDAO.delete(citizen.getId());
    }

    public List getAllCitizens() {
        return citizenDAO.readAll();
    }
}