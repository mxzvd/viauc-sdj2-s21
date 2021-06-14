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
                ReadList access = lock.acquireRead();
                access.read();
                lock.releaseRead(access);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
