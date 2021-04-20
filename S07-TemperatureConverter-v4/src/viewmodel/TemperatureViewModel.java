package viewmodel;

import external.ObservableClock;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TemperatureModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewModel implements PropertyChangeListener {

    private StringProperty inTemp;
    private StringProperty outTemp;
    private StringProperty time;
    private TemperatureModel model;

    public TemperatureViewModel(TemperatureModel model) {
        inTemp = new SimpleStringProperty();
        outTemp = new SimpleStringProperty();
        time = new SimpleStringProperty();
        this.model = model;

        ObservableClock clock = new ObservableClock();
        clock.addListener(this);
        Thread clockThread = new Thread(clock);
        clockThread.setDaemon(true);
        clockThread.start();
    }

    public StringProperty getInTempProperty() {
        return inTemp;
    }

    public StringProperty getOutTempProperty() {
        return outTemp;
    }

    public StringProperty getTimeProperty() {
        return time;
    }

    public void toCelsius() {
        try {
            outTemp.set(Double.toString(model.toCelsius(Double.parseDouble(inTemp.get()))));
            inTemp.set(null);
        } catch (Exception e) {
            outTemp.set("Error in input");
        }
    }

    public void toFahrenheit() {
        try {
            outTemp.set(Double.toString(model.toFahrenheit(Double.parseDouble(inTemp.get()))));
            inTemp.set(null);
        } catch (Exception e) {
            outTemp.set("Error in input");
        }
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> time.set(evt.getNewValue().toString()));
    }
}
