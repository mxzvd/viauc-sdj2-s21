public class Mine {
    public static Valuable discoverValuable() {
        return Valuable.getInstance(Valuable.Item.values()[(int) (Math.random() * Valuable.Item.values().length - 1)]);
    }
}
