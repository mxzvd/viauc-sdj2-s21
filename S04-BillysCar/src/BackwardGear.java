public class BackwardGear extends GearState {

    @Override
    public void buttonClick(Car car) {
        car.setGearState(new NeutralGear(0));
    }
}
