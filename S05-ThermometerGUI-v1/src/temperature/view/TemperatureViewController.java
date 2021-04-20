package temperature.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import temperature.mediator.TemperatureModel;
import temperature.model.Temperature;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewController implements PropertyChangeListener {

    @FXML private Label outputLabel;
    @FXML private TextField filterField;
    @FXML private Label filterLabel;

    private ViewHandler viewHandler;
    private TemperatureModel model;
    private Region root;
    private String thermometerId;

    public TemperatureViewController() {
        // Empty.
    }

    public void init(ViewHandler viewHandler, TemperatureModel model, Region root) {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;
        thermometerId = null;
        model.addListener("temperature", this);
    }

    public void reset() {
        // Empty.
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void updateButtonPressed() {
        Temperature t = model.getLastInsertedTemperature(thermometerId);
        if (t != null) {
            outputLabel.setText(t.toString());
        } else {
            outputLabel.setText("No data");
        }
    }

    @FXML private void onFilter() {
        String input = filterField.getText();
        if (input == null || input.isEmpty() || input.equalsIgnoreCase("All")) {
            input = null;
        }
        thermometerId = input;
        if (thermometerId == null) {
            filterLabel.setText("All");
        } else {
            filterLabel.setText(thermometerId);
        }
        filterField.setText(null);
        updateButtonPressed();
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(this::updateButtonPressed);
    }
}
