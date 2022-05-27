package Application.DAL.util;

import Application.BE.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHelpers
{

    public static Category buildCategory(ResultSet rs, CategoryType type) throws SQLException {
        return new Category(
                rs.getInt("CatID"),
                rs.getString("categoryName"),
                rs.getInt("FK_ParentCat"),
                type
        );
    }

    public static FunctionalEntry buildFunctionalEntry(ResultSet rs) throws SQLException {
        return new FunctionalEntry(
                rs.getInt("JournalHID"),
                rs.getInt("FK_Citizen"),
                buildCategory(rs, CategoryType.FUNCTIONAL_ABILITY),
                rs.getString("assessment"),
                rs.getString("cause"),
                rs.getString("implications"),
                rs.getString("goals"),
                rs.getString("notes"),
                rs.getInt("currentLevel"),
                rs.getInt("expetedLevel")
        );
    }

    public static HealthEntry buildHealthEntry(ResultSet rs) throws SQLException {
        return new HealthEntry (
                rs.getInt("JournalHID"),
                rs.getInt("FK_Citizen"),
                buildCategory(rs, CategoryType.HEALTH_CONDITION),
                rs.getString("assement"),
                rs.getString("cause"),
                rs.getString("notes"),
                rs.getInt("currentLevel"),
                rs.getInt("expetedLevel")
        );
    }

    /**
     * todo: doc
     * */
    public static Citizen buildCitizen(ResultSet rs) throws SQLException
    {
        return new Citizen(
                rs.getInt("CID"),
                buildGeneralJournal(rs),
                buildSchool(rs),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getInt("age"),
                rs.getBoolean("isTemplate")
        );
    }

    /**
     * todo: doc
     * */
    public static School buildSchool(ResultSet rs) throws SQLException
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

    public static Account buildAccount(ResultSet rs) throws SQLException, IOException {
        return new Account(
                rs.getInt("AID"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("email"),
                buildSchool(rs),
                rs.getBoolean("isTeacher"),
                rs.getBoolean("isAdmin"));
    }

    /**
     * todo: doc
     * */
    public static GeneralJournal buildGeneralJournal(ResultSet rs) throws SQLException
    {
        return new GeneralJournal(
                rs.getInt("JournalGID"),
                rs.getInt("FK_Citizen"),
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
