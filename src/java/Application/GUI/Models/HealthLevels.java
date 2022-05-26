package Application.GUI.Models;

public enum HealthLevels {
    NOT_RELEVANT(0, "Ikke relevant"),
    POSSIBLE_RELEVANT(1, "Mulig relevans"),
    RELEVANT(2, "Relevant");

    final int level;
    final String description;

    HealthLevels(int level, String description) {
        this.level = level;
        this.description = description;
    }
}
