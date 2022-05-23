package Application.DAL.TemplateMethod;

import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.DAL.TemplateMethod.AbstractDAO_TP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLInput;

public class SchoolDAO2
{
    public final AbstractDAO_TP<Object, Object> read = new AbstractDAO_TP<>() {
        @Override
        protected Object execute(PreparedStatement statement, Object input) {
            return null;
        }

        @Override
        protected String getSQLStatement() {
            return null;
        }
    };

    public School create(School input) throws Exception {
        var query = new AbstractDAO_TP<School, School>() {
            @Override
            protected School execute(PreparedStatement statement, School input) throws SQLException {
                statement.setString(1,input.getSchoolName());
                statement.setInt(2,input.getZipCode());

                statement.execute();

                return null; // return value not used; only the id
            }

            @Override
            protected String getSQLStatement() {
                return "INSERT INTO schools (schoolName, schoolZipCode) VALUES (?, ?)";
            }
        };

        if (!query.run(input))
            throw query.getLastError();

        return new School(
                (int) query.getResultID(),
                input.getSchoolName(),
                input.getZipCode(),
                input.getCityName()
        );
    }

}
