package Application.GUI.Models;

import javafx.scene.image.Image;

import java.util.Arrays;

/**
 * @author Rasmus Sandbæk
 * @author Mads Mandahl-Barth
 * */
public enum FunctionalLevels {

    LEVEL_0(0, new Image(FunctionalLevels.class.getResource("/img/func0.png").toExternalForm())),
    LEVEL_1(1, new Image(FunctionalLevels.class.getResource("/img/func1.png").toExternalForm())),
    LEVEL_2(2, new Image(FunctionalLevels.class.getResource("/img/func2.png").toExternalForm())),
    LEVEL_3(3, new Image(FunctionalLevels.class.getResource("/img/func3.png").toExternalForm())),
    LEVEL_4(4, new Image(FunctionalLevels.class.getResource("/img/func4.png").toExternalForm())),
    LEVEL_9(9, new Image(FunctionalLevels.class.getResource("/img/func9.png").toExternalForm()));

    public final Image image;
    public final int level;

    FunctionalLevels(int level, Image image) {
        this.level = level;
        this.image = image;
    }
    public static FunctionalLevels getByInt(int level) {
        return Arrays.stream(FunctionalLevels.values()).filter(functionalLevels -> functionalLevels.level == level).findFirst().orElse(LEVEL_9);
    }

}
