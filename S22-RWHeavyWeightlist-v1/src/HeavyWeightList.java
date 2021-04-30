import java.util.ArrayList;

public class HeavyWeightList implements ReadWriteList {

    private ArrayList<Integer> list;
    private int secondsToRead;
    private int secondsToWrite;

    public HeavyWeightList(int secondsToRead, int secondsToWrite) {
        list = new ArrayList<>();
        this.secondsToRead = secondsToRead;
        this.secondsToWrite = secondsToWrite;
    }

    @Override
    public void write(int value) {
        list.add(value);
        if (list.size() > 10000) {
            list.remove(0);
        }
        simulateThatItTakesTime(secondsToWrite);
    }

    @Override
    public int read() {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (sum > Integer.MAX_VALUE - list.get(i)) {
                sum = Integer.MAX_VALUE;
            } else {
                sum += list.get(i);
            }
        }
        simulateThatItTakesTime(secondsToRead);
        return sum;
    }

    private void simulateThatItTakesTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            /* empty */
        }
    }
}
