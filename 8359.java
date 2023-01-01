import java.util.*;
import java.io.*;
import org.protoflorum.generated.*;
import org.protoflorum.generated.exception.*;
import com.anclad.htmlcontrols.*;
import com.anclad.htmlcontrols.dataentry.*;
import com.anclad.htmlcontrols.forms.*;
import com.anclad.htmlcontrols.tables.*;
import com.anclad.htmlcontrols.links.*;

/** This is a convenience class so we only have to store
	one object (this) in the HTTP session.
*/
public class AppSession {

    public AppSession() {
    }

    public void newUser(String new_user_email) {
        new_user_e.setValue(new_user_email);
        if (!new_user_e.isValid()) {
            new_user_e.setLabel("This is <b>not</b> a valid email address:");
            return;
        } else {
            new_user_e.setLabel("Invite a new user to this thread (enter email here):");
            new_user_e.setValue(null);
        }
        UserBean u_new = uf.create();
        u_new.setEmail(new_user_email);
        try {
            if (um.loadUniqueUsingTemplate(u_new) == null) {
                um.save(u_new);
            } else {
                u_new = um.loadUniqueUsingTemplate(u_new);
            }
            messages.add("test12.java, 63: " + "new user id is " + u_new.getId());
            messages.add("test12.java, 67: " + "going to invite user with " + u_new.getId());
            inviteUser(u_new.getId());
        } catch (DAOException d) {
            messages.add("test12.java, 72: " + "DAO Exception");
        }
    }
}
