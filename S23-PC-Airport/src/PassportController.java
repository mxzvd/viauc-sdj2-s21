public class PassportController implements Runnable {

    private AirportQueue queue;

    public PassportController(AirportQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Passenger tmp = null;
            try {
                Thread.sleep((int) (Math.random() * 7) + 1);
                tmp = queue.getNextPassenger();
            } catch (Exception e) {
                // Do nothing.
            }
            if (tmp != null) System.out.println("Passport " + tmp.getPassportNumber() + " verified.");
        }
    }
}
