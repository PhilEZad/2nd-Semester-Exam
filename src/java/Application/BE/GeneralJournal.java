package Application.BE;

public class GeneralJournal implements IUniqueIdentifier<Integer>
{
    private int id;
    private String coping;
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
        this.id = -1;
        this.coping = "";
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

    public GeneralJournal(int generalID, String coping, String motivation, String resources, String roles, String habits, String eduAndJob, String lifeStory, String healthInfo, String assistiveDevices, String homeLayout, String network) {
        this.id = generalID;
        this.coping = coping;
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

    public String getCoping() {
        return coping;
    }

    public void setCoping(String coping) {
        if (coping == null)
            this.coping = "";
        else this.coping = coping;

    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        if (motivation == null)
            this.motivation = "";
        else this.motivation = motivation;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        if (resources == null)
            this.resources = "";
        else this.resources = resources;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        if (roles == null)
            this.roles = "";
        else this.roles = roles;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        if (habits == null)
            this.habits = "";
        else this.habits = habits;
    }

    public String getEduAndJob() {
        return eduAndJob;
    }

    public void setEduAndJob(String eduAndJob) {
        if (eduAndJob == null)
            this.eduAndJob = "";
        else this.eduAndJob = eduAndJob;
    }

    public String getLifeStory() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        if (lifeStory == null)
            this.lifeStory = "";
        else this.lifeStory = lifeStory;
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        if (healthInfo == null)
            this.healthInfo = "";
        else this.healthInfo = healthInfo;
    }

    public String getAssistiveDevices() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        if (assistiveDevices == null)
            this.assistiveDevices = "";
        else this.assistiveDevices = assistiveDevices;
    }

    public String getHomeLayout() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        if (homeLayout == null)
            this.homeLayout = "";
        else this.homeLayout = homeLayout;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        if (network == null)
            this.network = "";
        else this.network = network;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
    }
}
