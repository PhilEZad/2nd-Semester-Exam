package Application.GUI.Models;

import java.util.Arrays;

public enum HealthLevels {
    NOT_RELEVANT(0, "Ikke relevant"),
    POSSIBLE_RELEVANT(1, "Mulig relevans"),
    RELEVANT(2, "Relevant");

    int level;
    String description;

    HealthLevels(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public static HealthLevels getByInt(int level) {
        return Arrays.stream(HealthLevels.values()).filter(healthLevels -> healthLevels.level == level).findFirst().orElse(NOT_RELEVANT);
    }
}
