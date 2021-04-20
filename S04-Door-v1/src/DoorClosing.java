public class DoorClosing extends DoorState {

    @Override
    public void click(Door door) {
        door.setState(new DoorOpening());
    }

    @Override
    public void complete(Door door) {
        door.setState(new DoorClosed());
    }
}
