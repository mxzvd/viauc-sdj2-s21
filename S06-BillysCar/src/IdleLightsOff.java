public class IdleLightsOff extends CarState {
    @Override
    public void click(Car car) {
        car.setCounter();
        switch (car.getCounter()) {
            case 1 : car.setState(new ForwardLightsOn()); break;
            case 3 : // Skip down to the next case
            case 7 : car.setState(new IdleLightsOn()); break;
            case 5 : car.setState(new BackwardsLightsOn()); break;
            default: car.setState(new IdleLightsOff());
        }
    }
}
