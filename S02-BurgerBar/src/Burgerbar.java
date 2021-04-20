public class Burgerbar {

    private int numberOfBurgers;
    private int maxNumberOfBurgers;

    public Burgerbar(int maxNumberOfBurgers) {
        numberOfBurgers = 0;
        this.maxNumberOfBurgers = maxNumberOfBurgers;
    }

    public synchronized void makeBurger() {
        while (numberOfBurgers >= maxNumberOfBurgers) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Moved to wait.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        numberOfBurgers++;
        notifyAll();
    }

    public synchronized void eatBurger(String who) {
        while (numberOfBurgers <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " -> Moved to wait.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        numberOfBurgers--;
        System.out.println(who + " ate a burger (" + numberOfBurgers + " left at bar).");
        notifyAll();
    }

    public synchronized int getNumberOfBurgers() {
        return numberOfBurgers;
    }
}
