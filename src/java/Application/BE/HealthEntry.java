package Application.BE;

public class HealthEntry implements IUniqueIdentifier<Integer>
{
    private Integer id = -1;
    private Category category = new Category("unknown");
    private int citizenID = 0;
    private String assessment = "";
    private String cause = "";
    private String note = "";
    private Integer currentStatus = 0;
    private Integer expectedStatus = 0;


    public HealthEntry(int citizenID) {
        this.citizenID = citizenID;
    }

    public HealthEntry(int journalHID, int fk_citizen, Category fk_category, String assement, String cause, String notes, int currentLevel, int expetedLevel)
    {
        this.id = journalHID;
        this.citizenID = fk_citizen;
        this.category = fk_category;
        this.assessment = assement;
        this.cause = cause;
        this.note = notes;
        this.currentStatus = currentLevel;
        this.expectedStatus = expetedLevel;
    }

    public HealthEntry() {

    }


    @Override
    public Integer getID() {
        return id == null ? -1 : id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(Integer expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    public int getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }

    public boolean isRelevant() {
        return this.currentStatus != 0 && this.currentStatus != -1;
    }
}
