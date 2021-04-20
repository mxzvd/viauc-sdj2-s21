public abstract class AlertState {

    public abstract void click(Phone phone);
    public abstract String alert();
    public void volumeUp(Phone phone) {
        // Do nothing.
    }

    public void volumeDown(Phone phone) {
        // Do nothing.
    }
}
