package client.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import client.viewmodel.AuthorizationViewModel;
import java.io.IOException;

public class AuthorizationViewController extends ViewController {

    private ViewHandler viewHandler;
    private AuthorizationViewModel viewModel;
    
    @FXML private Label messageLabel;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @Override
    protected void init() {
        viewHandler = getViewHandler();
        viewModel = getViewModelFactory().getAuthorizationViewModel();

        messageLabel.textProperty().bind(viewModel.getMessageLabelProperty());
        usernameField.textProperty().bindBidirectional(viewModel.getUsernameFieldProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getPasswordFieldProperty());
        viewModel.getMessageLabelProperty().addListener((obs, oldVal, newVal) -> messageLabel.setStyle("-fx-text-fill:" + (newVal.charAt(0) == '!' ? "Red" : "#000000")));
    }

    public void reset() {
        viewModel.reset();
    }

    @FXML private void register() {
        if (viewModel.register()) login();
    }

    @FXML private void login() {
        try {
            if (viewModel.login()) viewHandler.openView(View.APPLICATION);
        } catch (IOException e) {
            messageLabel.setText(e.getMessage());
        }
    }
}
