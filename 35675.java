import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.sun.sgs.app.Channel;
import com.sun.sgs.app.ClientSession;

/**
 * Channel that registers in a log all the messages transmited
 * (Used only for conversations).
 */
public class LogChannelListener extends MJChannelListener implements Serializable {

    /** The version of the serialized form of this class. */
    private static final long serialVersionUID = 1L;

    /**
     * Conversation log.
     */
    private static final Logger conversation = Logger.getLogger("mundojava.conversation");

    /**
     * {@inheritDoc}
     */
    protected boolean validateEventType(int eventType) {
        return eventType == Event.CONVERSATION;
    }

    /**
     * {@inheritDoc}
     */
    protected boolean processBeforeSend(Channel channel, ClientSession session, Event event, String playerId) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    protected void processAfterSent(Channel channel, ClientSession session, Event event, String playerId) {
        conversation.log(Level.INFO, String.format("Channel: %s, playerId: %s, login: %s, message: %s", channel.getName(), playerId, session.getName(), event.getString("message")));
    }
}
