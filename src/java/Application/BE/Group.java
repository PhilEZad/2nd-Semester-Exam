package Application.BE;

import java.util.ArrayList;
import java.util.List;


    public class Group {

            List<Account> groupMembers = new ArrayList<Account>();
            String groupName;
            Citizen citizen;
            int id;

    public Group(List<Account> groupMembers, String groupName, int id) {
        this.id = id;
    }

    public Group()
    {

    }

    public List<Account> getGroupMembers() {
        return groupMembers;
    }

    public String getGroupName() {
        return groupName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupMembers(List<Account> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }
}
