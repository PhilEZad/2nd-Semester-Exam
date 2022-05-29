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

public class FunctionalAbilityDAO implements IDatabaseActions<FunctionalEntry>
{
    @Override
    public FunctionalEntry create(FunctionalEntry input)
    {
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
                        input.getImplications(),
                        input.getCitizenGoals(),
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
                        INSERT INTO FunctionalEntry (FK_Citizen, FK_Category, assessment, cause, implications, goals, notes, currentLevel, expectedLevel)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;
            }
        };

        dao.start();
        input.setID(dao.getResult().getKey());
        return input;
    }

    @Override
    public void update(FunctionalEntry input)
    {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement,
                        input.getAssessment(),
                        input.getCause(),
                        input.getImplications(),
                        input.getCitizenGoals(),
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
                        UPDATE FunctionalEntry
                        SET assessment = ?, cause = ?,implications  = ?, goals = ?, notes = ?, currentLevel = ?, expectedLevel = ?
                        WHERE JournalFID = ?
                        """;
            }
        };

        dao.start();
    }

    @Override
    public FunctionalEntry read(int id) {
        var dao = new AbstractDAO<FunctionalEntry>()
        {
            @Override
            protected FunctionalEntry execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, id);
                var rs = statement.executeQuery();
                return ResultSetHelpers.buildFunctionalEntry(rs);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM FunctionalEntry
                        JOIN Categories C on C.CatID = FunctionalEntry.FK_Category
                        WHERE JournalFID = ?
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    public List<FunctionalEntry> readAll(int citizen_id) {
        var dao = new AbstractDAO<List<FunctionalEntry>>()
        {
            @Override
            protected List<FunctionalEntry> execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, citizen_id);
                List<FunctionalEntry> results = new ArrayList<>();
                var rs = statement.executeQuery();

                while (rs.next())
                {
                    results.add(ResultSetHelpers.buildFunctionalEntry(rs));
                }

                return results;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM FunctionalEntry
                        JOIN Categories C on C.CatID = FunctionalEntry.FK_Category
                        WHERE FK_Citizen = ?
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    @Override
    public List<FunctionalEntry> readAll() {
        var dao = new AbstractDAO<List<FunctionalEntry>>()
        {
            @Override
            protected List<FunctionalEntry> execute(PreparedStatement statement) throws SQLException
            {
                List<FunctionalEntry> results = new ArrayList<>();
                var rs = statement.executeQuery();

                while (rs.next())
                {
                    results.add(ResultSetHelpers.buildFunctionalEntry(rs));
                }

                return results;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM FunctionalEntry
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
                        DELETE FROM FunctionalEntry
                        WHERE JournalFID = ?
                        """;
            }
        };

        dao.start();
    }

    public void updateAll(List<FunctionalEntry> functionalAbilities)
    {
        for (FunctionalEntry functionalEntry : functionalAbilities.stream().filter(entry -> !entry.equals(new FunctionalEntry())).toList())
        {
            if (functionalEntry.getID() == -1)
            {
                create(functionalEntry);
            }
            else
                update(functionalEntry);
        }
    }
}
