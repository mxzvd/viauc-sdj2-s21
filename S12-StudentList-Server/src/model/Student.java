package model;

public class Student {

    private String name;
    private String studyNumber;

    public Student(String name, String studyNumber) {
        if (studyNumber == null) {
            throw new IllegalArgumentException("A null study number: " + studyNumber);
        }
        setName(name);
        this.studyNumber = studyNumber;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("A null Name");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStudyNumber() {
        return studyNumber;
    }

    @Override
    public String toString() {
        return name + " (" + studyNumber + ')';
    }
}
