package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Temperature {
    
    private String id;
    private double value;
    private Date date;
    private boolean measuredOutdoor;

    public Temperature(String id, double value, boolean measuredOutdoor) {
        this.id = id;
        this.value = value;
        date = Calendar.getInstance().getTime();
        this.measuredOutdoor = measuredOutdoor;
    }

    public double getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    public Date getTime() {
        return date;
    }

    public boolean measuredOutdoor() {
        return measuredOutdoor;
    }

    @Override public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return String.format("[%s] %s -> %.1f", sdf.format(date), id, value);
    }
}
