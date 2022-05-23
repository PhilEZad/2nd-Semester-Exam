package Application.DAL;

import Application.BE.Citizen;
import Application.BE.Group;
import Application.BE.*;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends TemplatePatternDAO<Group>
{


    CitizenDAO citizenDAO = new CitizenDAO();
    private long return_id = -1;

    @Override
    public Group create(Group input) {
        Boolean worked = false;
        String sqlCreateGroup = """
                INSERT INTO [Group] 
                (groupName, FK_Citizen)
                VALUES
                (?, ?)
                """;


        String sqlAccountGroup = """
                INSERT INTO AccountGroup 
                (FK_GroupID, FK_MemberID)
                VALUES
                (?, ?)
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement pscg = conn.prepareStatement(sqlCreateGroup, PreparedStatement.RETURN_GENERATED_KEYS);
            PreparedStatement psag = conn.prepareStatement(sqlAccountGroup);

            pscg.setString(1,input.getGroupName());
            pscg.setInt(2,input.getCitizen().getId());

            ResultSet rs = pscg.getGeneratedKeys();
            if (rs.next())
                this.return_id = rs.getInt(1);

            for (Account account: input.getGroupMembers()){
                psag.setLong(1,return_id);
                psag.setInt(2,input.getCitizen().getId());
                psag.execute();
            }
            psag.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return input;
    }

    @Override
    public void update(Group input) {
        String sqlUpdate = """
                UPDATE Group
                SET groupName = ?,
                FK_Citizen = ?    
                WHERE GID = ?            
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psug = conn.prepareStatement(sqlUpdate);
            psug.setString(1,input.getGroupName());
            psug.setInt(2,input.getCitizen().getId());
            psug.setInt(3,input.getId());

            psug.execute();

            // updateGroupMembers is being called after the main baseGroup information have been changed.
            updateGroupMembers(input);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private Boolean updateGroupMembers(Group input)
    {
        Boolean worked = false;
        String sqlDeleteMembers = """
                DELETE FROM AccountGroup
                WHERE FK_MemberID = ?
                """;

        String sqlNewGroupMembers = """
                INSERT INTO AccountGroup
                (FK_GroupID, FK_MemberID)
                VALUES (?, ?)
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psgm = conn.prepareStatement(sqlDeleteMembers);
            PreparedStatement psng = conn.prepareStatement(sqlNewGroupMembers);
            psgm.setInt(1,input.getId());

            if (psgm.execute()) {
                for (Account account : input.getGroupMembers()) {
                    psng.setInt(1, input.getId());
                    psng.setInt(2, account.getId());
                    worked = psng.execute();
                }
            }
            psng.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return worked;
    }

    @Override
    public Group read(int groupID) {
        Group group = new Group();
        String sqlread = """
                SELECT 
                groupName, FK_Citizen
                FROM [Group]
                WHERE GID = ?,
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psrg = conn.prepareStatement(sqlread);
            psrg.setInt(1, groupID);

            ResultSet rs = psrg.executeQuery();
            while (rs.next())
            {
                String groupName = rs.getString("groupName");
                int citizenID = rs.getInt("FK_Citizen");
                Citizen citizen = citizenDAO.read(citizenID);
                List<Account> members = readMembers(groupID);
                group.setGroupName(groupName);
                group.setId(groupID);
                group.setGroupMembers(members);
                group.setCitizen(citizen);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return group;
    }

    private List<Account> readMembers(int groupID) {
        List<Account> members = new ArrayList<>();
        String sqlReadGroup = """
               SELECT CHILD.CatID, CHILD.catName, PARENT.CatID AS ParentID, PARENT.catName AS parentName, NULL as [text]
               FROM dbo.Categories PARENT, dbo.Categories CHILD\s
               WHERE PARENT.CatID = CHILD.FK_ParentCat AND CHILD.CatID = ?
                                   
               UNION SELECT NULLABLE.CatID, NULLABLE.catName, NULL, NULL, Tooltip.text
               FROM dbo.Categories AS NULLABLE\s
               JOIN Tooltip ON NULLABLE.FK_Description = Tooltip.ToolTipID
               WHERE NULLABLE.FK_ParentCat IS NULL AND NULLABLE.CatID = ?
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            /*PreparedStatement psds = conn.prepareStatement(sqlReadGroup);

            psds.setInt(1, groupID);
            psds.setInt(2, groupID);

            psds.executeUpdate();
            psds.close();


             */
            PreparedStatement psrg = conn.prepareStatement(sqlReadGroup);
            psrg.setInt(1, groupID);
            psrg.setInt(2, groupID);

            ResultSet rs = psrg.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("AID");
                String username = rs.getString("username");
                String hashed_pwd = rs.getString("hashed_pwd");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                int privilegeLevel = rs.getInt("privilegeLevel");
                String schoolName = rs.getString("schoolName");
                int zipCode = rs.getInt("School.FK_Zipcode");
                int SID = rs.getInt("School.SID");
                String name = rs.getString("Zipcode.city");

                Location city = new Location(zipCode, name);
                School school = new School(SID, schoolName, city);
                Account account = new Account(id, username, hashed_pwd, firstname, lastname, email, school, privilegeLevel);
                members.add(account);

                psrg.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return members;
    }

    public List<Group> readAll() {
        List<Group> groups = new ArrayList<Group>();
        Group group = new Group();
        String sqlread = """
                SELECT 
                groupName, FK_Citizen, GID
                FROM [Group]
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psrg = conn.prepareStatement(sqlread);

            ResultSet rs = psrg.executeQuery();
            while (rs.next())
            {
                String groupName = rs.getString("groupName");
                int citizenID = rs.getInt("FK_Citizen");
                int GID = rs.getInt("GID");
                Citizen citizen = citizenDAO.read(citizenID);
                List<Account> members = readMembers(GID);
                group.setGroupName(groupName);
                group.setId(GID);
                group.setGroupMembers(members);
                group.setCitizen(citizen);
                groups.add(group);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return groups;
    }

    public void delete(int id) {

        String sqlDelete = """
                DELETE 
                FROM AccountGroup
                WHERE FK_GroupID = ?
                
                DELETE 
                FROM [Group]
                WHERE GID = ?
                """;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement psdg = conn.prepareStatement(sqlDelete);

            psdg.setInt(1,id);
            psdg.setInt(2, id);
            psdg.execute();
            psdg.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
