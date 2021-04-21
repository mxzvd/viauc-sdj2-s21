public class Main {
    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                Log.getInstance("f1").addLog("t1: " + java.time.LocalDateTime.now().toString());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Log.getInstance("f2").addLog("t2: " + java.time.LocalDateTime.now().toString());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Log.getInstance("f1").addLog("t3: " + java.time.LocalDateTime.now().toString());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Log.getInstance("f2").addLog("t4: " + java.time.LocalDateTime.now().toString());
            }
        }).start();
    }
}
