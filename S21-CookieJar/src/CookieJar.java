import utility.collection.BoundedArrayQueue;

public class CookieJar {

    private BoundedArrayQueue<Cookie> cookieJar;
    private int cookieCountWhenToBake;
    private int numberOfCookiesInTheOven;
    private int cookiePlateSize;

    public CookieJar(int jarSize, int cookieCountWhenToBake, int cookiePlateSize) {
        cookieJar = new BoundedArrayQueue<>(jarSize);
        this.cookieCountWhenToBake = cookieCountWhenToBake;
        this.cookiePlateSize = cookiePlateSize;
    }

    public synchronized void startBaking() {
        while (cookieJar.size() >= cookieCountWhenToBake) {
            try {
                System.out.println(Thread.currentThread().getName() + " is now waiting to bake some cookies.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        for (int i = 0; i < cookiePlateSize; i ++) cookieJar.enqueue(new Cookie("Chocolate-Cookie"));
        if (cookieJar.size() == 1) {
            notify();
        }
        System.out.println("Added " + cookiePlateSize + " cookies. Totaling " + cookieJar.size() + " in the jar.");
    }

    public int finishedBaking() {
        return cookieJar.size();
    }

    public synchronized void eat() {
        while (cookieJar.size() <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " is now waiting for some cookies to eat.");
                wait();
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        cookieJar.dequeue();
        if (cookieJar.size() == cookieJar.capacity() - 1) {
            notify();
        }
        System.out.println("Taken a cookie. Totaling " + cookieJar.size() + " in the jar.");
    }
}
