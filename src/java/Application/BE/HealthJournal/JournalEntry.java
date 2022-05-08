package Application.BE.HealthJournal;

public class JournalEntry
{
    //subCategories
    // constant - fs3
    String title;

    // user input
    String description;

    String assessment;

    String cause;

    ERelevanceState relevance;

    ESeverityState severity;

    // TODO: 04-05-2022 maybe
    String toolTip;

    public JournalEntry(String title, String assessment)
    {
        this.title = title;
        this.assessment = assessment;
    }

    public JournalEntry(String title, ESeverityState severity)
    {
        this.title = title;
        this.severity = severity;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ERelevanceState getRelevance() {
        return relevance;
    }

    public ESeverityState getSeverity() {
        return severity;
    }

    public String getAssessment() {
        return assessment;
    }

    public String getCause(){return cause;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRelevance(ERelevanceState relevance) {
        this.relevance = relevance;
    }

    public void setSeverity(ESeverityState severity) {
        this.severity = severity;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public void setCause(String cause) {this.cause = cause;}
}
