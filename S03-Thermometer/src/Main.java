public class Main {
    public static void main(String[] args) {

        Thread thermometer1 = new Thread(new Thermometer("t1", 15, 1), "t1");
        Thread thermometer2 = new Thread(new Thermometer("t2", 15, 7), "t2");

        thermometer1.start();
        thermometer2.start();
    }
}
