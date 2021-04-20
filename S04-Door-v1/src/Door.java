public class Door {

    private DoorState state;

    public Door() {
        this.state = new DoorClosed();
    }

    public void click() {
        state.click(this);
    }

    public void complete() {
        state.complete(this);
    }

    public void setState(DoorState state) {
        this.state = state;
    }

    public String status() {
        return state.status();
    }

    public void timeout() {
        state.timeout(this);
    }
}
