public class BurgerBarCustomer implements Runnable {

    private String name;
    private Burgerbar burgerbar;
    private int burgersToEat;

    public BurgerBarCustomer(String name, Burgerbar burgerbar, int burgersToEat) {
        this.name = name;
        this.burgerbar = burgerbar;
        this.burgersToEat = burgersToEat;
    }

    @Override public void run() {
        while (burgersToEat-- > 0) {
            burgerbar.eatBurger(name);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
        System.out.println(Thread.currentThread().getName() + " is served.");
    }
}
