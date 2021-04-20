public class Bartender implements Runnable {

    private Bar bar;
    private String name;

    public Bartender(Bar bar, String name) {
        this.bar = bar;
        this.name = name;
    }

    @Override public void run() {
        while (bar.isOpen()) {
            bar.placeBeer(new Beer());
            System.out.println(Thread.currentThread().getName() + ": Bartender" + name + " just placed a beer, the bar now has " + bar.getNumberOfBeers() + " beers.");
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        System.out.println("Bar closed, bartender " + name + " finished his workday.");
    }
}
