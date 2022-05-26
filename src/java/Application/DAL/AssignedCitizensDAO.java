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

public class AssignedCitizensDAO implements IDatabaseActions<Pair<Account, List<Citizen>>>
{
        @Override
        public Pair<Account, List<Citizen>> create(Pair<Account, List<Citizen>> input)
        {
            var dao = new AbstractDAO<Pair<Account, List<Citizen>>>() {

                @Override
                protected Pair<Account, List<Citizen>> execute(PreparedStatement statement) throws SQLException
                {
                    for (Citizen citizen : input.getValue())
                    {
                        AbstractDAO.setPlaceholders(statement, input.getKey(), citizen.getID());
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
        public void update(Pair<Account, List<Citizen>> input)
        {
            var dao = new AbstractDAO<Pair<Account, List<Citizen>>>() {

                @Override
                protected Pair<Account, List<Citizen>> execute(PreparedStatement statement) throws SQLException
                {
                    for (Citizen citizen : input.getValue())
                    {
                        AbstractDAO.setPlaceholders(statement, input.getKey(), citizen.getID(), input.getKey(), citizen.getID());
                        statement.addBatch();

                    }
                    statement.executeBatch();
                    return null;
                }

                @Override
                protected String getSQLStatement() {
                    // loop
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

                /**
                 * param: 1 - CitizenID | 2 -
                 *
                 * todo: REPORT: use implSpec in report ?
                 * @implSpec should be a stored procedure (reason: Ahead-Of-Time compilation & network traffic)
                 * */
                //@Override
                protected String _getSQLStatement() {
                    return """
                           DECLARE @SQL_CurrentCID int
                           DECLARE @hasCitizen BIT = FALSE
                                         
                           DECLARE _NEXT CURSOR
                               LOCAL STATIC READ_ONLY FORWARD_ONLY
                               FOR
                               SELECT DISTINCT FK_CID
                           FROM AssignedCitizen
                           WHERE FK_AID = ?
                               
                           OPEN _NEXT
                               FETCH NEXT FROM _NEXT INTO @SQL_CurrentCID
                           WHILE @@FETCH_STATUS = 0
                           BEGIN
                               IF @SQL_CurrentCID = ? -- test this citizen id
                               BEGIN
                                    set @hasCitizen = TRUE;
                               END;
                               FETCH NEXT FROM _NEXT INTO @SQL_CurrentCID
                           END
                           IF @hasCitizen = FALSE
                           BEGIN
                                -- add to assigned
                           END
                           CLOSE _NEXT
                           DEALLOCATE _NEXT
                           """;
                }
            };

            dao.start();

        }

        /**
         * does not get the related citizen info (functional/health) use the returned id to populate
         *
         * @param id is taken from the account, and all citizens assigned will be returned
         * */
        @Override
        public Pair<Account, List<Citizen>> read(int id) throws SQLException
        {
            var dao = new AbstractDAO<List<Citizen>>() {
                @Override
                protected List<Citizen> execute(PreparedStatement statement) throws SQLException
                {
                    var citizens = new ArrayList<Citizen>();

                    setPlaceholders(statement, id);

                    var rs = statement.executeQuery();

                    while (rs.next())
                    {
                        citizens.add(ResultSetHelpers.buildCitizen(rs));
                    }

                    return citizens;
                }

                @Override
                protected String getSQLStatement() {
                    return """
                            SELECT * FROM Citizen
                            JOIN AssignedCitizen on Citizen.CID = AssignedCitizen.FK_CID
                            JOIN School on Citizen.FK_cSchool = School.SID
                            JOIN Zipcode on School.FK_Zipcode = Zipcode.Zip
                            JOIN GeneralJournal on Citizen.CID = GeneralJournal.FK_Citizen
                            WHERE AssignedCitizen.FK_AID = ?
                            """;
                }
            };

            dao.start();

            return new Pair<>(new Account(id), dao.getResult().getValue());
        }

        @Override
        public List<Pair<Account, List<Citizen>>> readAll()
        {
            var dao = new AbstractDAO<List<Pair<Account, List<Citizen>>>>()
            {
                @Override
                protected List<Pair<Account, List<Citizen>>> execute(PreparedStatement statement) throws SQLException
                {
                    var result = new ArrayList<Pair<Account, List<Citizen>>>();
                    var rs = statement.executeQuery();

                    while (rs.next())
                    {
                        var accountID = rs.getInt("AID");

                        // construct citizen regardless
                        var citizen = ResultSetHelpers.buildCitizen(rs);

                        var currentAccountInList = result.stream().filter(accountListPair -> accountListPair.getKey().getID() == accountID).findFirst();;

                        if (currentAccountInList.isPresent())
                        {
                            // it is in the list so add citizen to that list // maybe a copy is made ??? todo: test
                            result.get(result.indexOf(currentAccountInList.get())).getValue().add(citizen);
                        }
                        else
                        {
                            // construct account here because this is where we need it.
                            var account = ResultSetHelpers.buildAccount(rs);

                            // add account and citizen to list;
                            result.add(new Pair<>(account, List.of(citizen)));
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
         * @param id is taken from the account, and all citizens assigned will be removed (only the reference)
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
                            WHERE FK_AID = ?
                            """;
                }
            };

            dao.start();
        }
    }
    