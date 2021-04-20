package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import view.TemperatureViewController;

public class RunnableClock implements Runnable {

    private DateTimeFormatter timeFormatter;
    private TemperatureViewController tempController;

    public RunnableClock(TemperatureViewController tempController) {
        this.tempController = tempController;
    }

    @Override
    public void run() {
        while (true) {
            LocalTime time = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = time.format(timeFormatter);
            System.out.println(timeString);
            tempController.showTime(timeString);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }
}
