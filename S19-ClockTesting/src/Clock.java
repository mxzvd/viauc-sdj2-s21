/**
 * A clock represented with hour, minute and second and presented in a 24 hour
 * format with the smallest value 00:00:00 and largest value 23:59:59 <br>
 * <br>
 * Note: The implementation contains errors and are created solely with the
 * purpose to be used in JUnit testing
 *
 * @author Steffen Vissing Andersen
 * @version 0.1, 5/10/2019
 */
public class Clock {

    private int hour;
    private int minute;
    private int second;

    /**
     * Constructor setting the clock to the values given
     *
     * @param hour   hours (0-23)
     * @param minute minute (0-59)
     * @param second second (0-59)
     * @throws IllegalArgumentException if hour is not in the interval [0-23],
     *                                  minute not in the interval [0-59] or second
     *                                  not in the interval [0-59]
     */
    public Clock(int hour, int minute, int second) {
        set(hour, minute, second);
    }

    /**
     * Constructor setting the clock according to the total seconds given as
     * argument. The clock cannot exceed 23:59:59 and thus, while the total seconds
     * are equal to or more than 86400 (i.e. 24 hours) then the clock is rewinded to
     * the day before subtracting 86400 seconds (which makes sure that the total
     * seconds are in the interval [0-86400])
     *
     * @param totalSeconds the total seconds since midnight
     * @throws IllegalArgumentException if totalSeconds is negative
     */
    public Clock(int totalSeconds) {
        set(totalSeconds);
    }

    /**
     * Getter for hour
     *
     * @return the hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Getter for minute
     *
     * @return the minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Getter for second
     *
     * @return the second
     */
    public int getSecond() //
    {
        return second;
    }

    /**
     * Setter for the clock
     *
     * @param hour   hours (0-23)
     * @param minute minute (0-59)
     * @param second second (0-59)
     * @throws IllegalArgumentException if hour is not in the interval [0-23],
     *                                  minute not in the interval [0-59] or second
     *                                  not in the interval [0-59]
     */
    public void set(int hour, int minute, int second) //
    {
        if (hour < 0 || minute < 0 || second < 0 || hour > 23 || minute > 59 || second > 59) {
            throw new IllegalArgumentException();
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    /**
     * Sets the time according to the total seconds given as argument. The clock
     * cannot exceed 23:59:59 and thus, while the total seconds are equal to or more
     * than 86400 (i.e. 24 hours) then the clock is rewinded to the day before
     * subtracting 86400 seconds (which makes sure that the total seconds are in the
     * interval [0-86400])
     *
     * @param totalSeconds the total seconds since midnight
     * @throws IllegalArgumentException if totalSeconds is negative
     */
    public void set(int totalSeconds) //
    {
        if (totalSeconds < 0) {
            throw new IllegalArgumentException("Negative value");
        }
        if (totalSeconds >= 86400) {
            totalSeconds %= 86400;
        }
        this.hour = totalSeconds / 3600;
        this.minute = (totalSeconds % 3600) / 60;
        this.second = (totalSeconds % 3600) % 60;
    }

    /**
     * Converting the clock to total seconds since midnight
     *
     * @return the total seconds since midnight
     */
    public int getTimeInSeconds() {
        return hour * 3600 + minute * 60 + second;
    }

    /**
     * Updating the clock to the next second. <br>
     * 13:12:58 will be updated to 13:12:59<br>
     * 13:12:59 will be updated to 13:13:00<br>
     * 13:59:59 will be updated to 14:00:00<br>
     * 23:59:59 will be updated to 00:00:00<br>
     */
    public void tic() //
    {
        second++;
        if (second > 59) {
            second = 0;
            minute++;
            if (minute > 59) {
                minute = 0;
                hour++;
                if (hour > 23) {
                    hour = 0;
                }
            }
        }
    }

    /**
     * A string representation of the clock in the format HH:MM:SS. I.e. two digits
     * for hour, minute and second. Examples: "09:59:59", "10:05:01", "23:59:00"
     *
     * @return a string representation of the clock in the format HH:MM:SS
     */
    public String toString() //
    {
        String s = "";
        if (hour < 10) {
            s += "0";
        }
        s += hour + ":";
        if (minute < 10) {
            s += "0";
        }
        s += minute + ":";
        if (second < 10) {
            s += "0";
        }
        s += second;
        return s;
    }
}
