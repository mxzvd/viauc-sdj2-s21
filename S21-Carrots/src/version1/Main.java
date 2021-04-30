package version1;

public class Main {
    public static void main(String[] args) {

        Buffer<Carrot> carrotBunch = new BlockingQueue<>(20);

        CarrotPeeler employee1 = new CarrotPeeler(carrotBunch);
        CarrotPeeler employee2 = new CarrotPeeler(carrotBunch);
        CarrotPeeler employee3 = new CarrotPeeler(carrotBunch);

        CarrotEater customer1 = new CarrotEater(carrotBunch);
        CarrotEater customer2 = new CarrotEater(carrotBunch);
        CarrotEater customer3 = new CarrotEater(carrotBunch);
        CarrotEater customer4 = new CarrotEater(carrotBunch);
        CarrotEater customer5 = new CarrotEater(carrotBunch);

        Thread tEmployee1 = new Thread(employee1, "employee1");
        Thread tEmployee2 = new Thread(employee2, "employee2");
        Thread tEmployee3 = new Thread(employee3, "employee3");

        Thread tCustomer1 = new Thread(customer1, "customer1");
        Thread tCustomer2 = new Thread(customer2, "customer2");
        Thread tCustomer3 = new Thread(customer3, "customer3");
        Thread tCustomer4 = new Thread(customer4, "customer4");
        Thread tCustomer5 = new Thread(customer5, "customer5");

        tEmployee1.setDaemon(true);
        tEmployee2.setDaemon(true);
        tEmployee3.setDaemon(true);

        tEmployee1.start();
        tEmployee2.start();
        tEmployee3.start();

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
