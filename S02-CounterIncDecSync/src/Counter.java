public class Counter {

    private long value;
    private long min;
    private long max;

    public Counter (long min, long max) {
        value = 0;
        this.min = min;
        this.max = max;
    }

    public synchronized void increment() {
        while (value >= max) {
            try {
                System.out.println("Th-" + Thread.currentThread().getName() + " -> Moved to waiting.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        value++;
        notifyAll();
    }

    public synchronized void decrement() {
        while (value <= min) {
            try {
                System.out.println("Th-" + Thread.currentThread().getName() + " -> Moved to waiting.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
        value--;
        notifyAll();
    }

    public synchronized long getValue() {
        return value;
    }
}
