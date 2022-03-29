package Circuit;

public class Circuit {
    private int circuitId;
    private String circuitName;
    private String country;

    public Circuit(int circuitId, String circuitName, String country) {
        this.circuitId = circuitId;
        this.circuitName = circuitName;
        this.country = country;
    }

    public int getCircuitId() {
        return circuitId;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public String getCountry() {
        return country;
    }
}
