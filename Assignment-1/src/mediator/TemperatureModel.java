package mediator;

import model.Heater;
import model.Temperature;
import utility.UnnamedPropertyChangeSubject;

// Interface for implementing the model manager.
public interface TemperatureModel extends UnnamedPropertyChangeSubject {
    void addTemperature(String id, double temperature, boolean measuredOutdoor);
    Temperature getLastInsertedTemperature();
    Temperature getLastInsertedTemperature(String id);
    Heater getHeater();
    int getHeaterPower();
    void increaseHeaterPower();
    void decreaseHeaterPower();
}
