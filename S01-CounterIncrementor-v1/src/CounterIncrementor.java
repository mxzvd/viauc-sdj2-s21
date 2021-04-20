public class CounterIncrementor implements Runnable {

    private Counter counter;
    private int updates;

    public CounterIncrementor(Counter counter, int updates) {
        this.counter = counter;
        this.updates = updates;
    }

    @Override public void run() {
        while (updates-- > 0) counter.increment();
    }
}
