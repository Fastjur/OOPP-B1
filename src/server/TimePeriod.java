package server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Fastjur on 2-12-2015.
 */
public class TimePeriod {
    private int start;
    private int end;

    public TimePeriod(String startS, String endS) {
        if(startS == null || endS == null) {
            throw new IllegalArgumentException("Start or end time is null");
        }
        if(startS.equals(endS)) {
            throw new IllegalArgumentException("Start and end time are the same");
        }
        Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher mStart = p.matcher(startS),
                mEnd = p.matcher(endS);
        if(!mStart.matches() || !mEnd.matches()) {
            throw new IllegalArgumentException("Illegal start or end time specified!");
        }
        int start = parseToMinutes(startS),
            end = parseToMinutes(endS);
        if(end - start <= 0) {
            throw new IllegalArgumentException("Start time is after end time");
        }
        this.start = start;
        this.end = end;
    }

    private int getStart() {
        return this.start;
    }

    private int getEnd() {
        return this.end;
    }

    private int parseToMinutes(String time) {
        String[] split = time.split(":");
        int minutes = 0;
        minutes += Integer.parseInt(split[0]) * 60;
        minutes += Integer.parseInt(split[1]);
        return minutes;
    }
}
