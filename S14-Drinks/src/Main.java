public class Main {
    public static void main(String[] args) {

        RegularBar rBar = new RegularBar();
        WineBar wBar = new WineBar();

        new Thread(() -> {
            System.out.println("c1: " + rBar.orderDrink("Carlsberg"));
            System.out.println("c1: " + rBar.orderDrink("RoseWine"));
        }).start();

        new Thread(() -> {
            System.out.println("c2: " + wBar.orderDrink("WhiteWine"));
            System.out.println("c2: " + rBar.orderDrink("RoseWine"));
        }).start();

        new Thread(() -> {
            System.out.println("c3: " + wBar.orderDrink("WhiteWine"));
            System.out.println("c3: " + wBar.orderDrink("RoseWine"));
        }).start();

        new Thread(() -> {
            System.out.println("c4: " + rBar.orderDrink("Corona"));
            System.out.println("c4: " + rBar.orderDrink("Carlsberg"));
        }).start();
    }
}
