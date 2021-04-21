package model;

import java.util.ArrayList;

public class StudentList {

    private ArrayList<Student> students;

    public StudentList() {
        this.students = new ArrayList<>();
    }

    public int getSize() {
        return students.size();
    }

    public Student getStudent(int index) {
        return students.get(index);
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("A null Student");
        }
        if (getStudentByNumber(student.getStudyNumber()) != null) {
            throw new IllegalArgumentException(
                    "Student with study number " + student.getStudyNumber() + " already exist");
        }
        students.add(student);
    }

    public Student getStudentByNumber(String studyNumber) {
        if (studyNumber != null) {
            for (int i = 0; i < students.size(); i++) {
                if (studyNumber.equalsIgnoreCase(students.get(i).getStudyNumber())) {
                    return students.get(i);
                }
            }
        }
        return null;
    }

    public Student getStudentByName(String name) {
        if (name != null) {
            for (int i = 0; i < students.size(); i++) {
                if (name.equalsIgnoreCase(students.get(i).getName())) {
                    return students.get(i);
                }
            }
        }
        return null;
    }
}
