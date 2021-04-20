package temperature.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import temperature.viewmodel.TemperatureViewModel;

public class TemperatureViewController {

    @FXML private Label outputLabel;
    @FXML private TextField filterField;
    @FXML private Label filterLabel;

    private ViewHandler viewHandler;
    private TemperatureViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, TemperatureViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        filterField.textProperty().bindBidirectional(viewModel.getFilterValueProperty());
        filterLabel.textProperty().bind(viewModel.getThermometerProperty());
        outputLabel.textProperty().bind(viewModel.getTemperatureProperty());
    }

    public void reset() {
        // Empty.
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void updateButtonPressed() {
        viewModel.getValue();
    }

    @FXML private void onFilter() {
        viewModel.updateThermometerId();
    }
}
