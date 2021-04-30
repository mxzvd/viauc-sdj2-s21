public class ListAccess implements ReadWriteAccess {

    private HeavyWeightList list;
    private int readers;
    private int writers;
    private int waitingWriters;

    public ListAccess(HeavyWeightList list) {
        this.list = list;
    }

    @Override
    public synchronized ReadList acquireRead() {
        while (writers > 0 || waitingWriters > 0) try {
            System.out.println(Thread.currentThread().getName() + " is now waiting, for the active writers: " + writers + " and for the waiting writers: " + waitingWriters);
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        readers++;
        return list;
    }

    @Override
    public synchronized void releaseRead() {
        readers--;
        System.out.println(Thread.currentThread().getName() + " has finalized reading.");
        if (readers == 0) notify();
    }

    @Override
    public synchronized ReadWriteList acquireWrite() {
        waitingWriters++;
        while (readers > 0 || writers > 0) try {
            System.out.println(Thread.currentThread().getName() + " is now waiting, for the active readers: " + readers + " and active writers: " + writers);
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        waitingWriters--;
        writers++;
        return list;
    }

    @Override
    public synchronized void releaseWrite(int I) {
        writers--;
        System.out.println(Thread.currentThread().getName() + " has finalized writing.");
        notifyAll();
    }
}
