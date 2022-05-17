package Application.DAL;

import Application.BE.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends TemplatePatternDAO<Category> {
    @Override
    public Category create(Category input) {
        return null;
    }

    @Override
    public void update(Category input) {

    }

    @Override
    public Category read(int id) {
        return null;
    }

    @Override
    public List<Category> readAll() {
        List<Category> categories = new ArrayList<>();

        return categories;
    }

    @Override
    public void delete(int id) {

    }
}
