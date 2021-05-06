public class Miner implements Runnable {

    private DepositTarget deposit;

    public Miner(DepositTarget deposit) {
        this.deposit = deposit;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * (int) (Math.random() * 12) + 5);
            } catch (Exception e) {
                // Do nothing.
            }
            deposit.put(Mine.discoverValuable());
        }
    }
}
