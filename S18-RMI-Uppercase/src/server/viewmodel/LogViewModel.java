package server.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogViewModel implements PropertyChangeListener {

    private Model model;
    private ObservableList<String> logs;
    private StringProperty inputField;

    public LogViewModel(Model model) {
        this.model = model;
        logs = FXCollections.observableArrayList();
        inputField = new SimpleStringProperty();
        model.addListener(this);
    }

    public ObservableList<String> getLogs() {
        return logs;
    }

    public StringProperty getInputField() {
        return inputField;
    }

    public void onEnter() {
        model.addMessage(inputField.get());
        inputField.set("");
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            if (evt.getPropertyName().equals("log")) logs.add((String) evt.getNewValue());
        });
    }
}
