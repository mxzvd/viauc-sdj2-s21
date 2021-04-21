package mediator;

import model.Exercise;

public class ExercisePackage {

    private Exercise exercise;
    private String type;
    private String number;
    private String error;

    public ExercisePackage(String type, Exercise exercise, String number) {
        this.exercise = exercise;
        this.type = type;
        this.number = number;
        error = "";
    }

    public ExercisePackage(String type, String error) {
        exercise = null;
        this.type = type;
        number = "";
        this.error = error;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getError() {
        return error;
    }

    @Override public boolean equals(Object obj) {
        if (!(obj instanceof ExercisePackage)) return false;
        ExercisePackage other = (ExercisePackage) obj;
        return exercise.equals(other.exercise) && type.equals(other.type) && number.equals(other.number) && error.equals(other.error);
    }

    @Override public String toString() {
        return "{Exercise: " + exercise + ", type: " + type + ", number= " + number + ", error= " + error + "}";
    }
}
