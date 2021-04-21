import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Log {

    private ArrayList<LogLine> logList;
    private static Map<String, Log> map = new HashMap<>();
    private String filename;

    private Log(String filename) {
        logList = new ArrayList<>();
        this.filename = filename;
    }

    public static Log getInstance(String key) {
        Log instance = map.get(key);
        if (instance == null) {
            synchronized (map) {
                if (map.get(key) == null) {
                    instance = new Log(key);
                    map.put(key, instance);
                }
            }
        }
        return instance;
    }

    public void addLog(String text) {
        LogLine logline = new LogLine(text);
        logList.add(logline);
        addToFile(logline);
        System.out.println(logline.toString());
    }

    public ArrayList<LogLine> getAll() {
        return logList;
    }

    private void addToFile(LogLine log) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
