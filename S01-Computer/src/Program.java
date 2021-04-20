public class Program implements Runnable {

    private String program;
    private long maxFrequency;
    private String action;
    private int count;
    private static long RUNTIME = 100000;

    public Program(String program, String action, int count) {
        this.program = program;
        maxFrequency = 0;
        this.action = action;
        this.count = count;
    }

    private void normalOperation() {
        try {
            Thread.sleep(RUNTIME / 30);
        } catch (InterruptedException e) {
            // Do nothing.
        }
    }

    @Override public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(program + " wants to " + action);
            normalOperation();
        }
    }
}
