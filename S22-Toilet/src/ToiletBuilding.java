public class ToiletBuilding implements PublicToilet {

    private int people;
    private int cleaners;

    public ToiletBuilding() {
        people = 0;
        cleaners = 0;
    }

    @Override
    public synchronized void stepIntoCabin() {
        while (cleaners > 0 || people == 5) try {
            System.out.println(people == 5 ? "All cabins are full " + Thread.currentThread().getName() + " is now waiting." : "Toilet is being cleaned. " + Thread.currentThread().getName() + " is now waiting.");
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        people++;
        System.out.println(Thread.currentThread().getName() + " is now using the toilet. " + people + " are now using the cabins.");
    }

    @Override
    public synchronized void leaveCabin() {
        people--;
        System.out.println(Thread.currentThread().getName() + " has left a cabin. " + people + " left in the cabins.");
        notifyAll();
    }

    @Override
    public synchronized void startCleaning() {
        while (people > 0) try {
            System.out.println(people + " people are in cabins. " + Thread.currentThread().getName() + " is now waiting.");
            wait();
        } catch (Exception e) {
            // Do nothing.
        }
        cleaners++;
        System.out.println(Thread.currentThread().getName() + " is now cleaning.");
    }

    @Override
    public synchronized void endCleaning() {
        cleaners--;
        System.out.println(Thread.currentThread().getName() + " has finished cleaning the toilet.");
        notifyAll();
    }
}
