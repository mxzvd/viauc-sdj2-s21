public class RedCar implements Runnable {

    private Lane lock;

    public RedCar(Lane lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) try {
            Thread.sleep(2000);
            lock.enterFromTheLeft();
            Thread.sleep((int) (Math.random() * 7) + 1);
            lock.exitToTheRight();
        } catch (Exception e) {
            // Do nothing.
        }
    }
}
