package backend.entity;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    private int groupID;
    private String groupName;
    private final List<User> groupMembers;
    private Citizen citizen;

    public Group(String name, List<User> users, Citizen citizen)
    {
        this.groupMembers = new ArrayList<>(users);
        this.groupName = name;
        this.citizen = citizen;
    }

    public Group(Group partial, int id)
    {
        this(partial.groupName, partial.groupMembers, partial.citizen);
        this.groupID = id;
    }


    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getGroupMembers() {
        return groupMembers;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
}
