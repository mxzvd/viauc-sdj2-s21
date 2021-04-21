package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LogViewModel;

public class LogViewController {

    @FXML private ListView<String> logList;
    @FXML private TextField inputField;

    private ViewHandler viewHandler;
    private LogViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        logList.setItems(viewModel.getLogs());
        Bindings.bindBidirectional(inputField.textProperty(), viewModel.getInputField());
    }

    public Region getRoot() {
        return root;
    }

    public void reset() {
        // Do nothing.
    }

    @FXML private void onEnter() {
        viewModel.onEnter();
    }
}
