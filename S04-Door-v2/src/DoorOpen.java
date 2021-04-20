public class DoorOpen extends DoorState {

    private Thread timer;
    private boolean completed;

    public DoorOpen(Door door) {
        completed = false;
        timer = new Thread(() -> {
            try {
                Thread.sleep(10000);
                timeout(door);
            } catch (InterruptedException e) {
                System.out.println("Timer of the opened door was interrupted.");
            }
        });
        timer.start();
    }

    @Override
    public void click(Door door) {
        timer.interrupt();
        door.setState(new DoorStayOpen());
    }

    @Override
    public void timeout(Door door) {
        if (!completed) {
            door.setState(new DoorClosing(door));
            completed = true;
        }
    }
}
