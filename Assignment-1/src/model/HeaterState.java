package model;

// Interface used for the implementations of heater's states.
public interface HeaterState {
    void powerUp(Heater heater);
    void powerDown(Heater heater);
}
