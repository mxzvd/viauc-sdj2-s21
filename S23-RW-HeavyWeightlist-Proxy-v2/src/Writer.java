public class Writer implements Runnable {

    private ReadWriteAccess lock;

    public Writer(ReadWriteAccess lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep((int) (Math.random() * 2) + 5);
                ReadWriteList access = lock.acquireWrite();
                access.write(i);
                lock.releaseWrite(access);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
