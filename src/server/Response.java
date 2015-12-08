package server;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Govert on 7-12-2015.
 */
public class Response {
    public String responseTo;
    public int errorCode;
    public String errorMessage;
    private Map<String, Object> responseData;

    public Response(String responseTo) {
        this.responseTo = responseTo;
        this.errorCode = 0;
        this.errorMessage = "";
        this.responseData = new HashMap<>();
    }

    public void putData(String key, Object value) {
        this.responseData.put(key, value);
    }

    public Map<String, Object> getResponseData() {
        return this.responseData;
    }
}
