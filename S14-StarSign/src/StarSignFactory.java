import java.util.HashMap;
import java.util.Map;

public abstract class StarSignFactory {

    private static Map<String, StarSign> allInstances = new HashMap<>();

    protected abstract StarSign createStarSign(String name);

    public StarSign getStarSign(String name) {
        StarSign instance = allInstances.get(name);
        if (instance == null) {
            synchronized (allInstances) {
                instance = allInstances.get(name);
                if (instance == null) {
                    instance = createStarSign(name);
                    allInstances.put(name, instance);
                }
            }
        }
        return instance;
    }
}
