package shared;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Fastjur on 2-12-2015.
 * shared.AvailableTimes holds the TimePeriods of a shared.User object on a weekday basis
 *
 * @author Jurriaan Den Toonder
 * @version 0.5
 * @see TimePeriod
 */
public class AvailableTimes {
    private ArrayList<TimePeriod> monday = new ArrayList<>(), tuesday = new ArrayList<>(),
            wednesday = new ArrayList<>(), thursday = new ArrayList<>(),
            friday = new ArrayList<>(), saturday = new ArrayList<>(), sunday = new ArrayList<>();

    /**
     * Empty constructor for JSON
     */
    public AvailableTimes() {
    }

    /**
     * Returns this object in a String in JSON format
     *
     * @return String, this object formatted in JSON
     * @throws IOException
     */
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    /**
     * Creates an shared.AvailableTimes object from a JSON string
     *
     * @param json JSON formatted string to create user from
     * @return shared.AvailableTimes object with properties given in JSON string
     * @throws IOException
     */
    public static AvailableTimes fromJson(String json) throws IOException {
        if (json == null)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, AvailableTimes.class);
    }

    /**
     * Add a shared.TimePeriod to this shared.AvailableTimes object
     *
     * @param day int, day of week. 1 = monday, 7 = sunday
     * @param p   shared.TimePeriod, the shared.TimePeriod to add to this shared.AvailableTimes object
     */
    public void addTimePeriod(int day, TimePeriod p) {
        switch (day) {
            case 1:
                this.monday.add(p);
                break;
            case 2:
                this.tuesday.add(p);
                break;
            case 3:
                this.wednesday.add(p);
                break;
            case 4:
                this.thursday.add(p);
                break;
            case 5:
                this.friday.add(p);
                break;
            case 6:
                this.saturday.add(p);
                break;
            case 7:
                this.sunday.add(p);
                break;
        }
    }

    /**
     * Get a single shared.TimePeriod from a given day using the shared.TimePeriod's index
     *
     * @param day   int, day of week, 1 = monday, 7 = sunday
     * @param index int, index of the shared.TimePeriod
     * @return shared.TimePeriod
     */
    public TimePeriod getPeriod(int day, int index) {
        switch (day) {
            case 1:
                return this.monday.get(index);
            case 2:
                return this.tuesday.get(index);
            case 3:
                return this.wednesday.get(index);
            case 4:
                return this.thursday.get(index);
            case 5:
                return this.friday.get(index);
            case 6:
                return this.saturday.get(index);
            case 7:
                return this.sunday.get(index);
        }
        return null;
    }

    /**
     * Remove a shared.TimePeriod from a given day using the shared.TimePeriod's index
     *
     * @param day   int, day of week, 1 = monday, 7 = sunday
     * @param index int, index of shared.TimePeriod
     */
    public void removeTime(int day, int index) {
        switch (day) {
            case 1:
                if (this.monday.size() > index)
                    this.monday.remove(index);
                break;
            case 2:
                if (this.tuesday.size() > index)
                    this.tuesday.remove(index);
                break;
            case 3:
                if (this.wednesday.size() > index)
                    this.wednesday.remove(index);
                break;
            case 4:
                if (this.thursday.size() > index)
                    this.thursday.remove(index);
                break;
            case 5:
                if (this.friday.size() > index)
                    this.friday.remove(index);
                break;
            case 6:
                if (this.saturday.size() > index)
                    this.saturday.remove(index);
                break;
            case 7:
                if (this.sunday.size() > index)
                    this.sunday.remove(index);
                break;
        }
    }

    /**
     * Return this shared.AvailableTimes object in a String representation
     *
     * @return String
     */
    public String toString() {
        String res = "Maandag(";
        for (TimePeriod p : this.monday) {
            res += p.toString();
        }
        res += ") Dinsdag(";
        for (TimePeriod p : this.tuesday) {
            res += p.toString();
        }
        res += ") Woensdag(";
        for (TimePeriod p : this.wednesday) {
            res += p.toString();
        }
        res += ") Donderdag(";
        for (TimePeriod p : this.thursday) {
            res += p.toString();
        }
        res += ") Vrijdag(";
        for (TimePeriod p : this.friday) {
            res += p.toString();
        }
        res += ") Zaterdag(";
        for (TimePeriod p : this.saturday) {
            res += p.toString();
        }
        res += ") Zondag(";
        for (TimePeriod p : this.sunday) {
            res += p.toString();
        }
        res += ")";
        return res;
    }

    /**
     * Returns an shared.AvailableTimes object with TimePeriods that are in both `this` AND `that`
     *
     * @param that The object to intersect `this` with
     * @return shared.AvailableTimes object containing the intersection between this and that
     */
    public AvailableTimes intersect(AvailableTimes that) {
        if (this == that || this.equals(that)) {
            return that;
        }
        AvailableTimes res = new AvailableTimes();
        int index = 0;
        for (TimePeriod p : that.getMonday()) {
            if (this.monday.contains(that.getMonday().get(index))) {
                res.addTimePeriod(1, p);
            }
            index++;
        }
        index = 0;
        for (TimePeriod p : that.getTuesday()) {
            if (this.tuesday.contains(that.getTuesday().get(index))) {
                res.addTimePeriod(2, p);
            }
            index++;
        }
        index = 0;
        for (TimePeriod p : that.getWednesday()) {
            if (this.wednesday.contains(that.getWednesday().get(index))) {
                res.addTimePeriod(3, p);
            }
            index++;
        }
        index = 0;
        for (TimePeriod p : that.getThursday()) {
            if (this.thursday.contains(that.getThursday().get(index))) {
                res.addTimePeriod(4, p);
            }
            index++;
        }
        index = 0;
        for (TimePeriod p : that.getFriday()) {
            if (this.friday.contains(that.getFriday().get(index))) {
                res.addTimePeriod(5, p);
            }
            index++;
        }
        index = 0;
        for (TimePeriod p : that.getSaturday()) {
            if (this.saturday.contains(that.getSaturday().get(index))) {
                res.addTimePeriod(6, p);
            }
            index++;
        }
        index = 0;
        for (TimePeriod p : that.getSunday()) {
            if (this.sunday.contains(that.getSunday().get(index))) {
                res.addTimePeriod(7, p);
            }
            index++;
        }
        return res;
    }

    /**
     * Returns the total amount of TimePeriods in this shared.AvailableTimes object
     *
     * @return integer, total amount of TimePeriods
     */
    public int size() {
        return this.monday.size() + this.tuesday.size() + this.wednesday.size() + this.thursday.size() +
                this.friday.size() + this.saturday.size() + this.sunday.size();
    }

    public String toReadable(int dayNr) throws IllegalArgumentException {
        ArrayList<TimePeriod> day;
        switch (dayNr){
            case 1:
                day = this.monday;
                break;
            case 2:
                day = this.tuesday;
                break;
            case 3:
                day = this.wednesday;
                break;
            case 4:
                day = this.thursday;
                break;
            case 5:
                day = this.friday;
                break;
            case 6:
                day = this.saturday;
                break;
            case 7:
                day = this.sunday;
                break;
            default:
                throw new IllegalArgumentException("Invalid day number given!");
        }
        String res = "";
        if (day.size() > 0) {
            for (TimePeriod p : day) {
                res += p.toReadable() + " ";
            }
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public static ArrayList<TimePeriod> fromReadable(String readable) throws IllegalArgumentException {
        String arr[] = readable.split(" ");
        ArrayList<TimePeriod> res = new ArrayList<>();
        for (String s : arr) {
            TimePeriod p = TimePeriod.fromReadable(s);
            res.add(p);
        }
        return res;
    }

    public boolean equals(Object other) {
        if (other instanceof AvailableTimes) {
            AvailableTimes that = (AvailableTimes) other;
            return this.monday.equals(that.monday) &&
                    this.tuesday.equals(that.tuesday) &&
                    this.wednesday.equals(that.wednesday) &&
                    this.thursday.equals(that.thursday) &&
                    this.friday.equals(that.friday) &&
                    this.saturday.equals(that.saturday) &&
                    this.sunday.equals(that.sunday);
        }
        return false;
    }

    /*
     * Start of Getters and Setters block
     */

    public ArrayList<TimePeriod> getMonday() {
        return monday;
    }

    public void setMonday(ArrayList<TimePeriod> monday) {
        this.monday = monday;
    }

    public ArrayList<TimePeriod> getTuesday() {
        return tuesday;
    }

    public void setTuesday(ArrayList<TimePeriod> tuesday) {
        this.tuesday = tuesday;
    }

    public ArrayList<TimePeriod> getWednesday() {
        return wednesday;
    }

    public void setWednesday(ArrayList<TimePeriod> wednesday) {
        this.wednesday = wednesday;
    }

    public ArrayList<TimePeriod> getThursday() {
        return thursday;
    }

    public void setThursday(ArrayList<TimePeriod> thursday) {
        this.thursday = thursday;
    }

    public ArrayList<TimePeriod> getFriday() {
        return friday;
    }

    public void setFriday(ArrayList<TimePeriod> friday) {
        this.friday = friday;
    }

    public ArrayList<TimePeriod> getSaturday() {
        return saturday;
    }

    public void setSaturday(ArrayList<TimePeriod> saturday) {
        this.saturday = saturday;
    }

    public ArrayList<TimePeriod> getSunday() {
        return sunday;
    }

    public void setSunday(ArrayList<TimePeriod> sunday) {
        this.sunday = sunday;
    }
}
