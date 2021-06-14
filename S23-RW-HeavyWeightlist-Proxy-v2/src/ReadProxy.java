public class ReadProxy implements ReadList {

    private HeavyWeightList list;
    private ListAccess access;

    public ReadProxy(HeavyWeightList list, ListAccess access) {
        this.list = list;
        this.access = access;
    }

    @Override
    public int read() {
        if (list == null) throw new IllegalStateException("Access permission revoked.");
        if (access.hasReadAccess(Thread.currentThread())) {
            return list.read();
        }
        throw new IllegalStateException("Not enough permissions.");
    }

    public void terminate() {
        list = null;
    }
}
