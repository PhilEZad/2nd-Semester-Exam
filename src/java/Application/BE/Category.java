package Application.BE;

public class Category {
    private int id;
    private String name;
    private Category parent;
    private CategoryType type;
    private int depth;

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.depth = 0;
        this.initTypeIndicator();
        this.initDepth();
    }

    private void initTypeIndicator() {
        if (this.getParent() != null) {
            this.type = this.getParent().getType();
        }
    }

    private void initDepth() {
        if (this.getParent() != null) {
            this.depth = this.getParent().getDepth() + 1;
        }
        else depth = 0;
    }

    public int getDepth() {
        return this.depth;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType isHealthCondition) {
        this.type = isHealthCondition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
