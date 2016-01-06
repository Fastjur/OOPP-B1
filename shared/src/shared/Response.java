package shared;

import org.codehaus.jackson.annotate.JsonCreator;

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

    @JsonCreator
    public Response() {
    }

    public void putData(String key, Object value) {
        this.responseData.put(key, value);
    }

    public Map<String, Object> getResponseData() {
        return this.responseData;
    }

    public String toString() {
        return "{ \"responseTo\" : \"" + this.responseTo
                + "\" \"errorCode\" : \"" + errorCode
                + "\" \"errorMessage\" : \"" + errorMessage
                + "\" \"responseData\" : " + responseData.toString()
                + " }";
    }

    public boolean equals(Object other) {
        if (other instanceof Response) {
            Response that = (Response) other;
            return that.errorMessage.equals(this.errorMessage)
                    && that.errorCode == this.errorCode
                    && that.responseTo.equals(this.responseTo)
                    && that.responseData.equals(this.responseData);
        }
        return false;
    }
}
