package model;

import utility.observer.UnnamedPropertyChangeSubject;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject {
    ArrayList<Exercise> getAllExercises();
    ArrayList<Exercise> getExercises(boolean completed);
    Exercise getExercise(String number) throws Exception;
    Exercise removeExercise(String number) throws Exception;
    void addExercise(Exercise exercise) throws Exception;
    Exercise editExercise(String number, Exercise exercise) throws Exception;
}
