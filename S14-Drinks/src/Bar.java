import java.util.HashMap;
import java.util.Map;

public abstract class Bar {

    private Map<String, Drink> drinks = new HashMap<>();

    protected abstract Drink makeDrink(String name);

    public abstract String[] getDrinkTypes();

    public Drink orderDrink(String name) {
        Drink drink = drinks.get(name);
        if (drink == null) {
            synchronized (drinks) {
                drink = drinks.get(name);
                if (drink == null) {
                    drink = makeDrink(name);
                    drinks.put(name, drink);
                }
            }
        }
        return drink;
    }
}
