package Application.BE;

public enum CategoryType {
    HEALTH_CONDITION("Helbredstilstande"), FUNCTIONAL_ABILITY("Funktionsevnetilstande"), GENERAL_INFORMATION("Generelle Oplysninger"), UNKNOWN("Ukendt");

    String name;
    CategoryType(String name) {
        this.name = name;
    }
}
