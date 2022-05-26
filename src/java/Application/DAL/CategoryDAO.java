package Application.DAL;

import Application.BE.Category;
import Application.DAL.TemplateMethod.AbstractDAO;
import Application.DAL.TemplateMethod.IDatabaseActions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements IDatabaseActions<Category>
{
    @Override
    public Category create(Category input)
    {
        var dao = new AbstractDAO<Category>()
        {
            @Override
            protected Category execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, input.getName(), input.getParentID(), null, null);
                statement.execute();
                return input;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        INSERT INTO Categories (categoryName, FK_ParentCat, FK_Description, FK_Guide) 
                        VALUES (?, ?, ?, ?)
                        """;
            }
        };

        dao.start();

        if (dao.isSuccessful())
            input.setID(dao.getResult().getKey());

        return input;
    }

    @Override
    public void update(Category input)
    {
        var dao = new AbstractDAO<Category>()
        {
            @Override
            protected Category execute(PreparedStatement statement) throws SQLException
            {
                // TODO: 25-05-2022 missing foreign keys
                AbstractDAO.setPlaceholders(statement, input.getName(), input.getParentID(), null, null, input.getID());
                statement.execute();
                return input;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        UPDATE Categories SET categoryName = ?, FK_ParentCat = ?, FK_Description = ?, FK_Guide = ?
                        WHERE CatID = ?
                        """;
            }
        };

        dao.start();
    }

    @Override
    public Category read(int id)
    {
        var dao = new AbstractDAO<Category>()
        {
            @Override
            protected Category execute(PreparedStatement statement) throws SQLException
            {
                AbstractDAO.setPlaceholders(statement, id, id);
                ResultSet rs = statement.executeQuery();

                rs.next();

                return new Category(rs.getInt("CatID"), rs.getString("catName"), rs.getInt("ParentID"));
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT CHILD.CatID, CHILD.categoryName, PARENT.CatID AS ParentID, PARENT.categoryName AS parentName 
                        FROM dbo.Categories PARENT, dbo.Categories CHILD 
                        WHERE PARENT.CatID = CHILD.FK_ParentCat AND CHILD.CatID = ?
                        UNION SELECT NULLABLE.CatID, NULLABLE.categoryName, NULL, NULL
                        FROM dbo.Categories AS NULLABLE 
                        WHERE NULLABLE.CatID = ?
                        """;
            }
        };

        dao.start();

        return dao.getResult().getValue();
    }

    @Override
    public List<Category> readAll()
    {
        var dao = new AbstractDAO<List<Category>>()
        {
            @Override
            protected List<Category> execute(PreparedStatement statement) throws SQLException
            {
                ResultSet rs = statement.executeQuery();

                var categories = new ArrayList<Category>();

                while (rs.next())
                {
                    categories.add(new Category(rs.getInt("CatID"), rs.getString("catName"), rs.getInt("ParentID")));
                }

                return categories;
            }

            @Override
            protected String getSQLStatement() {
                return """
                        SELECT CHILD.CatID, CHILD.categoryName, PARENT.CatID AS ParentID, PARENT.categoryName AS parentName 
                        FROM dbo.Categories PARENT, dbo.Categories CHILD 
                        WHERE PARENT.CatID = CHILD.FK_ParentCat
                        """;
            }
        };

        dao.start();

        return dao.getResult().getValue();
    }

    @Override
    public void delete(int id) {
        // TODO: 25-05-2022 ???
        System.err.println("illegal");
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();

        System.out.println(dao.readAll());
        System.out.println(dao.read(5));
    }
}
