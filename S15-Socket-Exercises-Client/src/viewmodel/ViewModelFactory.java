package viewmodel;

import model.Model;

public class ViewModelFactory {

    private ListExerciseViewModel listExerciseViewModel;
    private ManageExerciseViewModel manageExerciseViewModel;
    private ViewState viewState;

    public ViewModelFactory(Model model) {
        viewState = new ViewState();
        listExerciseViewModel = new ListExerciseViewModel(model, viewState);
        manageExerciseViewModel = new ManageExerciseViewModel(model, viewState);
    }

    public ListExerciseViewModel getListExerciseViewModel() {
        return listExerciseViewModel;
    }

    public ManageExerciseViewModel getManageExerciseViewModel() {
        return manageExerciseViewModel;
    }
}
