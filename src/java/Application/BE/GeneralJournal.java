package Application.BE;

public class GeneralJournal
{
    private String mastering;
    private String motivation;
    private String resources;
    private String roles;
    private String habits;
    private String eduAndJob;
    private String lifeStory;
    private String healthInfo;
    private String assistiveDevices;
    private String homeLayout;
    private String network;

    public GeneralJournal()
    {
        this.mastering = "";
        this.motivation = "";
        this.resources = "";
        this.roles = "";
        this.habits = "";
        this.eduAndJob = "";
        this.lifeStory = "";
        this.healthInfo = "";
        this.assistiveDevices = "";
        this.homeLayout = "";
        this.network = "";
    }

    public GeneralJournal(String mastering, String motivation, String resources, String roles, String habits, String eduAndJob, String lifeStory, String healthInfo, String assistiveDevices, String homeLayout, String network) {
        this.mastering = mastering;
        this.motivation = motivation;
        this.resources = resources;
        this.roles = roles;
        this.habits = habits;
        this.eduAndJob = eduAndJob;
        this.lifeStory = lifeStory;
        this.healthInfo = healthInfo;
        this.assistiveDevices = assistiveDevices;
        this.homeLayout = homeLayout;
        this.network = network;
    }

    public String getMastering() {
        return mastering;
    }

    public void setMastering(String mastering) {
        this.mastering = mastering;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public String getEduAndJob() {
        return eduAndJob;
    }

    public void setEduAndJob(String eduAndJob) {
        this.eduAndJob = eduAndJob;
    }

    public String getLifeStory() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = healthInfo;
    }

    public String getAssistiveDevices() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices = assistiveDevices;
    }

    public String getHomeLayout() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout = homeLayout;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }
}
