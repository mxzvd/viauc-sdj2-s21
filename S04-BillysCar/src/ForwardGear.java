public class ForwardGear extends GearState {

    @Override
    public void buttonClick(Car car) {
        car.setGearState(new NeutralGear(-6));
    }
}
