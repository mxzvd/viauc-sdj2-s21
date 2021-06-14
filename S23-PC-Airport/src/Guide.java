public class Guide implements Runnable {

    private AirportQueue usQueue;
    private AirportQueue nonUSQueue;

    public Guide(AirportQueue usQueue, AirportQueue nonUSQueue) {
        this.usQueue = usQueue;
        this.nonUSQueue = nonUSQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 2) + 5000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
            Passenger tmp = generatePassenger();
            if (tmp.getNationality().equals("us")) {
                usQueue.putPassengerInQueue(tmp);
                System.out.println("Passenger guided to us queue.");
            } else {
                nonUSQueue.putPassengerInQueue(tmp);
                System.out.println("Passenger guided to non us queue.");
            }
        }
    }

    private Passenger generatePassenger() {
        return new Passenger(
                new String[] {"us", "ro", "dk"}[(int)(Math.random() * 3)],
                (int) (Math.random() * 100) + 10
        );
    }
}
