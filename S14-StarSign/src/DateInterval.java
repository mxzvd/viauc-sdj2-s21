public class DateInterval {

    private int startDay;
    private int startMonth;
    private int endDay;
    private int endMonth;

    public DateInterval(int startDay, int startMonth, int endDay, int endMonth) {
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.endDay = endDay;
        this.endMonth = endMonth;
    }

    public String getStartDate() {
        return "" + startDay + "/" + startMonth;
    }

    public String getEndDate() {
        return "" + endDay + "/" + endMonth;
    }

    @Override public String toString() {
        return "Start: " + getStartDate() + ", End: " + getEndDate();
    }
}
