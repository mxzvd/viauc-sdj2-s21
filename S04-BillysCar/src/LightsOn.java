public class LightsOn extends LightsState {

    @Override
    public void buttonClick(Car car) {
        car.setLightsState(new LightsOff());
    }
}
