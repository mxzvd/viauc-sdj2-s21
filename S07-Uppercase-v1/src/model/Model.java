package model;

public interface Model {
    String convert(String source) throws Exception;
    void addLog(String log);
}
