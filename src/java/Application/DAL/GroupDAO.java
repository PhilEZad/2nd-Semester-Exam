package Application.DAL;

import Application.BE.Citizen;
import Application.BE.Group;
import Application.BE.*;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends TemplatePatternDAO<Group>
{

    @Override
    public Group create(Group input)
    {
        String sql = """
                
                """;

        return null;
    }

    @Override
    public void update(Group input)
    {

    }

    @Override
    public Group read(int id) throws SQLException
    {
        return null;
    }

    @Override
    public List<Group> readAll()
    {
        String sql 
    }

    @Override
    public void delete(int id)
    {

    }
}
