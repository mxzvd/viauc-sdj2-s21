package temperature.model;

public class Temperature {

    private String id;
    private double value;
    private DateTime time;

    public Temperature(String id, double value) {
        this.id = id;
        this.value = value;
        time = new DateTime();
    }

    public double getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public DateTime getTime() {
        return time;
    }

    public String toString() {
        return String.format("%s: %.1f (%s)", id, value, time.getTimestamp());
    }
}
