import utility.collection.ArrayList;

public class Accountant implements Runnable {

    private TreasureRoomDoor guard;

    public Accountant(TreasureRoomDoor guard) {
        this.guard = guard;
    }

    @Override
    public void run() {
        while (true) {
            TreasureRead treasureRoom = guard.acquireReadAccess();
            ArrayList<Valuable> treasure = treasureRoom.look();
            long sum = 0;
            for (int i = 0; i < treasure.size(); i++) {
                sum += treasure.get(i).getItem().getValue();
                try {
                    Thread.sleep(1000 * (int) (Math.random() * 10) + 1);
                } catch (Exception e) {
                    // Do nothing.
                }
            }
            Log.getInstance().addLog("Accountant evaluated the cost of all treasures to be: " + sum);
            guard.releaseReadAccess();
            try {
                Thread.sleep(1000 * (int) (Math.random() * 20) + 10);
            } catch (Exception e) {
                // Do nothing.
            }
        }
    }
}
