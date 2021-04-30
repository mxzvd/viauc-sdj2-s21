public class Cookie {

    private String type;

    public Cookie(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Cookie: " + type;
    }
}