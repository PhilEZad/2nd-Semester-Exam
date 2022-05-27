package Application.BE;

import java.util.Objects;

public class GeneralJournal implements IUniqueIdentifier<Integer>
{
    private int id;
    private int citizenID;
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

    public GeneralJournal(int generalID, int citizenID, String coping, String motivation, String resources, String roles, String habits, String eduAndJob, String lifeStory, String healthInfo, String assistiveDevices, String homeLayout, String network) {
        this.citizenID = citizenID;
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
        this.motivation = Objects.requireNonNullElse(motivation, "");
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = Objects.requireNonNullElse(resources, "");
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
        this.eduAndJob = Objects.requireNonNullElse(eduAndJob, "");
    }

    public String getLifeStory() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory = Objects.requireNonNullElse(lifeStory, "");
    }

    public String getHealthInfo() {
        return healthInfo;
    }

    public void setHealthInfo(String healthInfo) {
        this.healthInfo = Objects.requireNonNullElse(healthInfo, "");
    }

    public String getAssistiveDevices() {
        return assistiveDevices;
    }

    public void setAssistiveDevices(String assistiveDevices) {
        this.assistiveDevices = Objects.requireNonNullElse(assistiveDevices, "");
    }

    public String getHomeLayout() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout = Objects.requireNonNullElse(homeLayout, "");
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = Objects.requireNonNullElse(network, "");
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
    }

    public int getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }
}
