package backend.entity;

public class Entry
{
    private Category category;

    private Integer id = null;
    private String assessment;
    private String cause;
    private String implications;
    private Integer currentStatus = null;
    private Integer expectedStatus = null;
    private String citizenGoals = "";
    private String note = "";
    private Integer severity = null;

    public Entry(Category category, String assessment, String cause, String implications, String citizenGoals, String note)
    {
        this.category = category;
        this.assessment = assessment;
        this.cause = cause;
        this.implications = implications;
        this.citizenGoals = citizenGoals;
        this.note = note;
    }

    public Entry(Entry partial, int currentStatus, int expectedStatus, int severity)
    {
        this (partial.category, partial.assessment, partial.cause, partial.implications, partial.citizenGoals, partial.note);
        this.currentStatus = currentStatus;
        this.expectedStatus = expectedStatus;
        this.severity = severity;
    }

    public Entry(Entry partial, int id)
    {
        this(partial, partial.currentStatus, partial.expectedStatus, partial.severity);
        this.id = id;
    }

    public Entry(int id, Category category, String assessment, String cause, String implications, String citizenGoals, String notes, int currentStatus, int expectedStatus, int severity)
    {
        this.id = id;
        this.category = category;
        this.assessment = assessment;
        this.cause = cause;
        this.implications = implications;
        this.citizenGoals = citizenGoals;
        this.note = notes;
        this.currentStatus = currentStatus;
        this.expectedStatus = expectedStatus;
        this.severity = severity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCitizenGoals() {
        return citizenGoals;
    }

    public void setCitizenGoals(String citizenGoals) {
        this.citizenGoals = citizenGoals;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
