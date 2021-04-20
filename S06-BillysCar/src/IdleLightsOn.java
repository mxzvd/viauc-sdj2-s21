public class IdleLightsOn extends CarState {
    @Override public void click(Car car) {
        car.setCounter();
        car.setState(new IdleLightsOff());
    }
}
