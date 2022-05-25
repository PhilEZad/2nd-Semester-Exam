package Application.DAL;

import Application.BE.Category;
import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements IDatabaseActions<Category> {
    @Override
    public Category create(Category input) {
        System.err.println("illegal");
        return null;
    }

    @Override
    public void update(Category input) {
        System.err.println("illegal");
    }

    @Override
    public Category read(int id) {

        String sql = """
                        SELECT CHILD.CatID, CHILD.catName, PARENT.CatID AS ParentID, PARENT.catName AS parentName 
                        FROM dbo.Categories PARENT, dbo.Categories CHILD 
                        WHERE PARENT.CatID = CHILD.FK_ParentCat AND CHILD.CatID = ?
                        UNION SELECT NULLABLE.CatID, NULLABLE.catName, NULL, NULL
                        FROM dbo.Categories AS NULLABLE 
                        WHERE NULLABLE.FK_ParentCat IS NULL AND NULLABLE.CatID = ?
                    """;

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psas = conn.prepareStatement(sql);

            psas.setInt(1, id);
            psas.setInt(2, id);

            ResultSet rs = psas.executeQuery();
            rs.next();

            return new Category(rs.getInt("CatID"), rs.getString("catName"), rs.getInt("ParentID"));

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public List<Category> readAll() {
        List<Category> categories = new ArrayList<>();

        String sql = """
                        SELECT CHILD.CatID, CHILD.catName, PARENT.CatID AS ParentID, PARENT.catName AS parentName 
                        FROM dbo.Categories PARENT, dbo.Categories CHILD 
                        WHERE PARENT.CatID = CHILD.FK_ParentCat 
                        UNION SELECT NULLABLE.CatID, NULLABLE.catName, NULL, NULL
                        FROM dbo.Categories AS NULLABLE WHERE NULLABLE.FK_ParentCat IS NULL
                     """;

        Connection conn = DBConnectionPool.getInstance().checkOut();

        try {
            PreparedStatement psas = conn.prepareStatement(sql);

            ResultSet rs = psas.executeQuery();

            while (rs.next())
            {
                categories.add(new Category(rs.getInt("CatID"), rs.getString("catName"), rs.getInt("ParentID")));
            }

            return categories;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally {
            DBConnectionPool.getInstance().checkIn(conn);
        }
    }

    @Override
    public void delete(int id) {
        System.err.println("illegal");
    }

    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();

        System.out.println(dao.readAll());
        System.out.println(dao.read(5));
    }
}
