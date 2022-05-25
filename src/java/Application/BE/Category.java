package Application.BE;

import java.util.ArrayList;
import java.util.List;

public class Category implements IUniqueIdentifier<Integer> {

    private Integer id = null;

    private String name;

    private List<Category> children = new ArrayList<>();

    private int parentID;

    private Category parent;

    private CategoryType type;


    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name, int parentID) {
        this.id = id;
        this.name = name;
        this.parentID = parentID;
        initType();
    }

    private void initType()
    {
        if (this.parent != null)
        {
            this.type = this.parent.getType();
        }
        else switch (this.name)
        {
            case "Helbredstilstande" -> this.type = CategoryType.HEALTH_CONDITION;
            case "Funktionsevnetilstande" -> this.type = CategoryType.FUNCTIONAL_ABILITY;
            default -> this.type = CategoryType.UNKNOWN;
        }
    }


    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType isHealthCondition) {
        this.type = isHealthCondition;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {
        this.id = id == null ? -1 : id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }

    public int getParentID() {
        return parentID;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
        initType();
    }

    public List<Category> getChildren() {
        return children;
    }

    public void addChild(Category child){
        this.children.add(child);
    }

    public void removeChild(Category child){
        this.children.remove(child);
    }

}
