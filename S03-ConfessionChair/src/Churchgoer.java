public class Churchgoer implements Runnable {

    ConfessionChair confessionChair;

    public Churchgoer(ConfessionChair confessionChair) {
        this.confessionChair = confessionChair;
    }

    @Override public void run() {
        for (int i = 1; i <= 3; i++) {
            confessionChair.EnterConfessionBooth(Thread.currentThread().getName() + "'s sin #" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
            int toPrayCount = confessionChair.leaveConfessionBooth();
            for (int j = 1; j <= toPrayCount; j++) {
                System.out.println(Thread.currentThread().getName() + " -> prayed Ave Maria (" + j + " out of " + toPrayCount + ") for his sin #" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // Do nothing.
                }
            }
        }
    }
}
