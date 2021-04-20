package view;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.converter.NumberStringConverter;
import viewmodel.AuctionItemViewModel;


public class AuctionItemViewController {

    @FXML Label itemLabel;
    @FXML Label timeLabel;
    @FXML Label currentBidTitle;
    @FXML Label currentBidLabel;
    @FXML Label currentBidderLabel;
    @FXML TextField bidField;
    @FXML Label errorLabel;
    @FXML private Button bidButton;

    private Region root;
    private AuctionItemViewModel viewModel;
    private ViewHandler viewHandler;

    public AuctionItemViewController() {
        // Empty.
    }

    public void init(ViewHandler viewHandler, AuctionItemViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;

        itemLabel.textProperty().bind(viewModel.getItemProperty());
        timeLabel.textProperty().bind(viewModel.getTimeProperty());
        currentBidTitle.textProperty().bind(viewModel.getCurrentBidTitleProperty());
        Bindings.bindBidirectional(currentBidLabel.textProperty(), viewModel.getCurrentBidProperty(), new NumberStringConverter());
        currentBidderLabel.textProperty().bind(viewModel.getCurrentBidderProperty());
        bidField.textProperty().bindBidirectional(viewModel.getBidProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());

        viewModel.getTimeProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.equals("00:00:00")) {
                timeLabel.setStyle("-fx-background-color:RED");
                bidButton.setDisable(true);
            }
        });
    }

    public void reset() {
        viewModel.clear();
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void bidOnAction() {
        viewModel.bid();
    }
}
