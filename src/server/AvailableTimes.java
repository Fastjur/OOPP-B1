package server;

import java.util.ArrayList;

/**
 * Created by Fastjur on 2-12-2015.
 * Available times per weekday. 1 is monday, 7 is sunday
 */
public class AvailableTimes {
    private ArrayList<TimePeriod> monday = new ArrayList<>(),
                                  tuesday = new ArrayList<>(),
                                  wednesday = new ArrayList<>(),
                                  thursday = new ArrayList<>(),
                                  friday = new ArrayList<>(),
                                  saturday = new ArrayList<>(),
                                  sunday = new ArrayList<>();

    public AvailableTimes() {}

    public void addTimePeriod(int day, TimePeriod p) {
        switch(day){
            case 1: this.monday.add(p);
            case 2: this.tuesday.add(p);
            case 3: this.wednesday.add(p);
            case 4: this.thursday.add(p);
            case 5: this.friday.add(p);
            case 6: this.saturday.add(p);
            case 7: this.sunday.add(p);
        }
    }

    public ArrayList<TimePeriod> getTimesOfDay(int day) {
        switch(day) {
            case 1: return this.monday;
            case 2: return this.tuesday;
            case 3: return this.wednesday;
            case 4: return this.thursday;
            case 5: return this.friday;
            case 6: return this.saturday;
            case 7: return this.sunday;
        }
        return null;
    }

    public TimePeriod getPeriod(int day, int index) {
        switch(day) {
            case 1: return this.monday.get(index);
            case 2: return this.tuesday.get(index);
            case 3: return this.wednesday.get(index);
            case 4: return this.thursday.get(index);
            case 5: return this.friday.get(index);
            case 6: return this.saturday.get(index);
            case 7: return this.sunday.get(index);
        }
        return null;
    }

    public void removeTime(int day, int index) {
        switch(day) {
            case 1: this.monday.remove(index);
            case 2: this.tuesday.remove(index);
            case 3: this.wednesday.remove(index);
            case 4: this.thursday.remove(index);
            case 5: this.friday.remove(index);
            case 6: this.saturday.remove(index);
            case 7: this.sunday.remove(index);
        }
    }

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
}
