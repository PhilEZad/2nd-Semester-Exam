package Application.DAL;

import Application.BE.GeneralJournal;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.DAL.TemplateMethod.IDatabaseActions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralDAO implements IDatabaseActions<GeneralJournal> {
    @Override
    public GeneralJournal create(GeneralJournal input) {
        String sql = """
                    INSERT INTO GeneralInfo (coping, motivation, resources, roles, habits, eduAndJob, lifestory, healthInfo, assistiveDevices, homelayout, network)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, input.getCoping());
            pstmt.setString(2, input.getMotivation());
            pstmt.setString(3, input.getResources());
            pstmt.setString(4, input.getRoles());
            pstmt.setString(5, input.getHabits());
            pstmt.setString(6, input.getEduAndJob());
            pstmt.setString(7, input.getLifeStory());
            pstmt.setString(8, input.getHealthInfo());
            pstmt.setString(9, input.getAssistiveDevices());
            pstmt.setString(10, input.getHomeLayout());
            pstmt.setString(11, input.getNetwork());

            pstmt.executeUpdate();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();
            return new GeneralJournal(
                    id,
                    input.getCoping(),
                    input.getMotivation(),
                    input.getResources(),
                    input.getRoles(),
                    input.getHabits(),
                    input.getEduAndJob(),
                    input.getLifeStory(),
                    input.getHealthInfo(),
                    input.getAssistiveDevices(),
                    input.getHomeLayout(),
                    input.getNetwork()
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void update(GeneralJournal input)
    {
        String sql = """
                UPDATE GeneralInfo
                SET coping = ?, motivation = ?, resources = ?, roles = ?, habits = ?, eduAndjob = ?, lifestory = ?, healthInfo = ?, assistiveDevices = ?, homelayout = ?, network = ?
                WHERE InfoID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, input.getCoping());
            ptsm.setString(2, input.getMotivation());
            ptsm.setString(3, input.getResources());
            ptsm.setString(4, input.getRoles());
            ptsm.setString(5, input.getHabits());
            ptsm.setString(6, input.getEduAndJob());
            ptsm.setString(7, input.getLifeStory());
            ptsm.setString(8, input.getHealthInfo());
            ptsm.setString(9, input.getAssistiveDevices());
            ptsm.setString(10, input.getHomeLayout());
            ptsm.setString(11, input.getNetwork());
            ptsm.setInt(12, input.getID());

            ptsm.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public GeneralJournal read(int id)
    {
        String sql = """
                    SELECT * FROM GeneralInfo
                    WHERE InfoID = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id);

            ResultSet result = ptsm.executeQuery();

            result.next();

            return new GeneralJournal(
                    result.getInt("InfoID"),
                    result.getString("coping"),
                    result.getString("motivation"),
                    result.getString("resources"),
                    result.getString("roles"),
                    result.getString("habits"),
                    result.getString("eduAndJob"),
                    result.getString("lifestory"),
                    result.getString("healthInfo"),
                    result.getString("assistiveDevices"),
                    result.getString("homelayout"),
                    result.getString("network")
            );
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<GeneralJournal> readAll() {
        String sql = """
                    SELECT * FROM GeneralInfo
                    """;
        List<GeneralJournal> returnList = new ArrayList<>();

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                GeneralJournal journal = new GeneralJournal(
                        rs.getInt("InfoID"),
                        rs.getString("coping"),
                        rs.getString("motivation"),
                        rs.getString("resources"),
                        rs.getString("roles"),
                        rs.getString("habits"),
                        rs.getString("eduAndJob"),
                        rs.getString("lifestory"),
                        rs.getString("healthInfo"),
                        rs.getString("assistiveDevices"),
                        rs.getString("homelayout"),
                        rs.getString("network")
                );
                returnList.add(journal);
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }

        return returnList;
    }

    @Override
    public void delete(int id) {
        String sql = """
                DELETE FROM GeneralInfo
                WHERE infoID = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GeneralDAO daoTest = new GeneralDAO();

        GeneralJournal journal = new GeneralJournal(
                2,
                "Coping2",
                "Motivation2",
                "Resources2",
                "Roles2",
                "Habits2",
                "eduAndJob2",
                "Life Story 2",
                "Health Info 2",
                "Assisitive Devices 2",
                "Home Layout 2",
                "Network 2"
        );

        daoTest.update(journal);

        System.out.print(journal);
    }
}
