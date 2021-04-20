import java.util.Random;

public class ConfessionChair {

    private String sin;

    public synchronized void EnterConfessionBooth(String sin) {
        while (this.sin != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        System.out.println(Thread.currentThread().getName() + " has entered the confession chair, to confess " + sin + ".");
        this.sin = sin;
        notifyAll();
    }

    public synchronized int leaveConfessionBooth() {
        System.out.println(Thread.currentThread().getName() + " has left the confession chair, to pray for " + sin + ".");
        this.sin = null;
        notifyAll();
        Random random = new java.util.Random();
        return random.nextInt(4) + 1;
    }
}
