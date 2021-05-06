package external;

import mediator.TemperatureModel;

public class OutdoorThermometer extends Thermometer {
    
    // Stores the last measured temperature by this same thermometer. And min, max temperature that could be outside.
    private double min;
    private double max;
    private double lastTemp;

    public OutdoorThermometer(TemperatureModel model, String id, double min, double max) {
        super(model, id);
        this.min = min;
        this.max = max;
        lastTemp = 0.0;
    }

    @Override public double getLastTemp() {
        return lastTemp;
    }

    // Returns a delay time between measurements in between [9; 11] units (seconds).
    @Override public int querySleepTime() {
        return (int) (Math.random() * 2 + 9);
    }

    // Formula followed from Appendix B.
    // Generates randomly the temperature outside.
    @Override public void updateTemperature() {
        double left = lastTemp - min;
        double right = max - lastTemp;
        int sign = Math.random() * (left + right) > left ? 1 : -1;
        lastTemp += sign * Math.random();
    }

    @Override public boolean measuredOutdoor() {
        return true;
    }
}
