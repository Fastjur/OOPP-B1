package server;

import java.util.Calendar;

public class Birthday {
    private Calendar bday; // Calendar object for birthday

    /**
     * Birthday: constructor for class Birthday
     *
     * @param day   - int
     * @param month - int 	Note: Calendar stores month as 0..11
     * @param year  - int
     */
    public Birthday(int day, int month, int year) {
        this.bday = new Calendar.Builder().setCalendarType("iso8601").setDate(year, month - 1, day).build();
    }

    /**
     * intToString: return sting for integer with added 0 in front of values 0..9
     *
     * @param n - int
     * @return String with minimal length 2, prefix 0 for values 0..9
     */
    private String intToString(int n) {
        return (n < 10 ? "0" + Integer.toString(n) : Integer.toString(n));
    }

    /**
     * toString: gives a textual representation of Birthday
     *
     * @return String
     */
    public String toString() {
        return intToString(this.bday.get(Calendar.DAY_OF_MONTH)) + "-" +
                intToString(this.bday.get(Calendar.MONTH) + 1) + "-" +
                Integer.toString(this.bday.get(Calendar.YEAR));
    }

    /**
     * getCalendar: getter for private attribute cal
     *
     * @return cal - Calendar
     */
    public Calendar getCalendar() {
        return this.bday;
    }

    /**
     * getDay: getter for private attribute day
     *
     * @return day - int
     */
    public int getDay() {
        return this.bday.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * getMonth: getter for private attribute month
     *
     * @return month - int
     */
    public int getMonth() {
        return this.bday.get(Calendar.MONTH) + 1;
    }

    /**
     * getYear: getter for private attribute year
     *
     * @return month - int
     */
    public int getYear() {
        return this.bday.get(Calendar.YEAR);
    }

    /**
     * setCalendar: setter for private attribute cal
     *
     * @param cal - Calendar
     */
    public void setCalendar(Calendar cal) {
        this.bday = cal;
    }

    /**
     * setDay: setter for private attribute day
     *
     * @param day - int
     */
    public void setDay(int day) {
        this.bday.set(Calendar.DAY_OF_MONTH, day);
    }

    /**
     * setMonth: setter for private attribute month
     *
     * @param month - int
     */
    public void setMonth(int month) {
        this.bday.set(Calendar.MONTH, month - 1);
    }

    /**
     * setYear: setter for private attribute year
     *
     * @param year - int
     */
    public void setYear(int year) {
        this.bday.set(Calendar.YEAR, year);
    }

    /**
     * equals: checks if current object is the same as the other
     *
     * @param other - Object
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other instanceof Birthday) {
            Birthday that = (Birthday) other;
            if (this.bday.equals(that.getCalendar())) {
                return true;
            }
        }
        return false;
    }


}
