import shared.AvailableTimes;
import shared.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * This is a dud database which should only be used for testing purposes.
 *
 * @author Govert de Gans
 */
@Deprecated
public class DudDatabase extends Database {
    public DudDatabase() {
        super(true);
    }

    /*@Override
    public User getUser(String email) {
        if (email.equals("sinterklaas@sintmail.nl")) {
            return new User(1, "Pepernoten01", "Sinter", "Klaas", new Date(00001),
                    "sinterklaas@sintmail.nl", "+316123456789",
                    "study1", "university", 3, new AvailableTimes(), new ArrayList<String>(),
                    new ArrayList<String>(), new ArrayList<String>(), "male", "NLD",
                    new ArrayList<String>(), "It's-a-me", 3, 0);
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        return;
    }*/
}
