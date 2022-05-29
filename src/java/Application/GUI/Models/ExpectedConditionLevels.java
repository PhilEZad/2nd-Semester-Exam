package Application.GUI.Models;

public enum ExpectedConditionLevels {

    DISAPPEARS(0, "Forsvinder"),
    DECREASES(1, "Mindskes"),
    REMAINS_UNCHANGED(2, "Forbliver Uændret");

    int level;
    String description;

    ExpectedConditionLevels(int level, String description) {
        this.level = level;
        this.description = description;
    }
}
