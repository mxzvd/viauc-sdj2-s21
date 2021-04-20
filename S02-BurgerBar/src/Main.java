public class Main {
    public static void main(String[] args) {

        Burgerbar burgerbar = new Burgerbar(50);

        BurgerBarEmployee employee1 = new BurgerBarEmployee("employee1", burgerbar);
        BurgerBarEmployee employee2 = new BurgerBarEmployee("employee2", burgerbar);

        BurgerBarCustomer customer1 = new BurgerBarCustomer("customer1", burgerbar, 2);
        BurgerBarCustomer customer2 = new BurgerBarCustomer("customer2", burgerbar, 1);
        BurgerBarCustomer customer3 = new BurgerBarCustomer("customer3", burgerbar, 4);
        BurgerBarCustomer customer4 = new BurgerBarCustomer("customer4", burgerbar, 3);
        BurgerBarCustomer customer5 = new BurgerBarCustomer("customer5", burgerbar, 2);

        Thread tEmployee1 = new Thread(employee1, "employee1");
        Thread tEmployee2 = new Thread(employee1, "employee2");

        Thread tCustomer1 = new Thread(customer1, "customer1");
        Thread tCustomer2 = new Thread(customer2, "customer2");
        Thread tCustomer3 = new Thread(customer3, "customer3");
        Thread tCustomer4 = new Thread(customer4, "customer4");
        Thread tCustomer5 = new Thread(customer5, "customer5");

        tEmployee1.setDaemon(true);
        tEmployee2.setDaemon(true);

        tEmployee1.start();
        tEmployee2.start();

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
