package server;

public class Weekdays {
    private static String[] daysInWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private String day;

    /**
     * Weekdays: constructor for class Weekdays
     *
     * @param day - String
     */
    public Weekdays(String day) {
        if (Weekdays.checkWeekday(day) == true) {
            this.day = day;
        } else {
            this.day = "";
        }
    }

    /**
     * toString: gives a textual representation of Weekdays
     *
     * @return day - String
     */
    public String toString() {
        return day;
    }

    /**
     * checkWeekday: checks if day is a weekday
     *
     * @param day - String
     * @return boolean
     */
    public static boolean checkWeekday(String day) {
        for (int i = 0; i < 7; i++) {
            if (day.equals(daysInWeek[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * getDay: getter for private attribute day
     *
     * @return day - String
     */
    public String getDay() {
        return day;
    }

    /**
     * setDay: setter for private attribute day
     *
     * @param day - String
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * equals: checks if current object is the same as the other object
     *
     * @param other - Object
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other instanceof Weekdays) {
            Weekdays that = (Weekdays) other;
            if (this.day.equals(that.getDay())) {
                return true;
            }
        }
        return false;
    }
}
