package server;
import java.util.ArrayList;

public class AvailableDate {
	private Date date;
	private ArrayList<TimePeriod> availableTimesList;
	
	public AvailableDate(Date date) {
		this.date = date;
		availableTimesList = new ArrayList<TimePeriod>();
	}
	
	/**
	 * toString: gives a textual representation of date
	 */
	public String toString() {
		String text = new String();
		
		text += date.toString() + "\n";
		for (int i = 0; i < availableTimesList.size(); i++) {
			text += availableTimesList.get(i).toString();
			if (i < availableTimesList.size() - 1) {
				text += "\n";
			}
		}
		return text;
	}
	
	/**
	 * getDate: getter for private attribute date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * setDate: setter for private attribute date
	 * @param date - Date
	 */
	public void setDate(Date date) {
		this.date.setDate(date.toString());
	}
	
	/**
	 * getAvailableTimes: getter for for the size of arrayList availableTimes
	 * @return availableTimes.size() - int
	 */
	public int getAvailableTimesSize() {
		return availableTimesList.size();
	}
	
	/**
	 * containsAvailableTimes: checks if availableTimes is already in the arrayList 
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
	 * @param availableTimes - TimePeriod
	 */
	public void addAvailableTimes(TimePeriod availableTimes) {
		if (!this.containsAvailableTimes(availableTimes)) {
			availableTimesList.add(availableTimes);
		}
	}
	
	public boolean equals(Object other) {
		if (other instanceof AvailableDate) {
			AvailableDate that = (AvailableDate) other;
			if (this.date.equals(that.getDate())) {
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
