public class LightsOff extends LightsState {

    @Override
    public void buttonClick(Car car) {
        car.setLightsState(new LightsOn());
    }
}
