package Application.DAL;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.BE.GeneralJournal;
import Application.BE.School;
import Application.DAL.TemplateMethod.AbstractDAO;
import Application.DAL.TemplateMethod.IDatabaseActions;
import Application.DAL.util.ResultSetHelpers;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignedAccountsDAO implements IDatabaseActions<Pair<Citizen, List<Account>>>
{

    @Override
    public Pair<Citizen, List<Account>> create(Pair<Citizen, List<Account>> input)
    {
        var dao = new AbstractDAO<Pair<Citizen, List<Account>>>() {

            @Override
            protected Pair<Citizen, List<Account>> execute(PreparedStatement statement) throws SQLException
            {
                for (Account account : input.getValue())
                {
                    AbstractDAO.setPlaceholders(statement, account.getID(), input.getKey());
                    statement.addBatch();

                }
                statement.executeBatch();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                            INSERT INTO AssignedCitizen (FK_AID, FK_CID)
                            VALUES (?, ?)
                            """;
            }
        };

        dao.start();

        // TODO: 25-05-2022 ?? create test to see whether or not the sizes always match (maybe some edge case)
        var ids = dao.getAllReturnedIDs();
        for (int i = 0; i < Math.min(input.getValue().size(), ids.size()); i++)
        {
            input.getValue().get(i).setID(ids.get(i));
        }

        return input;
    }

    @Override
    public void update(Pair<Citizen, List<Account>> input)
    {
        var dao = new AbstractDAO<Pair<Citizen, List<Account>>>() {

            @Override
            protected Pair<Citizen, List<Account>> execute(PreparedStatement statement) throws SQLException {
                for (Account account : input.getValue()) {
                    AbstractDAO.setPlaceholders(statement, account.getID(), input.getKey(), account.getID(), input.getKey());
                    statement.addBatch();

                }
                statement.executeBatch();
                return null;
            }

            @Override
            protected String getSQLStatement() {

                // if current is in sql
                    // do nothing
                // if not in sql
                    // add to sql
                // if in sql but not in list - removed in application
                    // remove from database (ignored)

                return """
                            IF NOT EXISTS(SELECT * FROM AssignedCitizen WHERE FK_AID = ? AND FK_CID = ?) -- current is not in sql
                            BEGIN
                            	INSERT INTO AssignedCitizen (FK_AID, FK_CID) VALUES (?, ?)
                            END
                            """;
            }
        };

        dao.start();

    }

    /**
     *
     * @param id is taken from the citizen, and all accounts assigned will be returned
     * */
    @Override
    public Pair<Citizen, List<Account>> read(int id) throws SQLException
    {
        var dao = new AbstractDAO<List<Account>>() {
            @Override
            protected List<Account> execute(PreparedStatement statement) throws SQLException
            {
                var accounts = new ArrayList<Account>();

                setPlaceholders(statement, id);

                var rs = statement.executeQuery();

                while (rs.next())
                {
                    accounts.add(ResultSetHelpers.buildAccount(rs));
                }

                return accounts;
            }

            @Override
            protected String getSQLStatement() {
                return """
                            SELECT * FROM Citizen
                            JOIN AssignedCitizen on Citizen.CID = AssignedCitizen.FK_CID
                            JOIN School on Citizen.FK_cSchool = School.SID
                            JOIN Zipcode on School.FK_Zipcode = Zipcode.Zip
                            JOIN GeneralJournal on Citizen.CID = GeneralJournal.FK_Citizen
                            WHERE AssignedCitizen.FK_CID = ?
                            """;
            }
        };

        dao.start();

        return new Pair<>(new Citizen(id), dao.getResult().getValue());
    }

    @Override
    public List<Pair<Citizen, List<Account>>> readAll()
    {
        var dao = new AbstractDAO<List<Pair<Citizen, List<Account>>>>()
        {
            @Override
            protected List<Pair<Citizen, List<Account>>> execute(PreparedStatement statement) throws SQLException
            {
                var result = new ArrayList<Pair<Citizen, List<Account>>>();
                var rs = statement.executeQuery();

                while (rs.next())
                {
                    var citizenID = rs.getInt("CID");

                    // construct account regardless
                    var account = ResultSetHelpers.buildAccount(rs);

                    var currentCitizenInList = result.stream().filter(accountListPair -> accountListPair.getKey().getID() == citizenID).findFirst();;

                    if (currentCitizenInList.isPresent())
                    {
                        // it is in the list so add citizen to that list // maybe a copy is made ??? todo: test
                        result.get(result.indexOf(currentCitizenInList.get())).getValue().add(account);
                    }
                    else
                    {
                        // construct citizen here because this is where we need it.
                        var citizen = ResultSetHelpers.buildCitizen(rs);

                        // add account and citizen to list;
                        result.add(new Pair<>(citizen, List.of(account)));
                    }
                }

                return result;
            }

            @Override
            protected String getSQLStatement() {
                return """
                            SELECT * FROM AssignedCitizen
                            JOIN Account on AssignedCitizen.FK_AID = Account.AID
                            JOIN Citizen on Citizen.CID = AssignedCitizen.FK_CID
                            JOIN School on School.SID = Account.FK_aSchool AND Citizen.FK_cSchool = School.SID
                            JOIN zipCode ON School.FK_Zipcode = zipCode.Zip
                            """;
            }
        };

        dao.start();;

        return dao.getResult().getValue();
    }

    /**
     * @param id is taken from the citizen, and all accounts assigned will be removed (only the reference)
     * */
    @Override
    public void delete(int id)
    {
        var dao = new AbstractDAO<>() {

            @Override
            protected Object execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, id);
                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                            DELETE FROM AssignedCitizen
                            WHERE FK_CID = ?
                            """;
            }
        };

        dao.start();
    }

}

