package server;
public class Timepoint {
	private int hour, minute;
	
	/**
	 * Timepoint: constructor for class Timepoint
	 * @param hour - int
	 * @param minute - int
	 */
	public Timepoint(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	/**
	 * toString: gives a textual representation of Timepoint
	 * @return text - String
	 */
	public String toString() {
		String text = new String();
		
		if (this.hour < 10) {
			text = "0" + Integer.toString(this.hour);
		} else {
			text = Integer.toString(this.hour);
		}
		text += ":";
		if (this.minute < 10) {
			text += "0" + Integer.toString(this.minute);
		} else {
			text += Integer.toString(this.minute);
		}
		
		return text;
	}
	
	/**
	 * getHour: getter for private attribute hour
	 * @return hour - int
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * getMinute: getter for private attribute minute
	 * @return minute - int
	 */
	public int getMinute() {
		return minute;
	}
	
	/**
	 * setHour: setter for private attribute hour and checks if hour has a valid input
	 * @param hour - int
	 */
	public void setHour(int hour) {
		if (hour >= 0 && hour <= 23) {
			this.hour = hour;
		}
	}
	
	/**
	 * setMinute: setter for private attribute minute and checks if minute has a valid input
	 * @param minute - int
	 */
	public void setMinute(int minute) {
		if (minute >= 0 && minute <= 59) {
			this.minute = minute;
		}
	}
	
	/**
	 * earlierThan: checks if this Timepoint is earlier than other Timepoint
	 * @param other - Timepoint
	 * @return boolean
	 */
	public boolean earlierThan(Timepoint other) {
		if ((this.hour < other.getHour()) ||
			(this.hour == other.getHour() && this.minute < other.getMinute())) {
			return true;
		}
		return false;
	}
	
	/**
	 * laterThan: checks if this Timepoint is later than other Timepoint
	 * @param other - Timepoint
	 * @return boolean
	 */
	public boolean laterThan(Timepoint other) {
		if ((this.hour > other.getHour()) ||
			(this.hour == other.getHour() && this.minute > other.getMinute())) {
			return true;
		}
		return false;
	}
	
	/**
	 * equals: checks if current object is the same as the other object
	 * @param other - Object
	 * @return boolean
	 */
	public boolean equals(Object other) {
		if (other instanceof Timepoint) {
			Timepoint that = (Timepoint) other;
			if (this.hour == that.getHour() &&
				this.minute == that.getMinute()) {
				return true;
			}
		}
		return false;
	}
}
