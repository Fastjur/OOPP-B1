package communication;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
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
        this.requestData = new HashMap<>();
    }

    @JsonCreator
    public Request() {
    }

    public void putData(String key, Object value) {
        this.requestData.put(key, value);
    }

    public String getAction() {
        return this.action;
    }

    public Map<String, Object> getRequestData() {
        return this.requestData;
    }

    public String toString() {
        return "{ \"action\" : \"" + this.action
                + "\", \"requestData\" : \"" + requestData.toString()
                + "\" }";
    }

    public String toSendableJSON() throws IOException {
        //TODO escape characters that are illegal/special
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
