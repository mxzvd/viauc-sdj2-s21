public class Main {
    public static void main(String[] args) {
        AirportQueue usQueue = new AirportQueue();
        AirportQueue nonUSQueue = new AirportQueue();

        Guide guide = new Guide(usQueue, nonUSQueue);
        new Thread(guide).start();

        PassportController usController = new PassportController(usQueue);
        new Thread(usController).start();

        PassportController nonUSController = new PassportController(nonUSQueue);
        new Thread(nonUSController).start();
    }
}
