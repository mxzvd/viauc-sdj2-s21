package version2;

import version2.utility.collection.BoundedArrayQueue;
import version2.utility.collection.QueueADT;

public class CarrotBunch {

    private QueueADT<Carrot> carrotBunch;
    private int whenToStartPeeling;
    private int whenToStopPeeling;

    public CarrotBunch(int start, int stop) {
        carrotBunch = new BoundedArrayQueue<>(stop);
        whenToStartPeeling = start;
        whenToStopPeeling = stop;
    }

    public synchronized void peel(Carrot carrot) {
        while (carrotBunch.size() > whenToStartPeeling) {
            try {
                System.out.println(Thread.currentThread().getName() + " is now waiting to peel more carrots.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        carrotBunch.enqueue(carrot);
        notifyAll();
        System.out.println("Added a carrot. Totaling " + carrotBunch.size() + " carrots in the bunch.");
    }

    public synchronized Carrot eat() {
        while (carrotBunch.size() <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " is now waiting for some carrots to eat.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        Carrot toReturn = carrotBunch.dequeue();
        notifyAll();
        System.out.println("Taken a carrot. Totaling " + carrotBunch.size() + " carrots in the bunch.");
        return toReturn;
    }
}
