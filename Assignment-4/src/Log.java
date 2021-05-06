import java.util.ArrayList;

public class Log {

    private static Log instance;
    private static Object lock = new Object();
    private ArrayList<String> logList;

    private Log() {
        logList = new ArrayList<>();
    }

    public static Log getInstance() {
        if (instance == null) synchronized (lock) {
            if (instance == null) instance = new Log();
        }
        return instance;
    }

    public void addLog(String text) {
        logList.add(text);
        System.out.println("[Logged:] " + text);
    }

    public ArrayList<String> getAll() {
        return logList;
    }
}
