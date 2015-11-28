package server;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Zoë van Steijn on 25-11-2015.
 */
public class SessionId {
    private SecureRandom random = new SecureRandom();

    public String randomSessionId(){
        return new BigInteger(160, random).toString(32);
    }
}
