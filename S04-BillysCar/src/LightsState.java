public abstract class LightsState {

    public abstract void buttonClick(Car car);

    public String status() {
        return getClass().getSimpleName();
    }
}
