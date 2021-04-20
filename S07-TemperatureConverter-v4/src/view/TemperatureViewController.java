package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.TemperatureViewModel;

public class TemperatureViewController {

    @FXML private TextField textInput;
    @FXML private Label labelTimer;
    @FXML private Label labelOutput;

    private TemperatureViewModel model;
    private Region root;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, TemperatureViewModel model, Region root) {
        this.viewHandler = viewHandler;
        this.model = model;
        this.root = root;

        textInput.textProperty().bindBidirectional(model.getInTempProperty());
        labelOutput.textProperty().bind(model.getOutTempProperty());
        labelTimer.textProperty().bind(model.getTimeProperty());
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void toCelsius() {
        model.toCelsius();
    }

    @FXML private void toFahrenheit() {
        model.toFahrenheit();
    }
}
