public class Person implements Runnable {

    private PublicToilet lock;

    public Person(PublicToilet lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20000);
                lock.stepIntoCabin();
                Thread.sleep(1000);
                lock.leaveCabin();
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }
}
