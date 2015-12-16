package shared;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * shared.TimePeriod class that specifies a starting and ending time on a day basis
 * @author Jurriaan Den Toonder
 * @version 0.5
 */
public class TimePeriod {
    private int start;
    private int end;

    /**
     * Empty constructor for JSON
     */
    public TimePeriod(){}

    /**
     * Constructor using minute notation
     * @param start int, minutes from 00:00 to the starting point of the shared.TimePeriod
     * @param end int, minutes from 00:00 to the ending point of the shared.TimePeriod
     */
    public TimePeriod(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor allowing the use of a String representation of times
     * 24h notation
     * @param start String, starting time of shared.TimePeriod ("xx:xx")
     * @param end String, ending time of shared.TimePeriod ("xx:xx")
     */
    public TimePeriod(String start, String end) {
        if(start == null || end == null) {
            throw new IllegalArgumentException("Start or end time is null");
        }
        if(start.equals(end)) {
            throw new IllegalArgumentException("Start and end time are the same");
        }
        Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher mStart = p.matcher(start),
                mEnd = p.matcher(end);
        if(!mStart.matches() || !mEnd.matches()) {
            throw new IllegalArgumentException("Illegal start or end time specified!");
        }
        int startInt = parseToMinutes(start),
            endInt = parseToMinutes(end);
        if(endInt - startInt <= 0) {
            throw new IllegalArgumentException("Start time is after end time");
        }
        this.start = startInt;
        this.end = endInt;
    }

    /**
     * Formats this shared.TimePeriod object to JSON and returns it in a String
     * @return String, JSON representation of this shared.TimePeriod
     * @throws IOException
     */
    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    /**
     * Creates a shared.TimePeriod from a JSON formatted String
     * @param json String, JSON representation of a shared.TimePeriod object
     * @return shared.TimePeriod object
     * @throws IOException
     */
    public static TimePeriod fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TimePeriod.class);
    }

    /**
     * Takes a String representing a time and returns it as a minutes from 00:00 representation
     * So 01:00 = 60, 01:30 = 90
     * @param time String representation of a time
     * @return int, amount of minutes from 00:00
     */
    private int parseToMinutes(String time) {
        String[] split = time.split(":");
        int minutes = 0;
        minutes += Integer.parseInt(split[0]) * 60;
        minutes += Integer.parseInt(split[1]);
        return minutes;
    }

    /**
     * Return this shared.TimePeriod as a String representation
     * @return String
     */
    public String toString() {
        return "<TimePeriod(" + this.start + "-" + this.end + ")>";
    }

    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (!(other instanceof TimePeriod))
            return false;

        TimePeriod that = (TimePeriod) other;
        return this.start == that.start &&
                this.end == that.end;
    }

    /*
       Start of Getters and Setters block
     */
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
