public class Wine extends Drink {

    public static final String RED = "red";
    public static final String WHITE = "white";
    public static final String ROSE = "rose";
    private String type;

    public Wine(String name, String type) {
        super(name, type.equals(RED) ? "In glass, slightly chilled" : "In glass, cold”");
        this.type = type;
    }
}
