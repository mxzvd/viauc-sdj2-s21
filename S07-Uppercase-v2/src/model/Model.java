package model;

public interface Model extends UnnamedPropertyChangeSubject {
    String convert(String source) throws Exception;
    void addLog(String log);
}
