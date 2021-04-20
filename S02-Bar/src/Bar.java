import java.util.ArrayList;

public class Bar implements Runnable {

    private ArrayList<Beer> beers;
    private int maxBeers;
    private boolean barIsOpen;

    public Bar(int maxBeers) {
        beers = new ArrayList<>();
        this.maxBeers = maxBeers;
        barIsOpen = true;
    }

    public synchronized void placeBeer(Beer b) {
        while (beers.size() >= maxBeers) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Waiting to place a beer.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        beers.add(b);
        notifyAll();
    }

    public synchronized void takeBeer() {
        while (beers.size() <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Waiting to take a beer.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        beers.remove(0);
        notifyAll();
    }

    public synchronized int getNumberOfBeers() {
        return beers.size();
    }

    public synchronized boolean isOpen() {
        return barIsOpen;
    }

    @Override public void run() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // Do nothing.
        }
        barIsOpen = false;
        System.out.println("The bar is now closed.");
    }
}
