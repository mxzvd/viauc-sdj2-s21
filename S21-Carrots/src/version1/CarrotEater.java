package version1;

public class CarrotEater implements Runnable {

    private Buffer<Carrot> carrotBunch;

    public CarrotEater(Buffer<Carrot> carrotBunch) {
        this.carrotBunch = carrotBunch;
    }

    @Override
    public void run() {
        while (true) {
            carrotBunch.take();
            System.out.println(Thread.currentThread().getName() + " just ate a carrot.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
