public class DoorOpening extends DoorState {

    @Override
    public void click(Door door) {
        door.setState(new DoorClosing());
    }

    @Override
    public void complete(Door door) {
        door.setState(new DoorOpen());
    }
}
