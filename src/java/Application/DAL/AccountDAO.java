package Application.DAL;

import Application.BE.Account;
import Application.BE.School;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends TemplatePatternDAO<Account> {

    /**
     * Creates an account from account entity given.
     * @param input
     * @return
     */
    @Override
    public Boolean create(Account input) {
        String sql = """
                    INSERT INTO accounts (login, password, firstName, surname, email, school, auth) 
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, input.getLogin());
            pstmt.setString(2, input.getPassword());
            pstmt.setString(3, input.getFirstName());
            pstmt.setString(4, input.getLastName());
            pstmt.setString(5, input.getEmail());
            pstmt.setInt(6, input.getSchool().getSchoolID());
            pstmt.setInt(7, input.getAuthorization());

            pstmt.executeUpdate();


            pstmt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }


    /**
     * Deletes a specific account based on account entity given.
     * @param accountid
     */
    @Override
    public void delete(int accountid){
        String sql = """
                    DELETE FROM account
                    WHERE id = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountid);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    /**
     * Returns a specific account based on account entity id given.
     * @param accountID
     * @return
     */
    @Override
    public Account read(int accountID){
        String sql = """
                    SELECT * FROM accounts
                    JOIN schools ON accounts.school = schools.schoolId
                    JOIN zipCode ON schools.schoolZipCode = zipCode.zipCode
                    WHERE accountId = ?
                    """;

        School school = null;
        Account account = null;

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                school = new School(
                        rs.getInt("schoolId"),
                        rs.getString("schoolName"),
                        rs.getInt("schoolZipCode"),
                        rs.getString("cityName")
                );

                account = new Account(
                        rs.getInt("accountId"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        school,
                        rs.getInt("auth")
                );
            }
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }

        return account;
    }

    public Account read(String accountName)
    {
        Account account = null;
        return null;
    }

    /**
     * Returns a list of all accounts.
     * @return
     */
    @Override
    public List<Account> readAll() {
        String sql = """
                    SELECT * FROM accounts
                    JOIN schools ON accounts.school = schools.schoolId
                    JOIN zipCodes ON schools.schoolZipCode = zipCodes.zipCode
                    """;
        List<Account> studentsList = new ArrayList<>();

        Connection conn = DBConnectionPool.getInstance().checkOut();
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                School school = new School(
                        rs.getInt("schoolID"),
                        rs.getString("schoolName"),
                        rs.getInt("zipCode"),
                        rs.getString("cityName")
                );

                Account student = new Account(
                        rs.getInt("accountId"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("studentFirstName"),
                        rs.getString("studentSurname"),
                        rs.getString("email"),
                        school,
                        rs.getInt("auth"));

                studentsList.add(student);
            }

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }

        return studentsList;
    }

    /**
     * Updates accounts using an account entity.
     * @param input
     */
    @Override
    public void update(Account input) {
        String sql = """
                     UPDATE accounts 
                     SET firstName = ?, surname = ?, email = ?
                     WHERE accountId = ?
                     """;

        Account account = input;
        Connection conn = DBConnectionPool.getInstance().checkOut();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, account.getFirstName());
            pstmt.setString(2, account.getLastName());
            pstmt.setString(3, account.getEmail());
            pstmt.setInt(4, account.getId());

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    public void changePassword(String password, Account account)
    {
        String sqlPassUpdate = """
                UPDATE acounts
                SET password = ?
                WHERE accountId = ?
                """;

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try { PreparedStatement pscp = conn.prepareStatement(sqlPassUpdate);
            pscp.setString(1,password);
            pscp.setInt(2,account.getId());

            pscp.executeUpdate();
            pscp.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
