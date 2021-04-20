public class TestCounter {
    public static void main(String[] args) {

        Counter counter = new Counter();
        CounterIncrementor c1 = new CounterIncrementor(counter, 200000);
        CounterIncrementor c2 = new CounterIncrementor(counter, 200000);

        Thread t1 = new Thread(c1, "Incrementor1");
        Thread t2 = new Thread(c2, "Incrementor2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // Do nothing.
        }

        System.out.println(Thread.currentThread().getName() + " " + counter.getValue());
    }
}
