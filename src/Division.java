public enum Division {
    U14("Under 14", "U14"),
    U16("Under 16", "U16"),
    NOVICE("Novice", "Nov"),
    JUNIOR("Junior", "Jnr"),
    SENIOR("Senior", "Snr"),
    MASTER("Master", "Mst"),
    OPEN("Open", "Opn");

    private final String text;
    private final String shortened;

    private Division(String text, String shortened) {
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