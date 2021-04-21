package mediator;

import model.Exercise;
import java.util.ArrayList;

public class ExerciseListPackage {

    private String type;
    private ArrayList<Exercise> exercises;

    public ExerciseListPackage(String type) {
        this.type = type;
        exercises = new ArrayList<>();
    }

    public ExerciseListPackage(String type, ArrayList<Exercise> exercises) {
        this.type = type;
        this.exercises = exercises;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    @Override public String toString() {
        StringBuilder toReturn = new StringBuilder("{Type: " + type + ", Exercises: ");
        for (Exercise exercise : exercises) toReturn.append(exercise);
        return toReturn.append("}").toString();
    }
}
