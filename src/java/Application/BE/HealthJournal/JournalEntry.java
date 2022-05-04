package Application.BE.HealthJournal;

public class JournalEntry
{
    //subCategories
    // constant - fs3
    String title;

    // user input
    String description;

    ERelevanceState relevance;

    ESeverityState severity;

    // TODO: 04-05-2022 maybe
    String toolTip;

    public JournalEntry(String title, String description)
    {
        this.title = title;
        this.description = description;
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
}
