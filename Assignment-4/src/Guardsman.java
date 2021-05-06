import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Guardsman implements TreasureRoomDoor {

    private TreasureRoom treasureRoom;
    private int readers;
    private int writers;
    private Queue<Thread> queue;
    private Map<Thread, TreasureReadProxy> activeReaders;
    private Map<Thread, TreasureReadWriteProxy> activeWriters;

    public Guardsman(TreasureRoom treasureRoom) {
        this.treasureRoom = treasureRoom;
        readers = 0;
        writers = 0;
        queue = new ArrayDeque<>();
        activeReaders = new HashMap<>();
        activeWriters = new HashMap<>();
    }

    @Override
    public synchronized TreasureRead acquireReadAccess() {
        queue.offer(Thread.currentThread());
        while (queue.peek() != Thread.currentThread()) try {
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        while (writers > 0) try {
            Log.getInstance().addLog(Thread.currentThread().getName() + " is now waiting to get reading permissions.");
            Log.getInstance().addLog("writers: " + writers);
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        Log.getInstance().addLog(Thread.currentThread().getName() + " is now taking notes (reading) in the treasure room.");
        readers++;
        queue.remove();
        TreasureReadProxy toReturn = new TreasureReadProxy(treasureRoom);
        activeReaders.put(Thread.currentThread(), toReturn);
        notifyAll();
        return toReturn;
    }

    @Override
    public synchronized void releaseReadAccess() {
        readers--;
        notifyAll();
        TreasureReadProxy proxy = activeReaders.get(Thread.currentThread());
        if (proxy != null) {
            proxy.restrictAccess();
            activeReaders.remove(Thread.currentThread());
        }
        Log.getInstance().addLog(Thread.currentThread().getName() + " is finished taking notes (reading) in the treasure room.");
    }

    @Override
    public synchronized TreasureReadWrite acquireWriteAccess() {
        queue.offer(Thread.currentThread());
        while (queue.peek() != Thread.currentThread()) try {
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        while (readers > 0 || writers > 0) try {
            Log.getInstance().addLog(Thread.currentThread().getName() + " is now waiting to get writing permissions.");
            Log.getInstance().addLog("readers: " + readers + ", writers: " + writers);
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        Log.getInstance().addLog(Thread.currentThread().getName() + " is now managing some valuables (writing) in the treasure room.");
        writers++;
        queue.remove();
        TreasureReadWriteProxy toReturn = new TreasureReadWriteProxy(treasureRoom);
        activeWriters.put(Thread.currentThread(), toReturn);
        notifyAll();
        return toReturn;
    }

    @Override
    public synchronized void releaseWriteAccess() {
        writers--;
        notifyAll();
        TreasureReadWriteProxy proxy = activeWriters.get(Thread.currentThread());
        if (proxy != null) {
            proxy.restrictAccess();
            activeWriters.remove(Thread.currentThread());
        }
        Log.getInstance().addLog(Thread.currentThread().getName() + " is finished managing some valuables (writing) in the treasure room.");
    }
}
