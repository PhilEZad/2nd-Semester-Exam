package Application.DAL.TemplateMethod;

import Application.BE.Citizen;
import Application.BE.School;
import Application.DAL.TemplateMethod.AbstractDAO_TP;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO
{

    public final Citizen create(Citizen citizen) {
        var action = new AbstractDAO_TP<Citizen, Citizen>() {
            @Override
            protected Citizen execute(PreparedStatement statement, Citizen input) throws SQLException {

                statement.setString(1, input.getFirstname());
                statement.setString(2, input.getLastname());
                statement.setInt(3, input.getAge());
                statement.setInt(7, input.getOwner().getSchoolID());

                statement.executeUpdate();

                return input;
            }

            @Override
            protected String getSQLStatement()
            {
                return "INSERT INTO citizen (firstName, lastName, age, FK_SchoolOwner, FK_Info) VALUES (?, ?, ?, ?, ?)";
            }
        };

        action.run(citizen);

        var updated = action.getResult();
        updated.setId((int) action.getResultID());

        return updated;
    }

    public final List<Citizen> readAll(int schoolID)
    {
       var action = new AbstractDAO_TP<List<Citizen>, Integer>() {
            @Override
            protected List<Citizen> execute(PreparedStatement statement, Integer input) throws SQLException {
                statement.setInt(1, input);

                List<Citizen> array = new ArrayList<>();

                var result = statement.executeQuery();

                while (result.next())
                {
                    Citizen citizen = new Citizen(
                            result.getInt("CID"),
                            result.getString("firstName"),
                            result.getString("lastName"),
                            result.getInt("age"),
                            result.getInt("FK_SchoolOwner")
                    );

                    array.add(citizen);
                }

                return array;
            }


            @Override
            protected @NotNull String getSQLStatement() {
                return "SELECT * FROM Citizen where FK_SchoolOwner = ?";
            }
       };

       action.run(schoolID);

       return action.getResult();
    }

}
