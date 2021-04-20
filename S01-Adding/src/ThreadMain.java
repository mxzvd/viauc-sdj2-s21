import java.util.ArrayList;

public class ThreadMain {
    public static void main(String[] args){

        ArrayList<String> elements = new ArrayList<>();

        Adding addingA = new Adding(elements, "A", 500);
        Adding addingB = new Adding(elements, "B", 2000);
        Adding addingC = new Adding(elements, "C", 1000);
        Thread addingThreadA = new Thread(addingA);
        Thread addingThreadB = new Thread(addingB);
        Thread addingThreadC = new Thread(addingC);

        addingThreadA.setName("Th-A");
        addingThreadB.setName("Th-B");
        addingThreadC.setName("Th-C");

        addingThreadA.start();
        addingThreadB.start();
        addingThreadC.start();
        try {
            addingThreadA.join();
            addingThreadB.join();
            addingThreadC.join();
        } catch (InterruptedException e) {
            // Do nothing.
        }

        System.out.println("list: " + elements + " total= " + elements.size());
    }
}
