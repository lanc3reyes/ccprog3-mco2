package model;

/**
 * The Date class represents a specific date with a month, day, and year.
 * This includes methods for comparing dates.
 */
public class Date {
    private int day;
    private int month;
    private int year;

    /**
     * Default constructor for the Date class. Initializes the date to December 1, 2023.
     */
    public Date() {
        this.month = 12;
        this.day = 1;
        this.year = 2023;
    }

    /**
     * Constructor for the Date class with a specified day. Initializes the date to December of the specified day in 2023.
     * 
     * @param day - day of the month
     */
    public Date(int day) {
        this.month = 12;
        this.day = day;
        this.year = 2023;
    }

    /**
     * Gets the month of the date.
     * 
     * @return month of the date
     */
    public int getMonth() {
        return month;
    }

    /**
     * Gets the day of the date.
     * 
     * @return day of the date
     */
    public int getDay() {
        return day;
    }

    /**
     * Gets the year of the date.
     * 
     * @return year of the date
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the day of the date.
     * 
     * @param day new day of the date
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Checks if this date is before another date.
     * 
     * @param date2 other date to compare to
     * @return true if this date is before the other date, otherwise it's false
     */
    public boolean isBefore(Date date2) {
        return this.day < date2.day;
    }

    /**
     * Checks if this date is after another date.
     * 
     * @param date2 other date to compare to
     * @return true if this date is after the other date, otherwise it's false
     */
    public boolean isAfter(Date date2) {
        return this.day > date2.day;
    }

    /**
     * Checks if this date is equal to another date.
     * 
     * @param date2 other date to compare to
     * @return true if this date is equal to the other date, otherwise it's false
     */
    public boolean isEqual(Date date2) {
        return this.day == date2.day;
    }

    public Date addDays(int value) {
        int newDay = this.day + value;

        return new Date(newDay);
    }
}
