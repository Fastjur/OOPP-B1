/**
 * Created by Zoë van Steijn on 25-11-2015.
 */
public class Session {
    private int userId;
    private String sessionId;

    public Session(int userId){
        this.userId = userId;
        SessionId sid = new SessionId();
        this.sessionId = sid.randomSessionId();
    }

    public String getSessionId(){
        return this.sessionId;
    }

    public int getUserId(){
        return this.userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public boolean equals(Object other){
        if(other instanceof Session){
            Session that = (Session) other;
            return(this.userId == that.userId && this.sessionId.equals(that.sessionId));
        }
        return false;
    }
}
