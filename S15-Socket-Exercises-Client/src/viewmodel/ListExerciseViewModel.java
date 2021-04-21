package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Exercise;
import model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.stream.Collectors;

public class ListExerciseViewModel implements PropertyChangeListener {

    private ObservableList<SimpleExerciseViewModel> list;
    private ObjectProperty<SimpleExerciseViewModel> selectedExerciseProperty;
    private StringProperty errorProperty;
    private Model model;
    private ViewState viewState;

    public ListExerciseViewModel(Model model, ViewState viewState) {
        this.model = model;
        this.viewState = viewState;
        list = FXCollections.observableArrayList(model.getAllExercises().stream().map(SimpleExerciseViewModel::new).collect(Collectors.toList()));
        selectedExerciseProperty = new SimpleObjectProperty<>();
        errorProperty = new SimpleStringProperty();
        model.addListener(this);
    }

    public void clear() {
        errorProperty.set("");
    }

    private void loadFromModel() {
        model.getAllExercises().forEach((exercise) -> list.add(new SimpleExerciseViewModel(exercise)));
    }

    public void addEdit() {
        viewState.setNumber(selectedExerciseProperty.get() == null ? "" : selectedExerciseProperty.get().getNumberProperty().get());
        viewState.setRemove(false);
    }

    public boolean remove() {
        if (selectedExerciseProperty.get() == null) {
            errorProperty.set("No exercise selected.");
            return false;
        }
        viewState.setNumber(selectedExerciseProperty.get().getNumberProperty().get());
        viewState.setRemove(true);
        return true;
    }

    public ObservableList<SimpleExerciseViewModel> getAll() {
        return list;
    }

    public void setSelected(SimpleExerciseViewModel exerciseVm) {
        selectedExerciseProperty.set(exerciseVm);
    }

    public StringProperty getErrorProperty() {
        return errorProperty;
    }

    private void removeSimpleExercise(String number) {
        for (SimpleExerciseViewModel exerciseViewModel : list) if (exerciseViewModel.getNumberProperty().get().equals(number)) {
                list.remove(exerciseViewModel);
                return;
        }
    }

    private void addSimpleExercise(Exercise exercise) {
        list.add(new SimpleExerciseViewModel(exercise));
    }

    @Override public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()) {
                case "Remove" : removeSimpleExercise((String) evt.getOldValue()); break;
                case "Add" : addSimpleExercise((Exercise) evt.getNewValue());
            }
        });
    }
}
