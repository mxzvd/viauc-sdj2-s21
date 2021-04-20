public class Main {
    public static void main(String[] args) {

        ConfessionChair confessionChair = new ConfessionChair();

        Thread churchgoerA = new Thread(new Churchgoer(confessionChair), "ChurchGoerA");
        Thread churchgoerB = new Thread(new Churchgoer(confessionChair), "ChurchGoerB");
        Thread churchgoerC = new Thread(new Churchgoer(confessionChair), "ChurchGoerC");

        churchgoerA.start();
        churchgoerB.start();
        churchgoerC.start();
    }
}
