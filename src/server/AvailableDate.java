package server;

import java.util.ArrayList;

public class AvailableDate {
    private Weekdays day;
    private ArrayList<TimePeriod> availableTimesList;

    /**
     * AvailableDate: constructor for class AvailableDate
     *
     * @param day - Weekdays
     */
    public AvailableDate(Weekdays day) {
        this.day = day;
        availableTimesList = new ArrayList<TimePeriod>();
    }

    /**
     * toString: gives a textual representation of day
     *
     * @return text - String
     */
    public String toString() {
        String text = new String();

        text += day.toString();
        for (int i = 0; i < availableTimesList.size(); i++) {
            text += " " + availableTimesList.get(i).toString();
            if (i < availableTimesList.size() - 1) {
                text += ";";
            }
        }
        return text;
    }

    /**
     * getDate: getter for private attribute day
     *
     * @return day - Weekdays
     */
    public Weekdays getDate() {
        return day;
    }

    /**
     * setDate: setter for private attribute day
     *
     * @param day - Weekdays
     */
    public void setDate(Weekdays day) {
        this.day = day;
    }

    /**
     * getAvailableTimes: getter for for the size of arrayList availableTimes
     *
     * @return availableTimes.size() - int
     */
    public int getAvailableTimesSize() {
        return availableTimesList.size();
    }

    /**
     * containsAvailableTimes: checks if availableTimes is already in the arrayList
     *
     * @param availableTimes - TimePeriod
     * @return boolean
     */
    public boolean containsAvailableTimes(TimePeriod availableTimes) {
        for (int i = 0; i < availableTimesList.size(); i++) {
            if (availableTimesList.get(i).equals(availableTimes)) {
                return true;
            }
        }
        return false;
    }

    /**
     * addAvailableTimes: adds availableTimes in the arrayList if it isn't already in it
     *
     * @param availableTimes - TimePeriod
     */
    public void addAvailableTimes(TimePeriod availableTimes) {
        if (!this.containsAvailableTimes(availableTimes)) {
            availableTimesList.add(availableTimes);
        }
    }

    /**
     * equals: checks if current object is the same as the other object
     *
     * @param other - Object
     * @return boolean
     */
    public boolean equals(Object other) {
        if (other instanceof AvailableDate) {
            AvailableDate that = (AvailableDate) other;
            if (this.day.equals(that.getDate())) {
                if (availableTimesList.size() != that.getAvailableTimesSize()) {
                    return false;
                }
                for (int i = 0; i < availableTimesList.size(); i++) {
                    if (!that.containsAvailableTimes(availableTimesList.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
