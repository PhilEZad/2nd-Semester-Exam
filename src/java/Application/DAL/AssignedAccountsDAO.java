package Application.DAL;

import Application.BE.Account;
import Application.BE.Citizen;
import javafx.util.Pair;

import java.sql.SQLException;
import java.util.List;

public class AssignedAccountsDAO implements IDatabaseActions<Pair<Citizen, List<Account>>>
{

        @Override
        public Pair<Citizen, List<Account>> create(Pair<Citizen, List<Account>> input)
        {
            return null;
        }

        @Override
        public void update(Pair<Citizen, List<Account>> input)
        {

        }

        /**
         * @param id is taken from the account, and all citizens assigned will be returned
         * */
        @Override
        public Pair<Citizen, List<Account>> read(int id) throws SQLException
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

