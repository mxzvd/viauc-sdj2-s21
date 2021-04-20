public class CounterIncrementer implements Runnable {

    private Counter counter;
    private int updates;

    public CounterIncrementer(Counter counter, int updates) {
        this.counter = counter;
        this.updates = updates;
    }

    @Override public void run() {
        while (updates-- > 0) counter.increment();
        System.out.println(Thread.currentThread().getName() + " -> Finished. Value after: " + counter.getValue());
    }
}
