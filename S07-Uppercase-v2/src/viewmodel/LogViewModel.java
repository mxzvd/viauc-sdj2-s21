package viewmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class LogViewModel implements PropertyChangeListener {

    private Model model;
    private ObservableList<String> logs;

    public LogViewModel(Model model) {
        this.model = model;
        logs = FXCollections.observableArrayList();
        model.addListener(this);
    }

    public ObservableList<String> getLogs() {
        return logs;
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> logs.add((String) evt.getNewValue()));
    }
}
