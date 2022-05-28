package Application.BE;

public class FunctionalEntry implements IUniqueIdentifier<Integer> {

    private Integer id = 0;
    private Category category = null;
    private int citizenID;

    private String assessment = "";
    private String cause = "";
    private String implications = "";
    private String citizenGoals = "";
    private String note = "";

    private Integer currentStatus = 0;
    private Integer expectedStatus = 0;

    private Integer severity = null;


    public FunctionalEntry(Category category)
    {
        this.id = category.getID();
        this.category = category;
    }

    public FunctionalEntry(int id, Category category, int level) {
        this.id = id;
        this.category = category;
        this.currentStatus = level;
        this.expectedStatus = -1;
    }

    public FunctionalEntry(int id, Category category, int citizenID, int level, String assessment, String cause, String implications, int expectedStatus, String citizenGoals, String note)
    {
        this.id = id;
        this.category = category;
        this.citizenID = citizenID;
        this.currentStatus = level;

        this.assessment = assessment;
        this.cause = cause;
        this.implications = implications;
        this.expectedStatus = expectedStatus;

        this.citizenGoals = citizenGoals;
        this.note = note;
    }

    public FunctionalEntry(int journalHID, int fk_citizen, Category fk_category, String assessment, String cause, String implications, String goals, String notes, int currentLevel, int expetedLevel) {
        this.id = journalHID;
        this.citizenID = fk_citizen;
        this.category = fk_category;
        this.assessment = assessment;
        this.cause = cause;
        this.note = notes;
        this.currentStatus = currentLevel;
        this.expectedStatus = expetedLevel;
    }

    public FunctionalEntry(int citizenId)
    {
        this.citizenID = citizenId;
    }


    @Override
    public Integer getID() {
        return this.id;
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

    public String getCategoryName() {
        return category.getName();
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
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

    public String getImplications() {
        return implications;
    }

    public void setImplications(String implications) {
        this.implications = implications;
    }

    public String getCitizenGoals() {
        return citizenGoals;
    }

    public void setCitizenGoals(String citizenGoals) {
        this.citizenGoals = citizenGoals;
    }

    public Integer getExpectedStatus() {
        return expectedStatus;
    }

    public void setExpectedStatus(int expectedStatus) {
        this.expectedStatus = expectedStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isRelevant() {
        return this.currentStatus != 9 && this.currentStatus != -1;
    }

    public int getSeverity() {
        return this.severity;
    }

    public int getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }
}
