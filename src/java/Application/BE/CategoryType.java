package Application.BE;

public enum CategoryType {
    HEALTH_CONDITION("Helbredstilstande"), FUNCTIONAL_ABILITY("Funktionsevnetilstande"), UNKNOWN("Ukendt");

    public final String name;
    CategoryType(String name) {
        this.name = name;
    }
}
