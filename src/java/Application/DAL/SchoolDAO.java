package Application.DAL;

import Application.BE.School;
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

public class SchoolDAO implements IDatabaseActions<School>
{
    @Override
    public School create(School input)
    {
        var dao = new AbstractDAO<Void>() {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException
            {
                setPlaceholders(statement, input.getSchoolName(), input.getZipCode());
                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        INSERT INTO School (schoolName, FK_Zipcode) VALUES (?, ?)
                        """;
            }
        };

        dao.start();
        input.setID(dao.getResult().getKey());
        return input;
    }

    @Override
    public void update(School input)
    {
        var dao = new AbstractDAO<Void>()
        {
            @Override
            protected Void execute(PreparedStatement statement) throws SQLException {

                setPlaceholders(statement, input.getSchoolName(), input.getZipCode(), input.getID());
                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE school SET schoolName =  ?, FK_Zipcode = ? WHERE SID = ?
                        """;
            }
        };

        dao.start();
    }

    @Override
    public School read(int id)
    {
        var dao = new AbstractDAO<School>(){

            @Override
            protected School execute(PreparedStatement statement) throws SQLException {

                ResultSet rs = statement.executeQuery();
                setPlaceholders(statement, id);
                rs.next();
                return ResultSetHelpers.buildSchool(rs);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM School
                        JOIN Zipcode on Zipcode.Zip = School.FK_Zipcode
                        WHERE SID = ?
                        """;
            }
        };

        dao.start();
        return dao.getResult().getValue();
    }

    @Override
    public List<School> readAll()
    {
        var dao = new AbstractDAO<List<School>>(){

            @Override
            protected List<School> execute(PreparedStatement statement) throws SQLException
            {
                List<School> results = new ArrayList<>();
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    results.add(ResultSetHelpers.buildSchool(rs));
                }
                return results;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM School
                        JOIN Zipcode on Zipcode.Zip = School.FK_Zipcode
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
                        DELETE FROM School WHERE SID = ?
                        """;
            }
        };

        dao.start();
    }
}
