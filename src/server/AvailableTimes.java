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
}
