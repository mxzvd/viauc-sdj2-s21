package model;

public class TemperatureModelManager implements TemperatureModel {

    private Temperature temperature;

    public TemperatureModelManager() {
        this.temperature = new Temperature();
    }

    @Override public double toCelsius(double fahrenheit) {
        return temperature.fahrenheitToCelsius(fahrenheit);
    }

    @Override public double toFahrenheit(double celcius) {
        return temperature.celsiusToFahrenheit(celcius);
    }
}
