import java.util.HashMap;
import java.util.Map;

public class Valuable {

    public enum Item {

        Diamond(150),
        GoldBar(90),
        Ruby(70),
        Jewel(50),
        GoldNugget(20),
        WoodenCoin(1);

        private final long value;

        Item(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    private static Map<Item, Valuable> allInstances = new HashMap<>();
    private Item item;

    private Valuable(Item item) {
        this.item = item;
    }

    public static Valuable getInstance(Item item) {
        Valuable instance = allInstances.get(item);
        if (instance == null) {
            synchronized (allInstances) {
                if (allInstances.get(item) == null) {
                    instance = new Valuable(item);
                    allInstances.put(item, instance);
                }
            }
        }
        return instance;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
