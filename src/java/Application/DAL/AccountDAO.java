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
    public Account create(Account input) {
        String sql = """
                    INSERT INTO accounts (login, password, firstName, surname, email, school, auth) 
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, input.getLogin());
            pstmt.setString(2, input.getPassword());
            pstmt.setString(3, input.getFirstName());
            pstmt.setString(4, input.getLastName());
            pstmt.setString(5, input.getEmail());
            pstmt.setInt(6, input.getSchool().getSchoolID());
            pstmt.setInt(7, input.getAuthorization());

            pstmt.executeUpdate();

            int id = -1;

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

            pstmt.close();
            return new Account(
                    id,
                    input.getLogin(),
                    input.getPassword(),
                    input.getFirstName(),
                    input.getLastName(),
                    input.getEmail(),
                    input.getSchool(),
                    input.getAuthorization()
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, accountid);

            pstmt.executeUpdate();

            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
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

        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();

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

        try
        {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
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
        try {
            Connection conn = DBConnectionPool.getInstance().checkOut().getConnection();
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
    }
}
