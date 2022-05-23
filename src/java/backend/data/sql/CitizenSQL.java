package backend.data.sql;

import backend.data.crud.IDataAccessObject;
import backend.entity.Citizen;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenSQL implements IDataAccessObject<Citizen>
{
    @Override
    public List<Citizen> getAll() throws Exception {
        return new AbstractSQL<List<Citizen>, Void>()
        {
            @Override
            protected List<Citizen> execute(PreparedStatement statement, Void input) throws Exception
            {
                List<Citizen> returnList = new ArrayList<>();
                ResultSet result = statement.executeQuery();

                while (result.next()) {

                    int SID = result.getInt("FK_SchoolOwner");
                    int CID = result.getInt("CID");

                    var school = new SchoolSQL().getByID(SID);

                    returnList.add(new Citizen(CID, result.getString("firstName"), result.getString("lastName"), result.getInt("age"), school));
                }

                statement.close();

                return returnList;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM Citizen
                        JOIN School ON School.SID = Citizen.FK_SchoolOwner
                        """;
            }
        }.getResult();
    }

    @Override
    public Citizen getByID(int id) throws Exception {
        return new AbstractSQL<Citizen, Integer>() {

            @Override
            protected Citizen execute(PreparedStatement statement, Integer input) throws Exception {
                statement.setInt(1, input);
                ResultSet result = statement.executeQuery();
                result.next();

                int SID = result.getInt("FK_SchoolOwner");
                int CID = result.getInt("CID");

                var school = new SchoolSQL().getByID(SID);

                return new Citizen(CID, result.getString("firstName"), result.getString("lastName"), result.getInt("age"), school);
            }

            @Override
            protected String getSQLStatement() {
                return """ 
                        SELECT * FROM Citizen
                        JOIN School ON School.SID = Citizen.FK_SchoolOwner
                        WHERE CID = ?
                        """;
            }
        }.getResult(id);
    }

    @Override
    public Citizen create(Citizen obj) throws Exception {
        return new AbstractSQL<Citizen, Citizen>() {
            // TODO: 22-05-2022  General Journal ????
            @Override
            protected Citizen execute(PreparedStatement statement, Citizen input) throws SQLException {
                statement.setInt(1, input.getJournal().getId());
                statement.setInt(2, input.getOwner().getId());
                statement.setString(3, input.getFirstname());
                statement.setString(4, input.getLastname());
                statement.setInt(5, input.getAge());

                statement.executeUpdate();

                int id = -1;

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                statement.close();

                return new Citizen(id, input.getJournal(), input.getOwner(), input.getFirstname(), input.getLastname(), input.getAge());
            }

            @Override
            protected String getSQLStatement() {
                return """  
                       INSERT INTO Citizen (FK_Info, FK_SchoolOwner, firstName, lastName, age) 
                       VALUES (?, ?, ?, ?, ?)
                       """;
            }
        }.getResult(obj);
    }

    @Override
    public void remove(Citizen obj) throws Exception {
        this.removeByID(obj.getId());
    }

    @Override
    public void removeByID(int id) throws Exception {
        new AbstractSQL<Void, Integer>()
        {
            @Override
            protected Void execute(PreparedStatement statement, Integer input) throws SQLException {

                statement.setInt(1, id);
                statement.execute();

                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """ 
                        DELETE FROM Citizen
                        WHERE CID = ?
                        """;
            }
        }.getResult(id);
    }

    @Override
    public void update(Citizen obj) throws Exception {
        this.updateByID(obj.getId(), obj);
    }

    @Override
    public void updateByID(int id, Citizen obj) throws Exception {
        new AbstractSQL<Void, Citizen>() {
            @Override
            protected Void execute(PreparedStatement statement, Citizen input) throws SQLException {
                statement.setInt(1, input.getJournal().getId());
                statement.setInt(2, input.getOwner().getId());
                statement.setString(3, input.getFirstname());
                statement.setString(4, input.getLastname());
                statement.setInt(5, input.getAge());
                statement.setInt(6, input.getId());

                statement.executeUpdate();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return  """ 
                        UPDATE Citizen
                        SET FK_Info = ?, FK_SchoolOwner = ?, firstName = ?, lastName = ?, age = ?
                        WHERE CID = ?
                        """;
            }
        }.getResult(new Citizen(obj, id));
    }

    @Override
    public Citizen getByString(String str) {
        // TODO: 22-05-2022  ??
        return null;
    }
}
