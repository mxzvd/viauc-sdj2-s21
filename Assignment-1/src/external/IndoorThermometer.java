package external;

import mediator.TemperatureModel;
import model.Temperature;

public class IndoorThermometer extends Thermometer {
    
    private String outdoorThermometerId;
    private int distance;
    private double lastTemp;
    private int elapsedSeconds;

    public IndoorThermometer(TemperatureModel model, String id, String outdoorThermometerId, int distance) {
        super(model, id);
        this.outdoorThermometerId = outdoorThermometerId;
        this.distance = 0;
        lastTemp = 0.0;
        elapsedSeconds = 0;
    }

    @Override public double getLastTemp() {
        return lastTemp;
    }

    // Returns a delay time between measurements in between [4; 8] units (seconds).
    // Also stores this time value in elapsedSeconds that's later used in the formula to calculate the new temperature.
    @Override public int querySleepTime() {
        return elapsedSeconds = (int) (Math.random() * 4 + 4);
    }

    // Formula followed from Appendix A.
    // Calculates the indoor temperatures for the thermometers.
    @Override public void updateTemperature() {
        int power = model.getHeaterPower();
        Temperature lastOutdoorTemp = model.getLastInsertedTemperature(outdoorThermometerId);
        // If there are not any previous records of a temperature measured outside by a thermometer with that id the reference temperature outside will just be assumed as 0.
        double outdoorTemp = lastOutdoorTemp == null ? 0.0 : lastOutdoorTemp.getValue();
        double tMax = Math.min(11 * power + 10, 11 * power + 10 + outdoorTemp);
        tMax = Math.max(Math.max(lastTemp, tMax), outdoorTemp);
        double heaterTerm = 0;
        if (power > 0) {
            double den = Math.max((tMax * (20 - 5 * power) * (distance + 5)), 0.1);
            heaterTerm = 30 * elapsedSeconds * Math.abs(tMax - lastTemp) / den;
        }
        double outdoorTerm = (lastTemp - outdoorTemp) * elapsedSeconds / 250.0;
        lastTemp = Math.min(Math.max(lastTemp - outdoorTerm + heaterTerm, outdoorTemp), tMax);
    }

    @Override public boolean measuredOutdoor() {
        return false;
    }
}
