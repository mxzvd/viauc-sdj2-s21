public class BlueCar implements Runnable {

    private Lane lock;

    public BlueCar(Lane lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) try {
            Thread.sleep(2000);
            lock.enterFromTheRight();
            Thread.sleep((int) (Math.random() * 7) + 1);
            lock.exitToTheLeft();
        } catch (Exception e) {
            // Do nothing.
        }
    }
}
