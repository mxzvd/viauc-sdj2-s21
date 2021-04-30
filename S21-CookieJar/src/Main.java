public class Main {
    public static void main(String[] args) {

        CookieJar cookieJar = new CookieJar(50, 16, 8);

        CookieBaker employee1 = new CookieBaker(cookieJar);

        CookieEater customer1 = new CookieEater(cookieJar);
        CookieEater customer2 = new CookieEater(cookieJar);
        CookieEater customer3 = new CookieEater(cookieJar);
        CookieEater customer4 = new CookieEater(cookieJar);
        CookieEater customer5 = new CookieEater(cookieJar);

        Thread tEmployee1 = new Thread(employee1, "employee1");

        Thread tCustomer1 = new Thread(customer1, "customer1");
        Thread tCustomer2 = new Thread(customer2, "customer2");
        Thread tCustomer3 = new Thread(customer3, "customer3");
        Thread tCustomer4 = new Thread(customer4, "customer4");
        Thread tCustomer5 = new Thread(customer5, "customer5");

        tEmployee1.setDaemon(true);

        tEmployee1.start();

        tCustomer1.start();
        tCustomer2.start();
        tCustomer3.start();
        tCustomer4.start();
        tCustomer5.start();

        try {
            tCustomer1.join();
            tCustomer2.join();
            tCustomer3.join();
            tCustomer4.join();
            tCustomer5.join();
        } catch (InterruptedException e) {
            // Do nothing.
        }
    }
}
