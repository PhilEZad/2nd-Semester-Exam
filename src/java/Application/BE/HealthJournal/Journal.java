package Application.BE.HealthJournal;

import Application.BE.HealthJournal.Builder.AbstractBuilder;

public class Journal {

    int id;
    FunctionalJournal functionalJournal;
    HealthJournal healthJournal;
    GeneralJournal generalJournal;
    CitizenWish citizenWish;

    public Journal()
    {
    }

    public FunctionalJournal getFunctionalJournal() {
        return functionalJournal;
    }

    public HealthJournal getHealthJournal() {
        return healthJournal;
    }

    public GeneralJournal getGeneralJournal() {
        return generalJournal;
    }

    public CitizenWish getCitizenWish() {
       return citizenWish;
    }

    public int getId() {
        return id;
    }

    public void setFunctionalJournal(FunctionalJournal functionalJournal) {
        this.functionalJournal = functionalJournal;
    }

    public void setHealthJournal(HealthJournal healthJournal) {
        this.healthJournal = healthJournal;
    }

    public void setGeneralJournal(GeneralJournal generalJournal) {
        this.generalJournal = generalJournal;
    }

    public void setCitizenWish(CitizenWish citizenWish) {
        this.citizenWish = citizenWish;
    }
}