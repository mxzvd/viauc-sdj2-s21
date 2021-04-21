package model;

import utility.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject {
    String convert(String source) throws Exception;
    void setMessage(String message);
}
