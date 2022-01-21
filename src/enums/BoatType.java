package enums;

public enum BoatType {
    SINGLE_SCULL("1x", 1, false),
    DOUBLE_SCULL("2x", 2, false),
    PAIR_SWEEP("2-", 2, false),
    QUAD_SCULL_COXED("4x+", 4, true),
    QUAD_SCULL_COXLESS("4x-", 4, false),
    FOUR_SWEEP_COXED("4+", 4, true),
    FOUR_SWEEP_COXLESS("4-", 4, false),
    OCTUPLE_SCULL("8x", 8, true),
    EIGHT_SWEEP("8+", 8, true);

    private final String boatShortened;
    private final int maxCrewSize;
    private final Boolean isCoxed;

    private BoatType(String boatShortened, int maxCrewSize, Boolean isCoxed) {
        this.boatShortened = boatShortened;
        this.maxCrewSize = maxCrewSize;
        this.isCoxed = isCoxed;
    }

    @Override
    public String toString() {
        return this.boatShortened;
    }

    // Getters

    public String getBoatShortened() {
        return boatShortened;
    }

    public int getMaxCrewSize() {
        return maxCrewSize;
    }

    public Boolean getCoxed() {
        return isCoxed;
    }
}
