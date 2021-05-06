package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class MediatorFactory {

    private static Map<String, Mediator> allInstances = new HashMap<>();

    protected abstract Mediator createMediator(String mediatorName);

    public Mediator getMediator(String uuid, String mediatorName) throws Exception {
        Mediator instance = allInstances.get(uuid);
        if (instance == null) {
            if (mediatorName == null) throw new Exception("No chat with this uuid has been found.");
            synchronized (allInstances) {
                instance = allInstances.get(uuid);
                if (instance == null) {
                    instance = createMediator(mediatorName);
                    allInstances.put(instance.getUUID(), instance);
                    return instance;
                }
            }
        }
        if (mediatorName != null) throw new Exception("A chat with this uuid already exists.");
        return instance;
    }

    public ArrayList<Mediator> getAllMediators() {
        ArrayList<Mediator> toReturn = new ArrayList<>();
        for (String uuid : allInstances.keySet()) toReturn.add(allInstances.get(uuid));
        return toReturn;
    }
}
