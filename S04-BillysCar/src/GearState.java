public abstract class GearState {

    public abstract void buttonClick(Car car);

    public String status() {
        return getClass().getSimpleName();
    }
}
