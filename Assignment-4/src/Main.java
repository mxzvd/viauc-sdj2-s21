public class Main {
    public static void main(String[] args) {

        Deposit deposit = new Deposit(30);
        TreasureRoom treasureRoom = new TreasureRoom();
        TreasureRoomDoor guard = new Guardsman(treasureRoom);

        Thread[] miners = new Thread[7];
        for (int i = 0; i < miners.length; i++) {
            (miners[i] = new Thread(new Miner(deposit), "Miner" + i)).start();
        }

        Thread[] transporters = new Thread[3];
        for (int i = 0; i < transporters.length; i++) {
            (transporters[i] = new Thread(new ValuableTransporter(deposit, guard), "Transporters" + i)).start();
        }

        Thread[] accountants = new Thread[2];
        for (int i = 0; i < accountants.length; i++) {
            (accountants[i] = new Thread(new Accountant(guard), "Accountants" + i)).start();
        }

        new Thread(new King(guard), "King").start();
    }
}
