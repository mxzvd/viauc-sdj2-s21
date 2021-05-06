import utility.collection.ArrayList;

public class ValuableTransporter implements Runnable {

    private DepositTarget deposit;
    private ArrayList<Valuable> transportedValuable;
    private TreasureRoomDoor guard;

    public ValuableTransporter(DepositTarget deposit, TreasureRoomDoor guard) {
        this.deposit = deposit;
        transportedValuable = new ArrayList<>();
        this.guard = guard;
    }

    @Override
    public void run() {
        while (true) {
            int valuableToCarryWorth = (int) (Math.random() * 200) + 50;
            int currentWorth = 0;
            do {
                Valuable toTransport = deposit.take();
                currentWorth += toTransport.getItem().getValue();
                transportedValuable.add(toTransport);
                try {
                    Thread.sleep(1000 * (int) (Math.random() * 4) + 1);
                } catch (Exception e) {
                    // Do nothing.
                }
            } while (currentWorth < valuableToCarryWorth);
            Log.getInstance().addLog("Valuable transporting is now carrying goods in worth of " + currentWorth);
            TreasureReadWrite treasureRoom = guard.acquireWriteAccess();
            for (int i = 0; i < transportedValuable.size(); i++) {
                treasureRoom.add(transportedValuable.remove(0));
                try {
                    Thread.sleep(1000 * (int) (Math.random() * 4) + 1);
                } catch (Exception e) {
                    // Do nothing.
                }
            }
            guard.releaseWriteAccess();
        }
    }
}
