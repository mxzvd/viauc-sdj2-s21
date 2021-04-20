public class Car {

    private GearState gearState;
    private LightsState lightsState;

    public Car() {
        gearState = new NeutralGear(2);
        lightsState = new LightsOff();
    }

    public void setGearState(GearState state) {
        gearState = state;
    }

    public void setLightsState(LightsState state) {
        lightsState = state;
    }

    public void buttonClick() {
        gearState.buttonClick(this);
        lightsState.buttonClick(this);
    }

    public String status() {
        return "Gear -> " + gearState.status() + " and lights -> " + lightsState.status();
    }
}
