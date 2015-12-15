import org.codehaus.jackson.annotate.JsonCreator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Govert on 14-12-2015.
 */
public class Request {
    private String action;
    private Map<String, Object> requestData;

    public Request(String action) {
        this.action = action;
        this.requestData = new HashMap<String, Object>();
    }

    @JsonCreator
    public Request() { }

    public void putData(String key, Object value) {
        this.requestData.put(key, value);
    }

    public Map<String, Object> getRequestData() {
        return this.requestData;
    }

    public String toString() {
        return "{ \"action\" : \"" + this.action
                + "\" \"requestData\" : \"" + requestData.toString()
                + " }";
    }
}
