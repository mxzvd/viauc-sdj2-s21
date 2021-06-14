import java.util.ArrayDeque;
import java.util.Queue;

public class AirportQueue implements PassengerQueue {

    private Queue<Passenger> queue;

    public AirportQueue() {
        queue = new ArrayDeque<>();
    }

    @Override
    public synchronized void putPassengerInQueue(Passenger p) {
        queue.add(p);
    }

    @Override
    public synchronized Passenger getNextPassenger() {
        return queue.remove();
    }
}
