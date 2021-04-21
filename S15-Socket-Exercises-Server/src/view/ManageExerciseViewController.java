package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import utility.IntStringConverter;
import viewmodel.ManageExerciseViewModel;

public class ManageExerciseViewController {

    @FXML Label headerLabel;
    @FXML TextField sessionField;
    @FXML TextField numberField;
    @FXML TextField topicField;
    @FXML RadioButton completedRadiobutton;
    @FXML Label errorLabel;
    @FXML Button submitButton;

    private Region root;
    private ManageExerciseViewModel viewModel;
    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler, ManageExerciseViewModel manageExerciseViewModel, Region root) {
        this.viewHandler = viewHandler;
        viewModel = manageExerciseViewModel;
        this.root = root;

        headerLabel.textProperty().bind(viewModel.getHeaderProperty());
        Bindings.bindBidirectional(sessionField.textProperty(), viewModel.getSessionProperty(), new IntStringConverter());
        Bindings.bindBidirectional(numberField.textProperty(), viewModel.getNumberProperty(), new IntStringConverter());
        topicField.textProperty().bindBidirectional(viewModel.getTopicProperty());
        completedRadiobutton.selectedProperty().bindBidirectional(viewModel.getCompletedProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        viewModel.getEditableProperty().addListener((obs, oldVal, newVal) -> {
            sessionField.setDisable(!newVal);
            numberField.setDisable(!newVal);
            topicField.setDisable(!newVal);
            completedRadiobutton.setDisable(!newVal);
        });
        reset();
    }

    public void reset() {
        viewModel.reset();
    }

    public Region getRoot() {
        return root;
    }

    @FXML private void submitButton() {
        if (viewModel.accept()) viewHandler.openView("list");
    }

    @FXML private void cancelButton() {
        viewHandler.openView("list");
    }

    @FXML private void onEnter(ActionEvent actionEvent) {
        submitButton();
    }
}
