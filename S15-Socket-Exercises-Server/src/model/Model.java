package model;

import utility.observer.UnnamedPropertyChangeSubject;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject {
    ArrayList<Exercise> getAllExercises();
    ArrayList<Exercise> getExercises(boolean completed);
    Exercise getExercise(String number);
    Exercise removeExercise(String number);
    void addExercise(Exercise exercise);
    Exercise editExercise(String number, Exercise exercise);
}
