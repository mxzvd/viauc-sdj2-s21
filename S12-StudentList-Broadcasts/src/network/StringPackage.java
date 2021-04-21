package network;

public class StringPackage extends NetworkPackage {

    private String value;

    public StringPackage(NetworkType type, String value) {
        super(type);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return getType() + ": " + value;
    }
}
