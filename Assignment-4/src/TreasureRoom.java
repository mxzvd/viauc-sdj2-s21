import utility.collection.ArrayList;
import utility.collection.ListADT;

public class TreasureRoom implements TreasureReadWrite {

    private ListADT<Valuable> treasure;

    public TreasureRoom() {
        treasure = new ArrayList<>();
    }

    public ArrayList<Valuable> look() {
        ArrayList<Valuable> toReturn = new ArrayList<>();
        for (int i = 0; i < treasure.size(); i++) toReturn.add(treasure.get(i));
        return toReturn;
    }

    @Override
    public void add(Valuable v) {
        treasure.add(v);
    }

    @Override
    public Valuable retrieve() {
        return treasure.remove(0);
    }
}
