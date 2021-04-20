public class Main {
    public static void main(String[] args) {

        Counter counter = new Counter(1000, 9000);

        CounterIncrementer cInc1 = new CounterIncrementer(counter, 5500);
        CounterIncrementer cInc2 = new CounterIncrementer(counter, 2000);

        CounterDecrementer cDec1 = new CounterDecrementer(counter, 5000);
        CounterDecrementer cDec2 = new CounterDecrementer(counter, 500);

        Thread inc1 = new Thread(cInc1, "Inc1");
        Thread inc2 = new Thread(cInc2, "Inc2");
        Thread dec1 = new Thread(cDec1, "Dec1");
        Thread dec2 = new Thread(cDec2, "Dec2");

        inc1.start();
        inc2.start();
        dec1.start();
        dec2.start();

        try {
            inc1.join();
            inc2.join();
            dec1.join();
            dec2.join();
        }
        catch (InterruptedException e) {
            // Do nothing.
        }

        System.out.println("Final value: " + counter.getValue());
    }
}
