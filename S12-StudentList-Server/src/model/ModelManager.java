package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model {

    private StudentList studentList;
    private PropertyChangeSupport property;

    public ModelManager() {
        this.studentList = new StudentList();
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public Student getStudentByStudyNumber(String studyNumber) throws IllegalArgumentException {
        Student student = studentList.getStudentByNumber(studyNumber);
        property.firePropertyChange("getByNumber", -1, student);
        return student;
    }

    @Override
    public Student getStudentByName(String name) throws IllegalArgumentException {
        Student student = studentList.getStudentByName(name);
        property.firePropertyChange("getByName", -1, student);
        return student;
    }

    @Override
    public void addStudent(Student student) throws IllegalArgumentException {
        studentList.addStudent(student);
        property.firePropertyChange("add", null, student);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
