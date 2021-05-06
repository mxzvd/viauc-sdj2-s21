package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class AuthorizationViewModel {

    private Model model;
    private StringProperty messageLabel;
    private StringProperty usernameField;
    private StringProperty passwordField;

    public AuthorizationViewModel(Model model) {
        this.model = model;
        messageLabel = new SimpleStringProperty("Login or Register");
        usernameField = new SimpleStringProperty();
        passwordField = new SimpleStringProperty();
    }

    public void reset() {
        messageLabel.set("Login or Register");
        usernameField.set("");
        passwordField.set("");
    }

    public StringProperty getMessageLabelProperty() {
        return messageLabel;
    }

    public StringProperty getUsernameFieldProperty() {
        return usernameField;
    }

    public StringProperty getPasswordFieldProperty() {
        return passwordField;
    }

    public boolean register() {
        try {
            model.register(usernameField.get(), passwordField.get());
        } catch (Exception e) {
            messageLabel.set("!" + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean login() {
        try {
            model.login(usernameField.get(), passwordField.get());
        } catch (Exception e) {
            messageLabel.set("!" + e.getMessage());
            return false;
        }
        return true;
    }
}
