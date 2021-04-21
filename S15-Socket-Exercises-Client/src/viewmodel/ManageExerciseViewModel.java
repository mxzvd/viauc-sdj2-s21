package viewmodel;

import javafx.beans.property.*;
import model.Exercise;
import model.Model;

public class ManageExerciseViewModel {

    private StringProperty errorProperty;
    private StringProperty headerProperty;
    private ObjectProperty<Boolean> completedProperty;
    private StringProperty topicProperty;
    private IntegerProperty numberProperty;
    private IntegerProperty sessionProperty;
    private ObjectProperty<Boolean> editableProperty;
    private Model model;
    private ViewState viewState;

    public ManageExerciseViewModel(Model model, ViewState viewState) {
        errorProperty = new SimpleStringProperty();
        headerProperty = new SimpleStringProperty();
        completedProperty = new SimpleObjectProperty<>();
        topicProperty = new SimpleStringProperty();
        numberProperty = new SimpleIntegerProperty();
        sessionProperty = new SimpleIntegerProperty();
        editableProperty = new SimpleObjectProperty<>();
        this.model = model;
        this.viewState = viewState;
    }

    public void reset() {
        errorProperty.set("");
        editableProperty.set(!viewState.isRemove());

        if (viewState.getNumber().isEmpty()) {
            headerProperty.set("Add exercise");
            sessionProperty.set(0);
            numberProperty.set(0);
            completedProperty.set(false);
            topicProperty.set("");
            return;
        }
        try {
            headerProperty.set(viewState.isRemove() ? "Remove exercise" : "Edit exercise");
            sessionProperty.set(model.getExercise(viewState.getNumber()).getSessionNumber());
            numberProperty.set(model.getExercise(viewState.getNumber()).getExerciseNumber());
            completedProperty.set(model.getExercise(viewState.getNumber()).isCompleted());
            topicProperty.set(model.getExercise(viewState.getNumber()).getTopic());
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
        }
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    public StringProperty getHeaderProperty() {
        return headerProperty;
    }

    public ObjectProperty<Boolean> getCompletedProperty() {
        return completedProperty;
    }

    public StringProperty getTopicProperty() {
        return topicProperty;
    }

    public IntegerProperty getNumberProperty() {
        return numberProperty;
    }

    public IntegerProperty getSessionProperty() {
        return sessionProperty;
    }

    public ObjectProperty<Boolean> getEditableProperty() {
        return editableProperty;
    }

    private Exercise createExerciseObject() {
        Exercise toReturn;
        try {
            toReturn = new Exercise(sessionProperty.get(), numberProperty.get(), topicProperty.get());
            toReturn.setCompleted(completedProperty.get());
            try {
                model.addExercise(toReturn);
                model.removeExercise(viewState.getNumber());
            } catch (Exception e) {
                if (viewState.getNumber().equals(toReturn.getNumber())) {
                    try {
                        model.removeExercise(viewState.getNumber());
                        model.addExercise(toReturn);
                    } catch (Exception e2) {
                        throw new Exception(e2.getMessage());
                    }
                } else {
                    throw new Exception(e.getMessage());
                }
            }
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
            return null;
        }
        return toReturn;
    }

    public boolean accept() {
        try {
            return null != (viewState.isRemove() ? model.removeExercise(viewState.getNumber()) : createExerciseObject());
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
            return false;
        }
    }
}
