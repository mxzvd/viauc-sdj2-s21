public class Customer implements Runnable {

    private Bar bar;
    private String name;

    public Customer(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    @Override public void run() {
        while (bar.isOpen()) {
            bar.takeBeer();
            System.out.println(Thread.currentThread().getName() + ": Customer " + name + " just took a beer.");
            try {
                Thread.sleep(1800);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        System.out.println("Bar closed, customer " + name + " left.");
    }
}
