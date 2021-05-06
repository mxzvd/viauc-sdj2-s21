package external;

import mediator.TemperatureModel;

public abstract class Thermometer implements Runnable {
    
    protected TemperatureModel model;
    private String id;
    private boolean running;
    private Thread runningThread;

    public Thermometer(TemperatureModel model, String id) {
        this.model = model;
        this.id = id;
    }

    // Method the implements the actual functionality of the thermometer.
    // Depending on which child class extends this class it will calculate the new temperatures with different formulas and sleep different times.
    @Override public void run() {
        running = true;
        runningThread = Thread.currentThread();
        while (running) {
            try {
                Thread.sleep(querySleepTime() * 1000L);
                updateTemperature();
                model.addTemperature(id, getLastTemp(), measuredOutdoor());
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
    }

    // Method to stop the thermometers threads then the main thread itself ends.
    public void stop() {
        running = false;
        if (runningThread != null) runningThread.interrupt();
    }

    // Methods the the child classes must implement.
    public abstract double getLastTemp();
    public abstract int querySleepTime();
    public abstract void updateTemperature();
    public abstract boolean measuredOutdoor();
}
