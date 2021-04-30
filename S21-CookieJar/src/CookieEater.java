public class CookieEater implements Runnable {

    private CookieJar cookieJar;

    public CookieEater(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    @Override public void run() {
        while (true) {
            cookieJar.eat();
            System.out.println(Thread.currentThread().getName() + " just ate a cookie.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
