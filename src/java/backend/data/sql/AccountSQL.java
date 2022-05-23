package backend.data.sql;

import backend.data.crud.IDataAccessObject;
import util.AccountType;
import backend.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountSQL implements IDataAccessObject<User>
{
    @Override
    public List<User> getAll() {
        return new AbstractSQL<List<User>, Void>() {

            @Override
            protected List<User> execute(PreparedStatement statement, Void input) throws SQLException {
                List<User> accounts = new ArrayList<>();
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    var partial = new User(rs.getString("username"),
                            rs.getString("hashed_pwd"),
                            rs.getString("firstName"),
                            rs.getString("lastname"),
                            rs.getString("email"),
                            AccountType.getByID(rs.getInt("privilegeLevel")));

                    accounts.add(new User(partial, rs.getInt("accountId")));
                }

                return accounts;
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM Account";
            }
        }.getResult();
    }

    @Override
    public User getByID(int id)
    {
       return new AbstractSQL<User, Integer>() {

           @Override
           protected User execute(PreparedStatement statement, Integer input) throws SQLException {
               statement.setInt(1, input);
               ResultSet rs = statement.executeQuery();
               rs.next();

               var partial = new User(rs.getString("username"),
                       rs.getString("hashed_pwd"),
                       rs.getString("firstName"),
                       rs.getString("lastname"),
                       rs.getString("email"),
                       AccountType.getByID(rs.getInt("privilegeLevel")));
               return new User(partial, rs.getInt("accountId"));
           }

           @Override
           protected String getSQLStatement() {
               return "SELECT * FROM Account WHERE AID = ?";
           }
       }.getResult(id);
    }

    @Override
    public User create(User obj) {
        return new AbstractSQL<User, User>(){
            @Override
            protected User execute(PreparedStatement statement, User input) throws SQLException {

                statement.setString(1, input.getUsername());
                statement.setString(2, input.getPassword());
                statement.setString(3, input.getFirstName());
                statement.setString(4, input.getLastName());
                statement.setString(5, input.getEmail());
                statement.setInt(6, input.getAuthorization().getId());

                statement.executeUpdate();

                int id = -1;

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                statement.close();

                var partial = new User(input.getUsername(),
                        input.getPassword(),
                        input.getFirstName(),
                        input.getLastName(),
                        input.getEmail(),
                        input.getAuthorization());
                return new User(partial, id);
            }

            @Override
            protected String getSQLStatement() {
                return "INSERT INTO Account (username, hashed_pwd, firstName, lastname, email, privilegeLevel) VALUES (?, ?, ?, ?, ?, ?)";
            }
        }.getResult(obj);
    }

    @Override
    public void remove(User obj) {
        this.removeByID(obj.getId());
    }

    @Override
    public void removeByID(int id) {
        new AbstractSQL<Void, Integer>() {
            @Override
            protected Void execute(PreparedStatement statement, Integer input) throws SQLException {
                statement.setInt(1, input);
                statement.executeUpdate();
                statement.close();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "DELETE FROM account WHERE AID = ?";
            }
        }.getResult(id);
    }

    @Override
    public void update(User obj) {
        this.updateByID(obj.getId(), obj);
    }

    @Override
    public void updateByID(int id, User obj) {
        new AbstractSQL<Void, User>()
        {
            @Override
            protected Void execute(PreparedStatement statement, User input) throws SQLException {
                statement.setString(1, input.getFirstName());
                statement.setString(2, input.getLastName());
                statement.setString(3, input.getEmail());
                statement.setInt(4, input.getId());

                statement.executeUpdate();

                statement.close();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "UPDATE Account SET firstName = ?, lastname = ?, email = ? WHERE AID = ?";
            }
        }.getResult(new User(obj, id));
    }

    @Override
    public User getByString(String username) {
        return new AbstractSQL<User, String>() {

            @Override
            protected User execute(PreparedStatement statement, String input) throws SQLException {
                statement.setString(1, input);
                ResultSet rs = statement.executeQuery();
                rs.next();

                var partial = new User(rs.getString("username"),
                        rs.getString("hashed_pwd"),
                        rs.getString("firstName"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        AccountType.getByID(rs.getInt("privilegeLevel")));

                return new User(partial, rs.getInt("AID"));
            }

            @Override
            protected String getSQLStatement() {
                return "SELECT * FROM Account WHERE Account.username = ?";
            }
        }.getResult(username);
    }
}
