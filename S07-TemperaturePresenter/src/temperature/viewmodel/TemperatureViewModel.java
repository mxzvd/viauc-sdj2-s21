package temperature.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import temperature.mediator.TemperatureModel;
import temperature.model.Temperature;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener {

    private StringProperty temperature;
    private StringProperty thermometerId;
    private StringProperty filterValue;
    private TemperatureModel model;

    public TemperatureViewModel(TemperatureModel model) {
        temperature = new SimpleStringProperty();
        thermometerId = new SimpleStringProperty();
        filterValue = new SimpleStringProperty();
        this.model = model;
        model.addListener("temperature", this);
    }

    public void getValue() {
        String id = thermometerId.get();
        Temperature lastTemp = model.getLastInsertedTemperature(id == null || id.isEmpty() || id.equalsIgnoreCase("All") ? null : id);
        temperature.set(lastTemp != null ? lastTemp.toString() : "No data");
    }

    private void setValue(Temperature t) {
        temperature.set(t.toString());
    }

    public void updateThermometerId() {
        String input = filterValue.get();
        thermometerId.set(input == null || input.isEmpty() || input.equalsIgnoreCase("All") ? "All" : input);
        filterValue.set(null);
        getValue();
    }

    public StringProperty getTemperatureProperty() {
        return temperature;
    }

    public StringProperty getThermometerProperty() {
        return thermometerId;
    }

    public StringProperty getFilterValueProperty() {
        return filterValue;
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        // None of the previous versions nor the current one state how the gui should be automatically updated
        // To update the gui with the latest value ignoring the filter
        // Platform.runLater(() -> setValue((Temperature) evt.getNewValue()));

        // To update the gui with the latest value considering the filter
        Platform.runLater(this::getValue);
    }
}
