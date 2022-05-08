package Application.BE;

public record ContactInfo(String Info) {

    @Override
    public String toString() {
        return Info;
    }
}
