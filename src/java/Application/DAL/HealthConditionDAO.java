package Application.DAL;

import Application.BE.FunctionalEntry;
import Application.BE.GeneralJournal;
import Application.BE.HealthEntry;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.DAL.TemplateMethod.AbstractDAO;
import Application.DAL.TemplateMethod.IDatabaseActions;
import Application.DAL.util.ResultSetHelpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mads Mandahl-Barth
 * @author Philip Zadeh
 * */
public class HealthConditionDAO implements IDatabaseActions<HealthEntry>
{
    @Override
    public HealthEntry create(HealthEntry input) {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement,
                        input.getCitizenID(),
                        input.getCategory().getID(),
                        input.getAssessment(),
                        input.getCause(),
                        input.getNote(),
                        input.getCurrentStatus(),
                        input.getExpectedStatus()
                );

                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        INSERT INTO HealthEntry (FK_Citizen, FK_Category, assement, cause, notes, currentLevel, expectedLevel)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """;
            }
        };

        dao.start();
        input.setID(dao.getResult().getKey());
        return input;
    }

    @Override
    public void update(HealthEntry input) {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement,
                        input.getAssessment(),
                        input.getCause(),
                        input.getNote(),
                        input.getCurrentStatus(),
                        input.getExpectedStatus(),
                        input.getID()
                );

                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE HealthEntry
                        SET assement = ?, cause = ?, notes = ?, currentLevel = ?, expectedLevel = ?
                        WHERE JournalHID = ?
                        """;
            }
        };

        dao.start();
    }

    @Override
    public HealthEntry read(int id) {
        var dao = new AbstractDAO<HealthEntry>()
        {
            @Override
            protected HealthEntry execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, id);
                var rs = statement.executeQuery();
                return ResultSetHelpers.buildHealthEntry(rs);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM HealthEntry
                        JOIN Categories C on C.CatID = HealthEntry.FK_Category
                        WHERE JournalHID = ?
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    public List<HealthEntry> readAll(int citizen_id) {
        var dao = new AbstractDAO<List<HealthEntry>>()
        {
            @Override
            protected List<HealthEntry> execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, citizen_id);
                List<HealthEntry> results = new ArrayList<>();
                var rs = statement.executeQuery();

                while (rs.next())
                {
                    results.add(ResultSetHelpers.buildHealthEntry(rs));
                }

                return results;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM HealthEntry
                        JOIN Categories C on C.CatID = HealthEntry.FK_Category
                        WHERE FK_Citizen = ?
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    @Override
    public List<HealthEntry> readAll() {
        var dao = new AbstractDAO<List<HealthEntry>>()
        {
            @Override
            protected List<HealthEntry> execute(PreparedStatement statement) throws SQLException
            {
                List<HealthEntry> results = new ArrayList<>();
                var rs = statement.executeQuery();

                while (rs.next())
                {
                    results.add(ResultSetHelpers.buildHealthEntry(rs));
                }

                return results;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM HealthEntry
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    @Override
    public void delete(int id) {
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
                        DELETE FROM HealthEntry
                        WHERE FK_Citizen = ?
                        """;
            }
        };

        dao.start();
    }

    public void updateAll(List<HealthEntry> healthConditions)
    {
        for (HealthEntry healthCondition : healthConditions.stream().filter(entry -> !entry.equals(new HealthEntry())).toList())
        {
            if (healthCondition.getID() == -1)
            {
                create(healthCondition);
            }
            else
                update(healthCondition);
        }
    }
}
