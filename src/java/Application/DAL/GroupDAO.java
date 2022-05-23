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

    @Override
    public Group create(Group input)
    {
        String sql = """
                
                """;

        return null;
    }

    @Override
    public void update(Group input)
    {

    }

    @Override
    public Group read(int id) throws SQLException {
        return null;
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

    @Override
    public List<Group> readAll()
    {
        String sql;

        return null;
    }

    @Override
    public void delete(int id)
    {

    }
}
