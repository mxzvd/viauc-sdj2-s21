public class WriteProxy implements ReadWriteList {

    private HeavyWeightList list;
    private ListAccess access;

    public WriteProxy(HeavyWeightList list, ListAccess access) {
        this.list = list;
        this.access = access;
    }

    @Override
    public int read() {
        if (list == null) throw new IllegalStateException("Access permission revoked.");
        if (access.hasWriteAccess(Thread.currentThread())) {
            return list.read();
        }
        throw new IllegalStateException("Not enough permissions.");
    }

    @Override
    public void write(int value) {
        if (list == null) throw new IllegalStateException("Access permission revoked.");
        if (access.hasWriteAccess(Thread.currentThread())) {
            list.write(value);
        }
        throw new IllegalStateException("Not enough permissions.");
    }

    public void terminate() {
        list = null;
    }
}
