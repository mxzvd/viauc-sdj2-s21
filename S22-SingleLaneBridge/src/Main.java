public class Main {
    public static void main(String[] args) {

        Lane sharedResource = new Bridge();

        Thread[] redCars = new Thread[5];
        for (int i = 0; i < redCars.length; i++) {
            (redCars[i] = new Thread(new RedCar(sharedResource), "RedCar" + i)).start();
        }

        Thread[] blueCars = new Thread[5];
        for (int i = 0; i < blueCars.length; i++) {
            (blueCars[i] = new Thread(new BlueCar(sharedResource), "BlueCar" + i)).start();
        }
    }
}
