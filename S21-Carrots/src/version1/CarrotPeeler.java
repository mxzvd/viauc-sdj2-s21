package version1;

public class CarrotPeeler implements Runnable {

    private Buffer<Carrot> carrotBunch;

    public CarrotPeeler(Buffer<Carrot> carrotBunch) {
        this.carrotBunch = carrotBunch;
    }

    @Override
    public void run() {
        while (true) {
            carrotBunch.put(new Carrot());
            System.out.println(Thread.currentThread().getName() + " just peeled a carrot.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
