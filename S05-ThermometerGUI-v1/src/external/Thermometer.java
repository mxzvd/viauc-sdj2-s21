package external;

import temperature.mediator.TemperatureModel;

public class Thermometer implements Runnable {

    private TemperatureModel model;
    private double t;
    private double t0;
    private int p;
    private String id;
    private int d;
    private boolean running;
    private Thread runningThread;

    public Thermometer(TemperatureModel model, String id, double t, int d) {
        this.model = model;
        this.id = id;
        this.t = t;
        this.d = d;
        this.p = 2;
        this.t0 = 0.0;
    }

    @Override
    public void run() {
        running = true;
        runningThread = Thread.currentThread();
        while (running) {
            try {
                int seconds = (int) (Math.random() * 4 + 4);
                Thread.sleep(seconds * 1000);
                t = temperature(t, p, d, t0, seconds);
                System.out.printf(id + " %.1f\n", t);
                model.addTemperature(id, t);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }

    public void stop() {
        running = false;
        if (runningThread != null) {
            runningThread.interrupt();
        }
    }

    private double temperature(double t, int p, int d, double t0, int s) {
        double tMax = Math.min(11 * p + 10, 11 * p + 10 + t0);
        tMax = Math.max(Math.max(t, tMax), t0);
        double heaterTerm = 0;
        if (p > 0) {
            double den = Math.max((tMax * (20 - 5 * p) * (d + 5)), 0.1);
            heaterTerm = 30 * s * Math.abs(tMax - t) / den;
        }
        double outdoorTerm = (t - t0) * s / 250.0;
        t = Math.min(Math.max(t - outdoorTerm + heaterTerm, t0), tMax);
        return t;
    }
}
