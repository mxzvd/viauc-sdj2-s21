package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import viewmodel.LogViewModel;

public class LogViewController {
    
    private ViewHandler viewHandler;
    private LogViewModel viewModel;
    private Region root;

    @FXML private ListView<String> logList;

    public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        // Bind the list with the instance variable from the view model.
        logList.setItems(viewModel.getLogs());
    }

    public void reset() {
        viewModel.clear();
    }

    public Region getRoot() {
        return root;
    }

    // Back button action.
    @FXML private void showOverview() {
        viewHandler.openView("overview");
    }
}
