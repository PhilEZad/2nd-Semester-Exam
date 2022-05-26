package Application.DAL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;
import Application.DAL.TemplateMethod.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements IDatabaseActions<Account> {

    /**
     * Creates an account from account entity given.
     * @param input
     * @return
     */
    @Override
    public Account create(Account input)
    {
        var dao = new AbstractDAO<Account>()
        {
            @Override
            protected Account execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, input.getUsername(), input.getPassword(), input.getFirstName(), input.getLastName(), input.getEmail(), input.getSchool().getID(), input.getAuthLevel());
                statement.execute();
                return input;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        INSERT INTO Account (username, password, firstName, lastname, email, FK_aSchool, accountType) 
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """;
            }
        };

        dao.start();

        if (dao.isSuccessful())
            input.setID(dao.getResult().getKey());

        return input;
    }


    /**
     * Deletes a specific account based on account entity given.
     * @param accountID
     */
    @Override
    public void delete(int accountID)
    {
        var dao = new AbstractDAO<>() {
            @Override
            protected Account execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, accountID, accountID);
                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        DELETE FROM AssignedCitizen
                        WHERE EXISTS (SELECT * FROM AssignedCitizen WHERE FK_AID = ?)
                        DELETE FROM account
                        WHERE AID = ?
                        """;
            }
        };

        dao.start();
    }

    /**
     * Returns a specific account based on account entity id given.
     * @param accountID
     * @return
     */
    @Override
    public Account read(int accountID)
    {
        var dao = new AbstractDAO<Account>()
        {
            @Override
            protected Account execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, accountID);
                ResultSet rs = statement.executeQuery();

                rs.next();

                var school = new School(
                        rs.getInt("SID"),
                        rs.getString("schoolName"),
                        rs.getInt("Zip"),
                        rs.getString("city")
                );

                return new Account(
                        rs.getInt("AID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        school,
                        rs.getByte("accountType") == 0x01,
                        rs.getByte("accountType") == 0x10);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM Account
                        JOIN School ON Account.FK_aSchool = School.SID
                        JOIN zipCode ON School.FK_Zipcode = zipCode.Zip
                        WHERE AID = ?
                        """;
            }
        };

        dao.start();

        return dao.getResult().getValue();
    }


    /**
     * Returns a list of all accounts.
     * @return
     */
    @Override
    public List<Account> readAll()
    {
        var dao = new AbstractDAO<List<Account>>()
        {
            @Override
            protected List<Account> execute(PreparedStatement statement) throws SQLException
            {
                var result = new ArrayList<Account>();
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {

                    School school = new School(
                            rs.getInt("SID"),
                            rs.getString("schoolName"),
                            rs.getInt("Zip"), rs.getString("city")
                    );

                    Account student = new Account(
                            rs.getInt("AID"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("email"),
                            school,
                            rs.getByte("accountType") == 0x01,
                            rs.getByte("accountType") == 0x10);

                    result.add(student);
                }

                return result;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM Account
                        JOIN School ON Account.FK_aSchool = School.SID
                        JOIN zipCode ON School.FK_Zipcode = zipCode.Zip
                        WHERE AID = ?
                        """;
            }
        };

        dao.start();

        return dao.getResult().getValue();
    }

    /**
     * Updates accounts using an account entity.
     * @param input
     */
    @Override
    public void update(Account input)
    {
        var dao = new AbstractDAO<Account>()
        {
            @Override
            protected Account execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, input.getFirstName(), input.getLastName(), input.getEmail(), input.getID());
                statement.executeUpdate();
                return input;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE Account
                        SET firstName = ?, lastname = ?, email = ? 
                        WHERE AID = ?
                        """;
            }
        };

        dao.start();
    }

    public Account read(String username)
    {
        var dao = new AbstractDAO<Account>()
        {
            @Override
            protected Account execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, username);
                ResultSet rs = statement.executeQuery();

                rs.next();

                var school = new School(
                        rs.getInt("SID"),
                        rs.getString("schoolName"),
                        rs.getInt("Zip"),
                        rs.getString("city")
                );

                return new Account(
                        rs.getInt("AID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        school,
                        rs.getByte("accountType") == 0x01,
                        rs.getByte("accountType") == 0x10);
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM Account
                        JOIN School ON Account.FK_aSchool = School.SID
                        JOIN zipCode ON School.FK_Zipcode = zipCode.Zip
                        WHERE username = ?
                        """;
            }
        };

        dao.start();

        return dao.getResult().getValue();
    }
}
