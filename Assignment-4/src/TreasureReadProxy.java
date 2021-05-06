import utility.collection.ArrayList;

public class TreasureReadProxy implements TreasureRead {

    private TreasureRead realSubject;
    private boolean hasAccess;

    public TreasureReadProxy(TreasureRead realSubject) {
        this.realSubject = realSubject;
        hasAccess = true;
    }

    public void restrictAccess() {
        hasAccess = false;
    }

    @Override
    public ArrayList<Valuable> look() {
        if (!hasAccess) throw new IllegalStateException("Read access denied.");
        return realSubject.look();
    }
}
