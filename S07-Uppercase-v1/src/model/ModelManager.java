package model;

public class ModelManager implements Model {

    private Converter converter;

    public ModelManager() {
        this.converter = new Converter();
    }

    @Override public synchronized String convert(String source) {
        String reply = converter.toUpperCase(source);
        addLog("Converting: " + source);
        return reply;
    }

    @Override public synchronized void addLog(String log) {
        String logValue = converter.getLogSize() + ": " + log;
        converter.addLog(logValue);
    }
}
