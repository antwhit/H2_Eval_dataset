import quickfix.*;
import quickfix.field.*;

public class at_application implements Application {

    private at_messagecracker cracker = new at_messagecracker();

    public void onCreate(SessionID sessionID) {
        Session session = Session.lookupSession(sessionID);
        try {
            if (session != null) session.reset();
        } catch (java.io.IOException ioe) {
        }
    }

    public void onLogon(SessionID sessionID) {
    }

    public void onLogout(SessionID sessionID) {
        cracker.reset();
    }

    public void toAdmin(Message message, SessionID sessionID) {
    }

    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
    }

    public void fromAdmin(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
    }

    public void fromApp(Message message, SessionID sessionID) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        cracker.crack(message, sessionID);
    }
}
