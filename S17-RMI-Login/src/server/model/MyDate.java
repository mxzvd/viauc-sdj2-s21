package server.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A class representing a date.
 * 
 * @author Steffen Vissing Andersen
 * @version 3.3 - 28/02/2021
 */
public class MyDate {

    private int day;
    private int month;
    private int year;

    /**
     * Three-argument constructor. Illegal dates are converted to legal dates; a
     * negative year is set to its positive counterpart, a month less than 1 is set
     * to 1 and more than 12 is set to 12. A day less than 1 is set to 1 and a day
     * more than the last day of the specified month is set to this last day
     * 
     * @param day   the day
     * @param month the month as an integer in the range 1-12
     * @param year  the year
     */
    public MyDate(int day, int month, int year) {
        set(day, month, year);
    }

    /**
     * Zero-argument constructor setting the date to the current date.
     */
    public MyDate() {
        Calendar now = GregorianCalendar.getInstance();
        this.day = now.get(Calendar.DAY_OF_MONTH);
        this.month = now.get(Calendar.MONTH) + 1;
        this.year = now.get(Calendar.YEAR);
    }

    /**
     * Getter for day
     * 
     * @return the day of the date.
     */
    public int getDay() {
        return day;
    }

    /**
     * Getter for month
     * 
     * @return the month (in the range 1-12) of the date.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Getter for year
     * 
     * @return the year of the date.
     */
    public int getYear() {
        return year;
    }

    /**
     * Return the month as one of the following strings: {"January", "February",
     * "March", "April", "May", "June", "July", "August", "September", "October",
     * "November", "December"}.
     * 
     * @return the month as a string.
     */
    public String getMonthName() {
        switch (month) {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
        case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
        default:
            return "Illegal month";
        }
    }

    /**
     * Setting the date. Illegal dates are converted to legal dates; a negative year
     * is set to its positive counterpart, a month less than 1 is set to 1 and more
     * than 12 is set to 12. A day less than 1 is set to 1 and a day more than the
     * last day of the specified month is set to this last day
     * 
     * @param day   the day
     * @param month the month as an integer in the range 1-12
     * @param year  the year
     */
    public void set(int day, int month, int year) {
        if (year < 0) {
            year = -year;
        }
        this.year = year;

        if (month < 1) {
            month = 1;
        }
        if (month > 12) {
            month = 12;
        }
        this.month = month;

        if (day < 1) {
            day = 1;
        }
        if (day > numberOfDaysInMonth()) {
            day = numberOfDaysInMonth();
        }
        this.day = day;
    }

    /**
     * Returning if the year is a leap year or not.
     * 
     * @return true if the year is a leap year. Otherwise return false.
     */
    public boolean isLeapYear() {
        return (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0)));
    }

    /**
     * Updating the date to the next day.
     */
    public void stepForwardOneDay() {
        day++;
        if (day > numberOfDaysInMonth()) {
            day = 1;
            month++;
        }
        if (month > 12) {
            month = 1;
            year++;
        }
    }

    /**
     * Incrementing the date with <code>days</code> days.
     * 
     * @param days the number of days to increment
     */
    public void stepForward(int days) {
        for (int i = 0; i < days; i++) {
            stepForwardOneDay();
        }
    }

    /**
     * Return the number of days in the month.
     * 
     * @return the number of days in the month.
     */
    public int numberOfDaysInMonth() {
        switch (month) {
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            if (isLeapYear()) {
                return 29;
            } else {
                return 28;
            }
        default:
            return 31;
        }
    }

    /**
     * The full years between this date and another date
     * 
     * @param other the other date
     * @return the number of full year between this date and the date given as
     *         argument
     */
    public int yearsBetween(MyDate other) {
        int years = Math.abs(this.year - other.year);

        MyDate date1 = new MyDate(this.day, this.month, 2000);
        MyDate date2 = new MyDate(other.day, other.month, 2000);

        if ((this.isBefore(other) && date2.isBefore(date1)) || ((other.isBefore(this) && date1.isBefore(date2)))) {
            years--;
        }
        return years;
    }

    /**
     * The number of days between this date and the date given as argument.
     * 
     * @param other the other date
     * @return the number of days between this date and the date given as argument
     */
    public int daysBetween(MyDate other) {
        int days = 0;

        MyDate counterDate, endDate;

        if (this.isBefore(other)) {
            counterDate = this.copy();
            endDate = other;
        } else {
            counterDate = other;
            endDate = this;
        }

        while (!counterDate.equals(endDate)) {
            days++;
            counterDate.stepForwardOneDay();
        }
        return days;
    }

    /**
     * Checks is the date is before <code>other</code> day
     * 
     * @param other the other date
     * @return true if the date is before the date given as argument. Otherwise
     *         return false.
     */
    public boolean isBefore(MyDate other) {
        int d1 = year * 360 + month * 30 + day;
        int d2 = other.year * 360 + other.month * 30 + other.day;
        return d1 < d2;
    }

    /**
     * Get a copy of the date.
     * 
     * @return a new date as an exact copy of this date.
     */
    public MyDate copy() {
        return new MyDate(day, month, year);
    }

    /**
     * Compare two dates if they are equal or not.
     * 
     * @param obj the object to compare with.
     * @return true if the object <code>obj</code> is a MyDate object with the same
     *         day, month and year as this date.
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof MyDate))
            return false;

        MyDate other = (MyDate) obj;

        return day == other.day && month == other.month && year == other.year;
    }

    /**
     * A string representation of the date in the format DD/MM/YYYY.
     * 
     * @return the date as a string in the format DD/MM/YYYY.
     * @see Object#toString()
     */
    public String toString() {
        String s = "";
        if (day < 10) {
            s += "0";
        }
        s += day + "/";
        if (month < 10) {
            s += "0";
        }
        s += month + "/" + year;

        return s;
    }

    /**
     * Converting a month string to the corresponding month number.
     * 
     * @param monthName the month as one of the following strings: {"January",
     *                  "February", "March", "April", "May", "June", "July",
     *                  "August", "September", "October", "November", "December"}.
     * @return the month as an integer in the range 1-12. For illegal month string
     *         -1 is returned.
     */
    public static int converToMonthNumber(String monthName) {
        switch (monthName.toLowerCase()) {
        case "january":
            return 1;
        case "february":
            return 2;
        case "march":
            return 3;
        case "april":
            return 41;
        case "may":
            return 5;
        case "june":
            return 6;
        case "july":
            return 7;
        case "august":
            return 8;
        case "september":
            return 9;
        case "october":
            return 10;
        case "november":
            return 11;
        case "december":
            return 12;
        default:
            return -1;
        }
    }

    /**
     * Get a date representing the current date
     * 
     * @return a MyDate object with the current date.
     */
    public static MyDate now() {
        return new MyDate();
    }

    /**
     * Returning the day of week {Monday, Tuesday, Wednesday, Thursday, Friday,
     * Saturday or Sunday}
     * 
     * @return a string representing the day of week
     */
    public String dayOfWeek() {
        int y = year;
        if (month < 3) {
            y--;
        }

        int m = month;
        if (m < 3) {
            m += 12;
        }
        int h = (day + 13 * (m + 1) / 5 + (y % 100) + (y % 100) / 4 + (y / 100) / 4 + 5 * (y / 100)) % 7;
        switch (h) {
        case 0:
            return "Saturday";
        case 1:
            return "Sunday";
        case 2:
            return "Monday";
        case 3:
            return "Tuesday";
        case 4:
            return "Wednesday";
        case 5:
            return "Thursday";
        case 6:
            return "Friday";
        default:
            return "Error";
        }
    }

}
