package server;

public class Date {
	private String date;
	private int[] daysInMonth = { 31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31 };
	
	/** 
	 * Constructor for Date class, checks if date is valid
	 * 00-00-000 is default for invalid date
	 * @param date - String
	 */
	public Date(String date) {
		if (checkDate(date)) {
			this.date = date;
		} else this.date = "00-00-0000";
	}
	
	/**
	 * toString: gives a textual representation of date
	 * @return date - String
	 */
	public String toString() {
		return date;
	}
	
	/** 
	 * checkDate: checks if date is valid
	 * @param date - String
	 * @return	boolean
	 */
	private boolean checkDate(String date)
	{
		int day, month, year;
		// date format used is: dd-mm-yyyy
		// check if date is a valid date
		if (date.length() == 10) {
			if ((date.charAt(2) == 45) && (date.charAt(5) == 45)) { // ASCII 45 = "-" 
				day   = Integer.parseInt(date.substring(0, 2)); // dd
				month = Integer.parseInt(date.substring(3, 5)); // mm
				year  = Integer.parseInt(date.substring(6)); // yyyy
				
				// Simple check, compares regular dates or Feb. 29 in leap years
				if (((year > 1900 && year < 2200) && 
					 (month > 0 && month < 13) && 
					 (day > 0 && day <= daysInMonth[month-1])) ||
					((((year%4) == 0 && (year%100 != 0)) || ((year%400) == 0)) && month == 2 && day == 29 )) {
					return true;
				}
			}
		} 				
		return false;
	}
	
	/** 
	 * getDate: getter for private attribute date
	 * @return	date - String
	 */
	public String getDate() {
		return date;
	}
	
	/** 
	 * setDate: setter for private attribute date; checks if date is valid
	 * @param date - String
	 */
	public void setDate(String date) {
		if (checkDate(date)) {
			this.date = date;
		}
	}
	
	/** 
	 * equals: checks if current object is the same as other object
	 * @param other - Object
	 * @return boolean 
	 */
	public boolean equals(Object other) {
		if (other instanceof Date) {
			Date that = (Date) other; 
			if (this.date.equals(that.getDate())) {
				return true;
			}
		}
		return false;
	}
}
