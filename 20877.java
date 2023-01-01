import se.sics.isl.transport.Transportable;
import se.sics.tasim.logtool.LogReader;
import se.sics.tasim.logtool.ParticipantInfo;
import se.sics.tasim.props.OfferBundle;
import se.sics.tasim.tac05.Parser;

/**
 * <code>OfferCounterParser</code> is a simple example of a TAC SCM
 * parser that counts all offers sent in a simulation from the
 * simulation log file.<p>
 *
 * The class <code>se.sics.tasim.tac05.Parser</code> is inherited to
 * provide base functionality for TAC SCM log processing.
 *
 * @see se.sics.tasim.tac05.Parser
 */
public class OfferCounterParser extends Parser {

    private long[] offerCounter;

    public OfferCounterParser(LogReader reader) {
        super(reader);
        ParticipantInfo[] participants = reader.getParticipants();
        if (participants == null) {
            throw new IllegalStateException("no participants");
        }
        offerCounter = new long[participants.length];
    }

    public void printOfferCount() {
        LogReader reader = getReader();
        ParticipantInfo[] participants = reader.getParticipants();
        System.out.println("Offers sent in simulation " + reader.getSimulationID() + ':');
        for (int i = 0, n = participants.length; i < n; i++) {
            ParticipantInfo info = participants[i];
            int role = info.getRole();
            if (role == MANUFACTURER) {
                System.out.println("  Manufacturer " + info.getName() + " sent " + offerCounter[info.getIndex()] + " offers");
            } else if (role == SUPPLIER) {
                System.out.println("  Supplier " + info.getName() + " sent " + offerCounter[info.getIndex()] + " offers");
            }
        }
    }

    /**
   * Invoked when a message to a specific receiver is encountered in
   * the log file. Example of this is the offers sent by the
   * manufacturers to the customers.
   *
   * @param sender the sender of the message
   * @param receiver the receiver of the message
   * @param content the message content
   */
    protected void message(int sender, int receiver, Transportable content) {
        if (content instanceof OfferBundle) {
            OfferBundle bundle = (OfferBundle) content;
            if (sender < offerCounter.length) {
                offerCounter[sender] += bundle.size();
            }
        }
    }
}
