public class CookieBaker implements Runnable {

    private CookieJar cookieJar;

    public CookieBaker(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
    }

    @Override public void run() {
        while (true) {
            cookieJar.startBaking();
            System.out.println(Thread.currentThread().getName() + " just baked some cookies.");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
