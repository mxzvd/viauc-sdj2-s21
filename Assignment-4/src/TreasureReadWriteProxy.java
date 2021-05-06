import utility.collection.ArrayList;

public class TreasureReadWriteProxy implements TreasureReadWrite {

    private TreasureReadWrite realSubject;
    private boolean hasAccess;

    public TreasureReadWriteProxy(TreasureReadWrite realSubject) {
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

    @Override
    public void add(Valuable v) {
        if (!hasAccess) throw new IllegalStateException("Write access denied.");
        realSubject.add(v);
    }

    @Override
    public Valuable retrieve() {
        if (!hasAccess) throw new IllegalStateException("Write access denied.");
        return realSubject.retrieve();
    }
}
