package Application.DAL;

import Application.BE.Citizen;
import Application.BE.GeneralJournal;
import Application.BE.School;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHelpers
{

    /**
     * todo: doc
     * */
    public static Citizen buildCitizen(ResultSet rs) throws SQLException
    {
        return new Citizen(
                rs.getInt("CID"),
                buildGeneralJournal(rs),
                buiSchool(rs),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getInt("age"),
                rs.getBoolean("isTemplate")
        );
    }

    /**
     * todo: doc
     * */
    public static School buiSchool(ResultSet rs) throws SQLException
    {
        return new School(
                rs.getInt("SID"),
                rs.getString("schoolName"),
                rs.getInt("FK_Zipcode"),
                rs.getString("city")
        );
    }

    /**
     * todo: doc
     * */
    public static Citizen buildCitizen(ResultSet rs, GeneralJournal journal, School school) throws SQLException
    {
        return new Citizen(
                rs.getInt("CID"),
                journal,
                school,
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getInt("age"),
                rs.getBoolean("isTemplate")
        );
    }

    /**
     * todo: doc
     * */
    public static GeneralJournal buildGeneralJournal(ResultSet rs) throws SQLException
    {
        return new GeneralJournal(
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
    }


}
