package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediator.TemperatureModel;
import model.Temperature;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// Represents a listener for any new temperatures added to the model.
public class LogViewModel implements PropertyChangeListener {
    
    private TemperatureModel model;
    private ObservableList<String> logs;

    public LogViewModel(TemperatureModel model) {
        this.model = model;
        logs = FXCollections.observableArrayList();
        model.addListener(this);
    }

    public void clear() {
        // Do nothing.
    }

    public ObservableList<String> getLogs() {
        return logs;
    }

    // Updates the log list whenever a new temperature gets added, keeps a record of the last 20 temperatures.
    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Temperature oldTemp = (Temperature) evt.getOldValue();
            Temperature newTemp = (Temperature) evt.getNewValue();
            if (logs.size() == 20) logs.remove(0);
            logs.add(newTemp + " | Old: " + (oldTemp != null ? oldTemp : "First time measured."));
        });
    }
}
