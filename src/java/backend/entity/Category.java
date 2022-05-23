package backend.entity;

import java.util.ArrayList;
import java.util.List;

public class Category
{
    private Integer id = null;

    private String name;

    private List<Category> children = new ArrayList<>(); // list not null

    private Category parent = null;

    public Category(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Category(Category partial, List<Category> children, Category parent)
    {
        this(partial.id, partial.name);

        this.children = new ArrayList<>(children);
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getChildren() {
        return children;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
