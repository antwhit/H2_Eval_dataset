import com.webobjects.foundation.*;
import com.webobjects.eocontrol.*;
import com.gammastream.validity.*;

public class User extends GSVGenericRecord {

    public User() {
        super();
    }

    public String username() {
        return (String) storedValueForKey("username");
    }

    public void setUsername(String value) {
        takeStoredValueForKey(value, "username");
    }

    public String password() {
        return (String) storedValueForKey("password");
    }

    public void setPassword(String value) {
        takeStoredValueForKey(value, "password");
    }

    public String firstName() {
        return (String) storedValueForKey("firstName");
    }

    public void setFirstName(String value) {
        takeStoredValueForKey(value, "firstName");
    }

    public String lastName() {
        return (String) storedValueForKey("lastName");
    }

    public void setLastName(String value) {
        takeStoredValueForKey(value, "lastName");
    }

    public Number number() {
        return (Number) storedValueForKey("number");
    }

    public void setNumber(Number value) {
        takeStoredValueForKey(value, "number");
    }
}
