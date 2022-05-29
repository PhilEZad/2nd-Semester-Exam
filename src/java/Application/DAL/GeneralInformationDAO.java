package Application.DAL;

import Application.BE.GeneralJournal;
import Application.DAL.TemplateMethod.AbstractDAO;
import Application.DAL.TemplateMethod.IDatabaseActions;
import Application.DAL.util.ResultSetHelpers;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mads Mandahl-Barth
 * @author Philip Zadeh
 * */
public class GeneralInformationDAO implements IDatabaseActions<GeneralJournal>
{
    @Override
    public GeneralJournal create(GeneralJournal input)
    {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement,
                    input.getCitizenID(),
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

                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        INSERT INTO GeneralJournal (FK_Citizen, coping, motivation, [resources], roles, habits, eduAndJob, lifestory, healthInfo, assistiveDevices, homelayout, network)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;
            }
        };

        dao.start();
        input.setID(dao.getResult().getKey());
        return input;
    }

    @Override
    public void update(GeneralJournal input)
    {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement,
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
                        input.getNetwork(),
                        input.getID()
                );

                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE GeneralJournal
                        SET coping = ?, motivation = ?, [resources] = ?, roles = ?, habits = ?,
                        eduAndjob = ?, lifestory = ?, healthInfo = ?, assistiveDevices = ?,
                        homelayout = ?, network = ?
                        WHERE JournalGID = ?
                        """;
            }
        };

        dao.start();
    }

    @Override
    public GeneralJournal read(int id)
    {
        var dao = new AbstractDAO<GeneralJournal>()
        {
            @Override
            protected GeneralJournal execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, id);
                var rs = statement.executeQuery();
                return ResultSetHelpers.buildGeneralJournal(rs);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM GeneralJournal
                        WHERE JournalGID = ?
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    @Override
    public List<GeneralJournal> readAll()
    {
        var dao = new AbstractDAO<List<GeneralJournal>>()
        {
            @Override
            protected List<GeneralJournal> execute(PreparedStatement statement) throws SQLException
            {
                List<GeneralJournal> results = new ArrayList<>();
                var rs = statement.executeQuery();

                while (rs.next())
                {
                    results.add(ResultSetHelpers.buildGeneralJournal(rs));
                }

                return results;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM GeneralJournal
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    @Override
    public void delete(int id)
    {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, id);
                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        DELETE FROM GeneralJournal
                        WHERE JournalGID = ?
                        """;
            }
        };

        dao.start();
    }

    public static void main(String[] args) {
        GeneralInformationDAO daoTest = new GeneralInformationDAO();

        GeneralJournal journal = new GeneralJournal(
                2,
                1,
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
