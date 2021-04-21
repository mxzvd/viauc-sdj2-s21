public class LogLine {

    private String text;
    private DateTime dateTime;

    public LogLine(String text) {
        this.text = text;
        dateTime = new DateTime();
    }

    public String getText() {
        return text;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    @Override public String toString() {
        return "{Text: " + text + ", dateTime: " + dateTime + "}";
    }
}
