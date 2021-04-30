public class Reader implements Runnable {

    private ReadWriteAccess lock;

    public Reader(ReadWriteAccess lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep((int) (Math.random() * 10) + 1);
                lock.acquireRead().read();
                lock.releaseRead();
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }
}
