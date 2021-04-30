import java.util.ArrayDeque;
import java.util.Queue;

public class Bridge implements Lane {

    private Queue<Thread> queue;
    private int redCarsOnBridge;
    private int blueCarsOnBridge;

    public Bridge() {
        queue = new ArrayDeque<>();
        redCarsOnBridge = 0;
        blueCarsOnBridge = 0;
    }

    @Override
    public synchronized void enterFromTheLeft() {
        queue.offer(Thread.currentThread());
        while (queue.peek() != Thread.currentThread()) try {
            System.out.println(Thread.currentThread().getName() + " is now waiting in the queue.");
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        System.out.println(Thread.currentThread().getName() + " is now first in the queue.");
        while (blueCarsOnBridge > 0) try {
            System.out.println(Thread.currentThread().getName() + " is waiting for all the blue cars left on bridge: " + blueCarsOnBridge);
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        redCarsOnBridge++;
        queue.remove();
        System.out.println(Thread.currentThread().getName() + " is now passing the bridge.");
        notifyAll();
    }

    @Override
    public synchronized void exitToTheRight() {
        redCarsOnBridge--;
        System.out.println(Thread.currentThread().getName() + " has passed the bridge. Red cars on bridge: " + redCarsOnBridge);
        if (redCarsOnBridge == 0) notifyAll();
    }

    @Override
    public synchronized void enterFromTheRight() {
        queue.offer(Thread.currentThread());
        while (queue.peek() != Thread.currentThread()) try {
            System.out.println(Thread.currentThread().getName() + " is now waiting in the queue.");
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        System.out.println(Thread.currentThread().getName() + " is now first in the queue.");
        while (redCarsOnBridge > 0) try {
            System.out.println(Thread.currentThread().getName() + " is waiting for all the red cars left on bridge: " + redCarsOnBridge);
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        blueCarsOnBridge++;
        queue.remove();
        System.out.println(Thread.currentThread().getName() + " is now passing the bridge.");
        notifyAll();
    }

    @Override
    public synchronized void exitToTheLeft() {
        blueCarsOnBridge--;
        System.out.println(Thread.currentThread().getName() + " has passed the bridge. Blue cars on bridge: " + blueCarsOnBridge);
        if (blueCarsOnBridge == 0) notifyAll();
    }
}
