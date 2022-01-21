package enums;

public enum Gender {
    MALE("Male", "M"),
    FEMALE("Female", "F"),
    MIXED("Mixed", "Mxd"),
    OTHER("Other", "O");

    private final String text;
    private final String shortened;

    private Gender(String text, String shortened) {
        this.text = text;
        this.shortened = shortened;
    }

    @Override
    public String toString() {
        return this.text;
    }

    public String toStringShortened() {
        return this.shortened;
    }
}
