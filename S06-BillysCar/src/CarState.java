public abstract class CarState {

    public abstract void click(Car car);

    public String status() {
        return getClass().getSimpleName();
    }
}
