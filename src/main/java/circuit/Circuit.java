package circuit;

public class Circuit {
    private final String circuitId;
    private final String circuitName;
    private final String country;

    public Circuit(String circuitId, String circuitName, String country) {
        this.circuitId = circuitId;
        this.circuitName = circuitName;
        this.country = country;
    }

    public String getCircuitId() {
        return circuitId;
    }

    public String getCircuitName() {
        return circuitName;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Circuit{" +
                "circuitId='" + circuitId + '\'' +
                ", circuitName='" + circuitName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
