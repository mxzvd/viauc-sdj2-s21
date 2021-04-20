public class DoorOpening extends DoorState {

    private Thread motor;
    private boolean completed;

    public DoorOpening(Door door) {
        completed = false;
        motor = new Thread(() -> {
            try {
                Thread.sleep(5000);
                complete(door);
            } catch (InterruptedException e) {
                System.out.println("Motor interrupted while opening the door.");
            }
        });
        motor.start();
    }

    @Override
    public synchronized void complete(Door door) {
        if (!completed) {
            System.out.println("Motor completed opening the door.");
            door.setState(new DoorOpen(door));
            completed = true;
        }
    }

    @Override
    public synchronized void click(Door door) {
        if (!completed) {
            motor.interrupt();
            door.setState(new DoorClosing(door));
            completed = true;
        }
    }
}
