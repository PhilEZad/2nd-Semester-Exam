package backend.data.sql;

import backend.data.crud.IDataAccessObject;
import backend.entity.Category;
import backend.entity.Entry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JournalEntrySQL implements IDataAccessObject<Entry>
{
    @Override
    public Entry create(Entry obj) throws Exception {
        return new AbstractSQL<Entry, Entry>() {
            @Override
            protected Entry execute(PreparedStatement statement, Entry input) throws SQLException
            {
                statement.setInt(1, input.getCategory().getId());
                statement.setString(2, input.getAssessment());
                statement.setString(3, input.getCause());
                statement.setString(4, input.getImplications());
                statement.setInt(5, input.getCurrentStatus());
                statement.setInt(6, input.getExpectedStatus());
                statement.setString(7, input.getCitizenGoals());
                statement.setString(8, input.getNote());
                statement.setInt(9, input.getSeverity());

                statement.execute();

                int id = -1;

                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                }

                input.setId(id);
                statement.close();
                return input;
            }

            @Override
            protected String getSQLStatement() {
                return """ 
                        INSERT INTO JournalEntry (FK_Category, assessment, cause, implications, currentStatus, expectedStatus, citizenGoals, notes, severity) 
                        VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?)
                        """;
            }
        }.getResult(obj);
    }

    @Override
    public void remove(Entry obj) throws Exception {
        this.removeByID(obj.getId());
    }

    @Override
    public void removeByID(int id) throws Exception {
        new AbstractSQL<Void, Integer>() {
            @Override
            protected Void execute(PreparedStatement statement, Integer input) throws SQLException
            {
                statement.setInt(1, input);
                statement.execute();
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return "DELETE FROM JournalEntry WHERE EID = ?";
            }
        }.getResult(id);
    }

    @Override
    public List<Entry> getAll() throws Exception {
        return new AbstractSQL<List<Entry>, Void>() {
            @Override
            protected List<Entry> execute(PreparedStatement statement, Void input) throws SQLException {
                return null;
            }

            @Override
            protected String getSQLStatement() {
                return null;
            }
        }.getResult();
    }

    @Override
    public Entry getByID(int id) throws Exception {
        return new AbstractSQL<Entry, Integer>() {
            @Override
            protected Entry execute(PreparedStatement statement, Integer input) throws SQLException
            {
                statement.setInt(1, input);
                statement.execute();

                var rs = statement.getResultSet();
                rs.next();

                var category = new Category(rs.getInt("CatID"), rs.getString("catName"));
                return new Entry(input, category,
                        rs.getString("assessment"),
                        rs.getString("cause"),
                        rs.getString("implications"),
                        rs.getString("citizenGoals"),
                        rs.getString("notes"),
                        rs.getInt("currentStatus"),
                        rs.getInt("expectedStatus"),
                        rs.getInt("severity"));
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT * FROM JournalEntry
                        JOIN Categories ON Categories.CatID = FK_Category
                        WHERE EID = ?
                        """;
            }
        }.getResult(id);
    }

    @Override
    public Entry getByString(String str) {
        // TODO: 22-05-2022 ???
        return null;
    }

    @Override
    public void update(Entry obj) throws Exception {
        new AbstractSQL<Void, Entry>() {
            @Override
            protected Void execute(PreparedStatement statement, Entry input) throws SQLException {

                statement.setString(1, input.getAssessment());
                statement.setString(2, input.getCause());
                statement.setString(3, input.getImplications());
                statement.setInt(4, input.getCurrentStatus());
                statement.setInt(5, input.getExpectedStatus());
                statement.setString(6, input.getCitizenGoals());
                statement.setString(7, input.getNote());
                statement.setInt(8, input.getSeverity());
                statement.setInt(9, input.getId());

                statement.execute();

                return null;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE JournalEntry SET
                            assessment = ?, cause = ?,
                            implications = ?, currentStatus = ?,
                            expectedStatus = ?, citizenGoals = ?,
                            notes = ?, severity = ?
                        WHERE EID = ?
                        """;
            }
        }.getResult(obj);
    }

    @Override
    public void updateByID(int id, Entry obj) throws Exception {
        this.update(new Entry(obj, id));
    }
}
