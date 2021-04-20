public class BurgerBarEmployee implements Runnable {

    private String name;
    private Burgerbar burgerbar;

    public BurgerBarEmployee(String name, Burgerbar burgerbar) {
        this.name = name;
        this.burgerbar = burgerbar;
    }

    @Override public void run() {
        while (true) {
            burgerbar.makeBurger();
            System.out.println(Thread.currentThread().getName() + " just made a burger.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
