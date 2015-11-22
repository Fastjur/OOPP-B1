package server;

public class TimePeriod {
	private Timepoint beginTime, endTime;
	
	public TimePeriod(Timepoint beginTime, Timepoint endTime) {
		// check if TimePeriod is valid, i.e. endTime not earlier than beginTime
		if (!endTime.earlierThan(beginTime)) {
			this.beginTime = beginTime;
			this.endTime = endTime;
		} else { // create empty period
			this.beginTime = beginTime;
			this.endTime = beginTime;			
		}
	}
	
	public String toString() {
		return this.beginTime.toString() + " - " + this.endTime.toString();
	}
	
	/**
	 * beginPeriode: getter for private attribute beginHour and beginMinute
	 * @return beginHour - int
	 * @return beginMinute - int
	 */
	public Timepoint getBeginTime() {
		return beginTime;
	}
	
	/**
	 * endPeriod: getter for private attribute endHour + endMinute
	 * @return endHour - int
	 * @return endMinute - int
	 */
	public Timepoint getEndTime() {
		return endTime;
	}
	
	/**
	 * setBeginTime: setter for private attribute beginTime
	 * @param beginTime - Timepoint
	 */
	public void setBeginTime(Timepoint beginTime) {
		if (beginTime.earlierThan(endTime)) {
				this.beginTime = beginTime;
		}
	}
	
	/**
	 * setEndTime: setter for private attribute endTime
	 * @param endTime - Timepoint
	 */
	public void setEndTime(Timepoint endTime) {
		if (endTime.laterThan(beginTime)) {
			this.endTime = endTime;
		}
	}
	
	/**
	 * overlap: checks if period1 has overlapping times with period2
	 * @param period1 - TimePeriod
	 * @param period2 - TimePeriod
	 * @return overlapPeriod - TimePeriod
	 */
	public TimePeriod overlap(TimePeriod period2) {
		TimePeriod overlapPeriod = new TimePeriod(new Timepoint(0,0), new Timepoint(0,0));

		if ((this.endTime.earlierThan(period2.getEndTime()) || this.endTime.equals(period2.getEndTime())) &&
				period2.getBeginTime().earlierThan(this.endTime)) {
				overlapPeriod.setEndTime(this.endTime);
		} else if (period2.getEndTime().earlierThan(this.endTime) &&
				this.beginTime.earlierThan(period2.getEndTime())) {
				overlapPeriod.setEndTime(period2.getEndTime());
		} else {
				// there is a beginTime for the overlap, but no end time, so no overlap
				return new TimePeriod(new Timepoint(0,0), new Timepoint(0,0)); // return empty overlapPeriod
		}

		if ((this.beginTime.earlierThan(period2.getBeginTime()) || this.beginTime.equals(period2.getBeginTime())) && 
			(this.endTime.laterThan(period2.getBeginTime()) ||
			 this.endTime.equals(period2.getBeginTime()))) {
			overlapPeriod.setBeginTime(period2.getBeginTime()); // overlap.beginTime = period2.beginTime			
		} else if ((period2.getBeginTime().earlierThan(this.beginTime) ||
				   (period2.getBeginTime().equals(this.beginTime))) && 
				   period2.getEndTime().laterThan(this.beginTime)) {
				overlapPeriod.setBeginTime(this.beginTime); // overlap.beginTime = period1.beginTime
		} else {
			// there is and time, but no beginTime for the overlap, so no overlap
			return new TimePeriod(new Timepoint(0,0), new Timepoint(0,0)); // return empty overlapPeriod			
		}
		
		// both beginTime and endTime for overlap found
		return overlapPeriod;
	}
	
	/**
	 * isEmpty: checks if the period is empty
	 * @param period - TimePeriod
	 * @return booleans
	 */
	public boolean isEmpty() {
		if (this.beginTime.equals(this.endTime)) {
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
		if (other instanceof TimePeriod) {
			TimePeriod that = (TimePeriod) other;
			if (this.beginTime.equals(that.getBeginTime()) &&
				this.endTime.equals(that.getEndTime())) {
				return true;
			}
		}
		return false;
	}
	
}
