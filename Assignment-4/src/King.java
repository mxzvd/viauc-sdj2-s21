import utility.collection.ArrayList;

public class King implements Runnable {

    private TreasureRoomDoor guard;

    public King(TreasureRoomDoor guard) {
        this.guard = guard;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20000);
            } catch (Exception e) {
                // Do nothing.
            }
            ArrayList<Valuable> partyValuables = new ArrayList<>();
            int budgetForParty = (int) (Math.random() * 150) + 50;
            int currentWorth = 0;
            TreasureReadWrite treasureRoom = guard.acquireWriteAccess();
            if (treasureRoom.look().size() > 0) {
                do {
                    Valuable forParty = treasureRoom.retrieve();
                    if (forParty == null && currentWorth < budgetForParty) {
                        for (int i = 0; i < partyValuables.size(); i++) treasureRoom.add(partyValuables.remove(0));
                        break;
                    }
                    currentWorth += forParty.getItem().getValue();
                    partyValuables.add(forParty);
                    try {
                        Thread.sleep(1000 * (int) (Math.random() * 4) + 1);
                    } catch (Exception e) {
                        // Do nothing.
                    }
                } while (currentWorth < budgetForParty);
            }
            guard.releaseWriteAccess();
            if (currentWorth >= budgetForParty) {
                Log.getInstance().addLog("King has just had a party. He spent " + currentWorth);
                for (int i = 0; i < partyValuables.size(); i++) partyValuables.remove(0);
            }
        }
    }
}
