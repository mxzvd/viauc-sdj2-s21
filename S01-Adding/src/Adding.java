import java.util.ArrayList;

public class Adding implements Runnable {

    private String id;
    private long sleepTime;
    private ArrayList<String> list;

    public Adding(ArrayList<String> list, String id, long sleepTime) {
        this.list = list;
        this.id = id;
        this.sleepTime = sleepTime;
    }

    @Override public void run() {
        for (int i = 1; i <= 5; i++) {
            list.add("#" + i + " " + id);
            System.out.println(Thread.currentThread().getName() + " -> #" + i + " -> added: " + "| op" + i + " " + id + " |" + ", list: " + list + ", total= " + list.size());
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
