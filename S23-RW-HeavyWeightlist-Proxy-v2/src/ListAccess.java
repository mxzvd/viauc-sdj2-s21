import java.util.ArrayList;

public class ListAccess implements ReadWriteAccess {

    private HeavyWeightList list;
    private int readers;
    private int writers;
    private int waitingWriters;
    private ArrayList<Thread> allowedReadAccess;
    private ArrayList<Thread> allowedWriteAccess;

    public ListAccess(HeavyWeightList list) {
        this.list = list;
        readers = 0;
        writers = 0;
        waitingWriters = 0;
        allowedReadAccess = new ArrayList<>();
        allowedWriteAccess = new ArrayList<>();
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
        allowedReadAccess.add(Thread.currentThread());
        return new ReadProxy(list, this);
    }

    @Override
    public synchronized void releaseRead(ReadList list) {
        if (list instanceof ReadProxy) ((ReadProxy) list).terminate();
        readers--;
        System.out.println(Thread.currentThread().getName() + " has finalized reading.");
        allowedReadAccess.remove(Thread.currentThread());
        notifyAll();
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
        allowedWriteAccess.add(Thread.currentThread());
        return new WriteProxy(list, this);
    }

    @Override
    public synchronized void releaseWrite(ReadWriteList list) {
        if (list instanceof WriteProxy) ((WriteProxy) list).terminate();
        writers--;
        System.out.println(Thread.currentThread().getName() + " has finalized writing.");
        allowedWriteAccess.remove(Thread.currentThread());
        notifyAll();
    }

    public boolean hasReadAccess(Thread t) {
        for (Thread readAccess : allowedReadAccess) if (readAccess.equals(t)) return true;
        return false;
    }

    public boolean hasWriteAccess(Thread t) {
        for (Thread writeAccess : allowedWriteAccess) if (writeAccess.equals(t)) return true;
        return false;
    }
}
