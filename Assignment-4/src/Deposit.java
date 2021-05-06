import utility.collection.ArrayList;
import utility.collection.ListADT;

public class Deposit implements DepositTarget {

    private ListADT<Valuable> queue;
    private int capacity;

    public Deposit(int capacity) {
        queue = new ArrayList<>();
        this.capacity = capacity;
    }

    @Override
    public synchronized String toString() {
        return queue.toString();
    }

    @Override
    public synchronized void put(Valuable element) {
        if (element == null) throw new IllegalArgumentException("Valuable argument can't be null.");
        while (queue.size() >= capacity) {
            try {
                Log.getInstance().addLog("The Deposit is full. " + Thread.currentThread().getName() + " is now waiting to put a valuable.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        queue.add(element);
        notifyAll();
        Log.getInstance().addLog("Miner " + Thread.currentThread().getName() + " added " + element + " to the deposit. Totaling " + queue.size() + " valuables.");
    }

    @Override
    public synchronized Valuable take() {
        while (queue.size() == 0) {
            try {
                Log.getInstance().addLog("The Deposit is empty. " + Thread.currentThread().getName() + " is now waiting to take any valuables.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        Valuable toReturn = queue.remove(0);
        notifyAll();
        Log.getInstance().addLog("Transporter " + Thread.currentThread().getName() + " took a valuable to be transported. Totaling " + queue.size() + " valuables left.");
        return toReturn;
    }

    @Override
    public synchronized Valuable look() {
        return queue.isEmpty() ? null : queue.get(0);
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public synchronized boolean isFull() {
        return queue.isFull();
    }

    @Override
    public synchronized int size() {
        return queue.size();
    }
}
