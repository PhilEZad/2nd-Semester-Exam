package Application.DAL;

import Application.BE.*;
import Application.DAL.TemplateMethod.AbstractDAO;
import Application.DAL.TemplateMethod.IDatabaseActions;
import Application.DAL.util.ResultSetHelpers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO implements IDatabaseActions<Citizen>
{
    @Override
    public Citizen create(Citizen input)
    {
        var dao = new AbstractDAO<Citizen>(){

            @Override
            protected Citizen execute(PreparedStatement statement) throws SQLException {

                setPlaceholders(statement,
                        input.getFirstname(),
                        input.getLastname(),
                        input.getAge(),
                        input.getSchool().getID(),
                        input.getTemplate()
                );

                statement.executeUpdate();
                return input;
            }

            @Override
            protected String getSQLStatement() {
                return """
                INSERT INTO Citizen (firstName, lastName, age, FK_cSchool, isTemplate) 
                VALUES (?, ?, ?, ?, ?)
                """;
            }
        };

        dao.start();
        input.setID(dao.getResult().getKey());
        return input;
    }

    @Override
    public void update(Citizen input)
    {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException {
                setPlaceholders(statement,
                        input.getFirstname(),
                        input.getLastname(),
                        input.getAge(),
                        input.getSchool().getID(),
                        input.getID()
                );

                statement.executeUpdate();

                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE Citizen
                        SET firstName = ?, lastName = ?, age = ?, FK_cSchool = ?
                        WHERE CID = ?
                        """;
            }
        };

        dao.start();
    }

    @Override
    public Citizen read(int id)
    {
        var dao = new AbstractDAO<Citizen>()
        {
            @Override
            protected Citizen execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, id);
                ResultSet result = statement.executeQuery();
                result.next();

                return ResultSetHelpers.buildCitizen(result);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM Citizen
                        JOIN School ON School.SID = Citizen.FK_cSchool
                        JOIN GeneralJournal GJ on Citizen.CID = GJ.FK_Citizen
                        JOIN Zipcode ON Zipcode.zip = School.FK_Zipcode
                        WHERE CID = ?
                        """;
            }
        };

        dao.start();

        return dao.getResult().getValue();
    }

    @Override
    public List<Citizen> readAll()
    {
        var dao = new AbstractDAO<List<Citizen>>()
        {
            @Override
            protected List<Citizen> execute(PreparedStatement statement) throws SQLException {
                ResultSet result = statement.executeQuery();
                List<Citizen> citizens = new ArrayList<>();

                while (result.next()) {

                    citizens.add(ResultSetHelpers.buildCitizen(result));
                }

                return citizens;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM Citizen
                        JOIN School ON School.SID = Citizen.FK_cSchool
                        JOIN GeneralJournal GJ on Citizen.CID = GJ.FK_Citizen
                        JOIN Zipcode ON Zipcode.zip = School.FK_Zipcode
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
                        DECLARE @citizenID INT = ?;
                        
                        DELETE FROM AssignedCitizen -- remove all account <-> citizen relations
                        WHERE AssignedCitizen.FK_CID = @citizenID
                        
                        DELETE FROM GeneralJournal  -- remove general information
                        WHERE GeneralJournal.FK_Citizen = @citizenID
                        
                        DELETE FROM FunctionalEntry  -- remove all functional entries that has been edited
                        WHERE FunctionalEntry.FK_Citizen = @citizenID
                        
                        DELETE FROM HealthEntry -- remove health entries
                        WHERE HealthEntry.FK_Citizen = @citizenID
                        
                        DELETE FROM Citizen -- finally destroy the citizen
                        WHERE CID = @citizenID
                        """;
            }
        };

        dao.start();
    }
}
