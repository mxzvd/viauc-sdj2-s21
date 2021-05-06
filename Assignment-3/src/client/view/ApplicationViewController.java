package client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import client.viewmodel.ApplicationViewModel;

public class ApplicationViewController extends ViewController {

    private ViewHandler viewHandler;
    private ApplicationViewModel viewModel;
    
    @FXML private TextField createChatField;
    @FXML private TextField joinChatField;
    @FXML private TextField messageField;
    @FXML private Label errorLabel;
    @FXML private ListView<String> chatList;
    @FXML private ListView<String> messageList;

    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getApplicationViewModel();

        createChatField.textProperty().bindBidirectional(viewModel.getCreateChatFieldProperty());
        joinChatField.textProperty().bindBidirectional(viewModel.getJoinChatFieldProperty());
        messageField.textProperty().bindBidirectional(viewModel.getMessageFieldProperty());
        errorLabel.textProperty().bindBidirectional(viewModel.getErrorLabel());
        chatList.setItems(viewModel.getChatList());
        messageList.setItems(viewModel.getSelectedChatMessages());
    }

    public void reset() {
        viewModel.reset();
    }

    @FXML private void createChat() {
        viewModel.createChat();
    }

    @FXML private void joinChat() {
        viewModel.joinChat();
    }

    @FXML private void sendMessage() {
        viewModel.sendMessage();
    }

    @FXML private void selectChat() {
        viewModel.getSelectedChatProperty().set(chatList.getSelectionModel().getSelectedItem());
        viewModel.refreshMessages("");
    }
}
