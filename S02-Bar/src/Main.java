public class Main {
    public static void main(String[] args) {

        Bar bar = new Bar(20);

        Thread barThread = new Thread(bar, "Bar");

        Thread bartender1 = new Thread(new Bartender(bar, "Bartender1"), "Th-Bartender1");
        Thread bartender2 = new Thread(new Bartender(bar, "Bartender2"), "Th-Bartender2");

        Thread customer1 = new Thread(new Customer(bar, "Customer1"), "Th-Customer1");
        Thread customer2 = new Thread(new Customer(bar, "Customer2"), "Th-Customer2");

        try {
            bartender1.join();
            bartender2.join();
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            // Do nothing.
        }

        barThread.start();
        bartender1.start();
        bartender2.start();
        customer1.start();
        customer2.start();
    }
}
