package backend.data.sql;

import backend.data.crud.IDataAccessObject;
import backend.entity.Category;

import java.util.List;

public class CategorySQL implements IDataAccessObject<Category>
{

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Category getByID(int GroupID) {
        return null;
    }

    @Override
    public Category getByString(String catName) {
        return null;
    }


    /*
    * Categories are constants defined in Database
    * */

    @Override
    public Category create(Category obj) {
        System.err.println("illegal op");
        return null;
    }

    @Override
    public void remove(Category obj) {
        // TODO: 22-05-2022
        System.err.println("illegal op");
    }

    @Override
    public void removeByID(int id) {
        // TODO: 22-05-2022
        System.err.println("illegal op");
    }

    @Override
    public void update(Category obj) {
        // TODO: 22-05-2022
        System.err.println("illegal op");
    }

    @Override
    public void updateByID(int id, Category obj) {
        // TODO: 22-05-2022
        System.err.println("illegal op");
    }
}
