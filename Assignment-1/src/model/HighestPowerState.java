package model;

public class HighestPowerState implements HeaterState {
    
    private Thread timeout;
    private boolean hasEnded;

    // We start the 40s timeout upon entering the 3rd power of the heater.
    public HighestPowerState(Heater heater) {
        hasEnded = false;
        timeout = new Thread(() -> {
            try {
                Thread.sleep(40000);
                completeTimeout(heater);
            } catch (InterruptedException e) {
                System.out.println("Heater was interrupted while timing out.");
            }
        });
        timeout.start();
    }

    // When the timeout is complete we switch back to the GenericPowerState with a power of 2.
    private void completeTimeout(Heater heater) {
        if (!hasEnded) {
            heater.offsetPowerBy(-1);
            System.out.println("Heater timed out and was set back to power 2.");
            heater.setState(new GenericPowerState());
            hasEnded = true;
        }
    }

    // Just displays an error.
    @Override public void powerUp(Heater heater) {
        throw new UnsupportedOperationException("The heater is already at maximum power.");
    }

    // Lowers the heater power and terminates the 40s timeout if it's active.
    @Override public void powerDown(Heater heater) {
        if (!hasEnded) {
            timeout.interrupt();
            heater.offsetPowerBy(-1);
            heater.setState(new GenericPowerState());
            hasEnded = true;
        }
    }
}
