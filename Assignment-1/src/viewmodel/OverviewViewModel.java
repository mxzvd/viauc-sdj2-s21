package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import mediator.TemperatureModel;
import model.Temperature;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class OverviewViewModel implements PropertyChangeListener {
    
    private TemperatureModel model;

    private StringProperty currentHeaterPower;
    private DoubleProperty lowCriticalValue;
    private DoubleProperty highCriticalValue;
    private StringProperty errorProperty;
    // Here we store the columns of the bar chart. (Alphabetically ordered by the name of each thermometer.)
    private ObservableList<XYChart.Series<String, Number>> chartData;

    // Constructor to initialize all the instance variables of course.
    // Also adds the class as a listener for any new temperatures and for power changing in the heater.
    public OverviewViewModel(TemperatureModel model) {
        this.model = model;
        currentHeaterPower = new SimpleStringProperty("" + model.getHeaterPower());
        lowCriticalValue = new SimpleDoubleProperty(Double.NaN);
        highCriticalValue = new SimpleDoubleProperty(Double.NaN);
        errorProperty = new SimpleStringProperty();
        chartData = FXCollections.observableArrayList();
        chartData.add(new XYChart.Series<>());

        model.addListener(this);
        model.getHeater().addListener(this);
    }

    public void clear() {
        errorProperty.set(" ");
        currentHeaterPower.set("" + model.getHeaterPower());
    }

    // Getters for properties.
    public StringProperty getCurrentHeaterPowerProperty() {
        return currentHeaterPower;
    }

    public DoubleProperty getLowCriticalValueProperty() {
        return lowCriticalValue;
    }

    public DoubleProperty getHighCriticalValueProperty() {
        return highCriticalValue;
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public ObservableList<XYChart.Series<String, Number>> getCharData() {
        return chartData;
    }

    // Functionality for the increase power button.
    public void increaseHeaterPower() {
        try {
            model.increaseHeaterPower();
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
    }

    // Functionality for the decrease power button.
    public void decreaseHeaterPower() {
        try {
            model.decreaseHeaterPower();
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
    }

    // Verify if the values the user gave in the critical values field are valid numbers.
    public void submitCriticalValues() {
        if (Double.isNaN(lowCriticalValue.doubleValue())) {
            errorProperty.set("Low critical value is not a valid number.");
            return;
        }
        if (Double.isNaN(highCriticalValue.doubleValue())) {
            errorProperty.set("High critical value is not a valid number.");
        }
    }

    // Method to check if a temperature is beyond the critical threshold.
    private void checkWithThresholdBoundaries(Temperature temperature) {
        if (temperature.measuredOutdoor()) return;
        double lowerThreshold = lowCriticalValue.doubleValue();
        double upperThreshold = highCriticalValue.doubleValue();
        if (!Double.isNaN(lowerThreshold) && temperature.getValue() < lowerThreshold) {
            errorProperty.set("Warning! Thermometer " + temperature.getId() + " has passed the lower critical value!");
            return;
        }
        if (!Double.isNaN(upperThreshold) && temperature.getValue() > upperThreshold) {
            errorProperty.set("Warning! Thermometer " + temperature.getId() + " has passed the high critical value!");
        }
    }

    // Gets executed whenever we have a new temperature or heater changes power.
    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {

            // If the event was triggered by the heater we just have to update the heater power field to represent the correct variable.
            if (evt.getPropertyName().equals("heater")) {
                currentHeaterPower.set("" + evt.getNewValue());
                return;
            }

            // If the event was triggered by a new temperature added in the model.
            // Here we just temporarily store the new value of the thermometer, and open the char data.
            Temperature newTemp = (Temperature) evt.getNewValue();
            ObservableList<XYChart.Data<String, Number>> thermometers = chartData.get(0).getData();

            // First we search the existing columns in the chart (thermometers) to see if that thermometer has previously defined any temperature.
            // If we find such a thermometer that has a matching id as the temperature that means we have to update the thermometer in the chart with
            // the new latest values of its measured temperature.

            // Because the thermometer list in the chart is sorted alphabetically we could use binary search for a log(n) time.
            // Iterative implementation of binary search.
            int s = 0, e = thermometers.size() - 1, deviate;
            while (e >= s) {
                if ((deviate = newTemp.getId().compareTo(thermometers.get((s + e) / 2).getXValue())) == 0) {
                    // If we found a matching thermometer we update it's value.
                    thermometers.get((s + e) / 2).setYValue(newTemp.getValue());
                    // After updating we check the new temperature value against the critical thresholds and display a warning if it's the case.
                    checkWithThresholdBoundaries(newTemp);
                    return;
                }
                if (deviate < 0) {
                    e = ((s + e) / 2) - 1;
                } else {
                    s = ((s + e) / 2) + 1;
                }
            }

            // If we could not find any matching thermometers, it means the new temperature value comes from not yet registered
            // thermometer, so we have to register it.
            // Register a new temp and insert it in the chart sorted based on the lexical order of the ids of the thermometers.
            XYChart.Data<String, Number> toAdd = new XYChart.Data<>(newTemp.getId(), newTemp.getValue());
            for (int i = 0; i < thermometers.size(); i++) {
                if (newTemp.getId().compareTo(thermometers.get(i).getXValue()) < 0) {
                    thermometers.add(i, toAdd);
                    checkWithThresholdBoundaries(newTemp);
                    return;
                }
            }
            thermometers.add(thermometers.size(), toAdd);
            checkWithThresholdBoundaries(newTemp);
        });
    }
}
