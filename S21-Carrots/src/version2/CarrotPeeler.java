package version2;

public class CarrotPeeler implements Runnable {

    private CarrotBunch carrotBunch;

    public CarrotPeeler(CarrotBunch carrotBunch) {
        this.carrotBunch = carrotBunch;
    }

    @Override public void run() {
        while (true) {
            carrotBunch.peel(new Carrot());
            System.out.println(Thread.currentThread().getName() + " just peeled a carrot.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
