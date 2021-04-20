public class CounterDecrementer implements Runnable {

    private Counter counter;
    private int updates;

    public CounterDecrementer(Counter counter, int updates) {
        this.counter = counter;
        this.updates = updates;
    }

    @Override public void run() {
        while (updates-- > 0) counter.decrement();
        System.out.println(Thread.currentThread().getName() + " -> Finished. Value after: " + counter.getValue());
    }
}
