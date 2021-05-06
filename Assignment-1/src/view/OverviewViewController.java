package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.DoubleStringConverter;
import viewmodel.OverviewViewModel;

public class OverviewViewController {
    
    private ViewHandler viewHandler;
    private OverviewViewModel viewModel;
    private Region root;

    @FXML private TextField currentHeaterPowerField;
    @FXML private TextField lowCriticalField;
    @FXML private TextField highCriticalField;
    @FXML private Label errorField;
    @FXML private BarChart<String, Number> overviewChart;

    public void init(ViewHandler viewHandler, OverviewViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        // Binds to the instance variables from the view model.
        currentHeaterPowerField.textProperty().bind(viewModel.getCurrentHeaterPowerProperty());
        Bindings.bindBidirectional(lowCriticalField.textProperty(), viewModel.getLowCriticalValueProperty(), new DoubleStringConverter());
        Bindings.bindBidirectional(highCriticalField.textProperty(), viewModel.getHighCriticalValueProperty(), new DoubleStringConverter());
        errorField.textProperty().bind(viewModel.getErrorProperty());
        overviewChart.setData(viewModel.getCharData());
        // Responsible for changing the color from red for errors to yellow for any warnings.
        viewModel.getErrorProperty().addListener((obs, oldVal, newVal) -> errorField.setStyle("-fx-text-fill:" + (newVal.substring(0, newVal.indexOf(" ")).equals("Warning!") ? "#ff9913" : "Red")));
    }

    public void reset() {
        viewModel.clear();
    }

    public Region getRoot() {
        return root;
    }

    // Decrease power button action.
    @FXML private void decreaseHeaterPower() {
        viewModel.decreaseHeaterPower();
    }

    // Decrease power button action.
    @FXML private void increaseHeaterPower() {
        viewModel.increaseHeaterPower();
    }

    // Action for the critical value fields inputs.
    @FXML public void onEnter(ActionEvent actionEvent) {
        viewModel.submitCriticalValues();
        root.requestFocus();
    }

    // Button the opens the temperature logs window.
    @FXML private void showTemperatureLogs() {
        viewHandler.openView("log");
    }
}
