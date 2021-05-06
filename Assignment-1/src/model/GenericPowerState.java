package model;

public class GenericPowerState implements HeaterState {

    // Increases the heater's power and if it becomes 3 will set the state to HighestPowerState.
    @Override public void powerUp(Heater heater) {
        heater.offsetPowerBy(1);
        if (heater.getPower() == 3) heater.setState(new HighestPowerState(heater));
    }

    // Lowers the power and if 0 displays an error.
    @Override public void powerDown(Heater heater) {
        if (heater.getPower() == 0) throw new UnsupportedOperationException("Heater is already off.");
        heater.offsetPowerBy(-1);
    }
}
