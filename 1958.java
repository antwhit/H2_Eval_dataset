import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.util.Properties;
import java.util.Observable;
import java.util.Observer;
import ie.omk.smpp.SMPPException;
import ie.omk.smpp.SmppTransmitter;
import ie.omk.smpp.SmppEvent;
import ie.omk.smpp.net.TcpLink;
import ie.omk.smpp.message.SMPPPacket;
import ie.omk.smpp.message.MsgFlags;
import ie.omk.smpp.message.SmeAddress;
import ie.omk.smpp.message.SubmitSMResp;
import ie.omk.smpp.message.BindTransmitterResp;
import ie.omk.smpp.util.GSMConstants;

/** Example class to submit a message to a SMSC.
  * This class simply binds to the server, submits a message and then unbinds.
  */
public class AsyncTransmitter implements java.util.Observer {

    public static final String PROPS_FILE = "smpp.properties";

    private static Properties props = null;

    public void update(Observable o, Object arg) {
        SmppTransmitter trans = (SmppTransmitter) o;
        SmppEvent ev = (SmppEvent) arg;
        SMPPPacket pak = ev.getPacket();
        System.out.println("Packet received: Id = " + Integer.toHexString(pak.getCommandId()));
        switch(pak.getCommandId()) {
            case SMPPPacket.ESME_BNDTRN_RESP:
                if (pak.getCommandStatus() != 0) {
                    System.out.println("Error binding to the SMSC. Error = " + pak.getCommandStatus());
                } else {
                    System.out.println("\tSuccessfully bound to SMSC \"" + ((BindTransmitterResp) pak).getSystemId() + "\".\n\tSubmitting message...");
                    send(trans);
                }
                break;
            case SMPPPacket.ESME_SUB_SM_RESP:
                if (pak.getCommandStatus() != 0) {
                    System.out.println("\tMessage was not submitted. Error code: " + pak.getCommandStatus());
                } else {
                    System.out.println("\tMessage Submitted! Id = " + ((SubmitSMResp) pak).getMessageId());
                }
                try {
                    trans.unbind();
                } catch (IOException x) {
                    System.err.println("\tUnbind error. Closing network " + "connection.");
                    x.printStackTrace(System.err);
                } catch (SMPPException x) {
                    x.printStackTrace(System.err);
                }
                break;
            case SMPPPacket.ESME_UBD_RESP:
                System.out.println("\tUnbound.");
                break;
            default:
                System.out.println("\tUnknown response received! Id = " + pak.getCommandId());
        }
    }

    public void send(SmppTransmitter trans) {
        try {
            String message = new String("Test Short Message. :-)");
            SmeAddress destination = new SmeAddress(GSMConstants.GSM_TON_UNKNOWN, GSMConstants.GSM_NPI_UNKNOWN, props.getProperty("esme.destination"));
            MsgFlags flags = new MsgFlags();
            trans.submitMessage(message, flags, destination);
        } catch (IOException x) {
            x.printStackTrace(System.err);
        } catch (SMPPException x) {
            x.printStackTrace(System.err);
        }
    }

    public static void main(String[] args) {
        try {
            AsyncTransmitter ex = new AsyncTransmitter();
            FileInputStream in = new FileInputStream(PROPS_FILE);
            ex.props = new Properties();
            ex.props.load(new BufferedInputStream(in));
            String server = props.getProperty("smsc.hostname", "localhost");
            String p = props.getProperty("smsc.port", "5432");
            int port = Integer.parseInt(p);
            TcpLink link = new TcpLink(server, port);
            SmppTransmitter trans = new SmppTransmitter(link, true);
            trans.addObserver(ex);
            trans.autoAckLink(true);
            trans.setSystemType(props.getProperty("esme.system_type"));
            trans.setSystemId(props.getProperty("esme.system_id"));
            trans.setPassword(props.getProperty("esme.password"));
            trans.bind();
        } catch (IOException x) {
            x.printStackTrace(System.err);
        } catch (NumberFormatException x) {
            System.err.println("Bad port number in properties file.");
            x.printStackTrace(System.err);
        } catch (SMPPException x) {
            System.err.println("SMPP exception: " + x.getMessage());
            x.printStackTrace(System.err);
        }
    }
}
