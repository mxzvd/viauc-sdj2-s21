public class Car {

    private CarState state;
    private int counter;

    public Car() {
        state = new IdleLightsOff();
        counter = 0;
    }

    public void click() {
        state.click(this);
    }

    public void setState(CarState state) {
        this.state = state;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter() {
        counter += counter < 8 ? 1 : -7;
    }

    public String status() {
        return state.toString();
    }
}
