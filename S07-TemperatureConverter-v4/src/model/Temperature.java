package model;

public class Temperature {
    public double fahrenheitToCelsius(double fahrenheit) {
        return 5.0 / 12.0 * (fahrenheit - 32);
    }

    public double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }
}
