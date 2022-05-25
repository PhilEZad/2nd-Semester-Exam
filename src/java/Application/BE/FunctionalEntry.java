package Application.BE;

public class FunctionalEntry implements IUniqueIdentifier<Integer> {

    private Integer id = 0;
    private Category category = null;

    private String assessment = "";
    private String cause = "";
    private String implications = "";
    private String citizenGoals = "";
    private String note = "";

    private Integer currentStatus = 0;
    private Integer expectedStatus = 0;


    private boolean relevant;
    private Integer severity = null;


    public FunctionalEntry(Category category)
    {
        this.id = category.getID();
        this.category = category;
        initRelevance();
    }

    public FunctionalEntry(int id, Category category, int level) {
        this.id = id;
        this.category = category;
        this.currentStatus = level;
        this.expectedStatus = -1;
        initRelevance();
    }

    public FunctionalEntry(int id, Category category, int level, String assessment, String cause, String implications, int expectedStatus, String citizenGoals, String note)
    {
        this.id = id;
        this.category = category;
        this.currentStatus = level;

        this.assessment = assessment;
        this.cause = cause;
        this.implications = implications;
        this.expectedStatus = expectedStatus;

        this.citizenGoals = citizenGoals;
        this.note = note;

        initRelevance();
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
        initRelevance();
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

    public void setRelevant(boolean relevant) {
        this.relevant = relevant;
    }

    public boolean getRelevant() {
        return relevant;
    }

    private void initRelevance()
    {
        if (this.currentStatus == -1)
        {
            this.relevant = false;
        }
        else switch (getCategory().getType())
        {
            case FUNCTIONAL_ABILITY -> setRelevant(this.currentStatus != 9);
            case HEALTH_CONDITION -> setRelevant(this.currentStatus != 0);
        }
    }

    public int getSeverity() {
        return this.severity;
    }

}
