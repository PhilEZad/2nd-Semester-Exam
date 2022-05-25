package Application.DAL;

import Application.BE.Account;
import Application.BE.Citizen;
import Application.DAL.TemplateMethod.AbstractDAO;
import javafx.util.Pair;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                 * todo: REPORT: use implSpec in report
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
         * @param id is taken from the account, and all citizens assigned will be returned
         * */
        @Override
        public Pair<Account, List<Citizen>> read(int id) throws SQLException
        {
            return null;
        }

        /**
         * @param id is taken from the account, and all citizens assigned will be removed (only the reference)
         * */
        @Override
        public void delete(int id) {

        }



        public List<CitizenModel> read(AccountModel id) throws SQLException
        {
            String sql = """                                                                                                             
                                                                                                                                     
                    """;

            Connection conn = DBConnectionPool.getInstance().checkOut();

            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id.getId());

            ResultSet resultSet = ptsm.executeQuery();
            List<CitizenModel> accounts = new ArrayList<>();
            while (resultSet.next())
            {
                accounts.add(new CitizenModel( new Citizen(
                        resultSet.getInt("CID"),
                        new GeneralJournal(), // TODO: 24-05-2022
                        new School(1, "EASV", 6700, "Esbjerg"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getBoolean("isTemplate")
                )));
            }
            return accounts;
        }

        public List<AccountModel> read(CitizenModel id) throws SQLException
        {
            String sql = """                                                                                                             
                    SELECT * FROM [Group]                                                                                            
                    JOIN AccountGroup ON AccountGroup.FK_GroupID = FK_GroupID                                                        
                    JOIN Account ON AccountGroup.FK_MemberID = Account.AID                                                           
                    WHERE [Group].FK_Citizen = ?                                                                                     
                    """;

            Connection conn = DBConnectionPool.getInstance().checkOut();

            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id.getBeCitizen().getID());

            ResultSet resultSet = ptsm.executeQuery();
            List<AccountModel> accounts = new ArrayList<>();
            while (resultSet.next())
            {
                accounts.add(new AccountModel( new Account(
                        resultSet.getInt("AID"),
                        resultSet.getString("username"),
                        resultSet.getString("hashed_pwd"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        new School(1, "EASV", 6700,  "Esbjerg"),
                        resultSet.getByte("accountType") == 0x01,
                        resultSet.getByte("accountType") == 0x10)
                ));
            }
            return accounts;
        }




        public List<Group> readAll() throws SQLException
        {
            List<Group> returnList = new ArrayList<>();

            String sql = """                                                                                                             
                SELECT * FROM AccountGroup                                                                                           
                JOIN Account ON AccountGroup.FK_MemberID = Account.AID                                                               
                JOIN School ON Account.FK_AccountSchool = School.SID                                                                 
                JOIN Zipcode ON School.FK_Zipcode = Zipcode.Zip                                                                      
                """;

            Connection conn = DBConnectionPool.getInstance().checkOut();

            PreparedStatement ptsm = conn.prepareStatement(sql);

            ResultSet resultSet = ptsm.executeQuery();

            while (resultSet.next())
            {


                returnList.add(newGroup);
            }

            return returnList;
        }


    }

