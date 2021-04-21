package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    public Student getStudentByStudyNumber(String studyNumber) throws Exception;
    public Student getStudentByName(String name) throws Exception;
    public void addStudent(Student student) throws Exception;
}
