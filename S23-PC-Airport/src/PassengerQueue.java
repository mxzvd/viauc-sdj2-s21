public interface PassengerQueue {
    void putPassengerInQueue(Passenger p);
    Passenger getNextPassenger();
}
