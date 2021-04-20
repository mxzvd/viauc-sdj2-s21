public class NeutralGear extends GearState {

    private int gearOffset;

    public NeutralGear(int gearOffset) {
        this.gearOffset = gearOffset;
    }

    @Override
    public void buttonClick(Car car) {
//        if (++gearOffset == 3) {
//            car.setGearState(new ForwardGear());
//        } else if (gearOffset == -3) {
//            car.setGearState(new BackwardGear());
//        }

        // Or one-liner
        if (++gearOffset % 3 == 0) car.setGearState(gearOffset > 0 ? new ForwardGear() : new BackwardGear());
    }
}
