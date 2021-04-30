package version2;

public class CarrotEater implements Runnable {

    private CarrotBunch carrotBunch;

    public CarrotEater(CarrotBunch carrotBunch) {
        this.carrotBunch = carrotBunch;
    }

    @Override public void run() {
        while (true) {
            carrotBunch.eat();
            System.out.println(Thread.currentThread().getName() + " just ate a carrot.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
