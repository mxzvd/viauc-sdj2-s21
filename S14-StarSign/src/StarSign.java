public abstract class StarSign {

    private String name;

    public StarSign(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getDescription();

    @Override
    public String toString() {
        return "{name: " + name + ", description: " + getDescription() + "}";
    }
}
