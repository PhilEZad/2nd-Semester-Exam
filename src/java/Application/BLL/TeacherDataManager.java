package Application.BLL;

import Application.BE.*;
import Application.DAL.*;

import java.util.ArrayList;
import java.util.List;

public class TeacherDataManager
{
    TemplatePatternDAO inquiryDAO;
    TemplatePatternDAO caseDAO;
    TemplatePatternDAO healthCategoryDAO;
    TemplatePatternDAO functionalAbilityDAO;
    TemplatePatternDAO citizenDAO;
    TemplatePatternDAO citizenTemplateDAO;
    TemplatePatternDAO categoryDAO;
    TemplatePatternDAO generalInfoDAO;

    public TeacherDataManager()
    {
        inquiryDAO = new InquiryDAO();
        caseDAO = new CasesDAO();
        citizenDAO = new CitizenDAO();
        citizenTemplateDAO = new CitizenTemplateDAO();
        categoryDAO = new CategoryDAO();
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
        CitizenTemplate newTemplate = new CitizenTemplate();
        generateCategoryEntries(newTemplate);

        return (CitizenTemplate) citizenTemplateDAO.create(newTemplate);
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

    /**
     * Utility method to insert blank entries to the template
     * @param template
     */
    private void generateCategoryEntries(CitizenTemplate template) {
        List<Category> categories = categoryDAO.readAll();
        List<CategoryEntry> healthConditions = new ArrayList<>();
        List<CategoryEntry> functionalAbilities = new ArrayList<>();


        for (Category category : categories) {
            if (category.getDepth() == 0)
                continue;


            if (category.getType() == CategoryType.HEALTH_CONDITION) {
                if (category.getDepth() == 1)
                healthConditions.add(new CategoryEntry(-1,category));

                if (category.getDepth() > 1)
                    healthConditions.add(new CategoryEntry(-1,category, 0));
            }

            if (category.getType() == CategoryType.FUNCTIONAL_ABILITY) {
                if (category.getDepth() == 1)
                    functionalAbilities.add(new CategoryEntry(-1,category));

                if (category.getDepth() > 1)
                    functionalAbilities.add(new CategoryEntry(-1,category, 9));
            }
        }

        template.setFunctionalAbilities(functionalAbilities);
        template.setHealthConditions(healthConditions);

    }
}