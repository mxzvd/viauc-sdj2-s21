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
        headerProperty.set(viewState.isRemove() ? "Remove exercise" : "Edit exercise");
        sessionProperty.set(model.getExercise(viewState.getNumber()).getSessionNumber());
        numberProperty.set(model.getExercise(viewState.getNumber()).getExerciseNumber());
        completedProperty.set(model.getExercise(viewState.getNumber()).isCompleted());
        topicProperty.set(model.getExercise(viewState.getNumber()).getTopic());
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
            boolean isExerciseDefined = toReturn.equals(model.getExercise(viewState.getNumber()));
            if (!viewState.getNumber().isEmpty() && !isExerciseDefined && model.removeExercise(viewState.getNumber()) == null) throw new Exception("Error editing the exercise");
            if (viewState.getNumber().isEmpty() || !isExerciseDefined) model.addExercise(toReturn);
        } catch (Exception e) {
            errorProperty.set(e.getMessage());
            return null;
        }
        return toReturn;
    }

    public boolean accept() {
        return null != (viewState.isRemove() ? model.removeExercise(viewState.getNumber()) : createExerciseObject());
    }
}
