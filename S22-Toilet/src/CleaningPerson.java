public class CleaningPerson implements Runnable {

    private PublicToilet lock;

    public CleaningPerson(PublicToilet lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) try {
            Thread.sleep(10000);
            lock.startCleaning();
            Thread.sleep(5000);
            lock.endCleaning();
        } catch (Exception e) {
            // Do nothing.
        }
    }
}
