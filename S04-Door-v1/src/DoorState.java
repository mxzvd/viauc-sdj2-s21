public abstract class DoorState {

    public abstract void click(Door door);

    public void complete(Door door) {
        // Do nothing.
    }

    public void timeout(Door door) {
        // Do nothing.
    }

    public String status() {
        return getClass().getSimpleName();
    }
}
