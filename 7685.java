import java.io.*;
import java.lang.*;
import java.math.*;
import gnu.io.*;
import java.util.TooManyListenersException;

public class IOPackSerialController extends SketchProtocolTransport implements SerialPortEventListener {

    public static final String DISABLED_INTERFACE = "(disable)";

    public static final int PORT_OPEN_TIMEOUT = 100;

    /***********************************/
    private boolean started = false;

    private CommPortIdentifier myPortID;

    private SerialPort myPort = null;

    public IOPackSerialController(int code, SketchProtocolListener lsnr) {
        super(code, lsnr);
    }

    public void start(String portName) throws UnsupportedCommOperationException, AlreadyStartedException, NoSuchPortException, TooManyListenersException, PortInUseException, IOException {
        if (started) throw new AlreadyStartedException();
        if (portName.toLowerCase().equals(IOPackSerialController.DISABLED_INTERFACE)) return;
        myPortID = CommPortIdentifier.getPortIdentifier(portName);
        openPort();
        started = true;
    }

    public void stop() {
        if (!started) return;
        closePort();
        started = false;
    }

    private void openPort() throws IOException, TooManyListenersException, PortInUseException, UnsupportedCommOperationException {
        System.out.println("IOPackSerialController: opening port...");
        myPort = (SerialPort) myPortID.open("IOPackSerialController", PORT_OPEN_TIMEOUT);
        synchronized (myPort) {
            myPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            myPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE | SerialPort.FLOWCONTROL_NONE);
            myPort.notifyOnBreakInterrupt(true);
            myPort.notifyOnCarrierDetect(true);
            myPort.notifyOnCTS(true);
            myPort.notifyOnDataAvailable(true);
            myPort.notifyOnDSR(true);
            myPort.notifyOnFramingError(true);
            myPort.notifyOnOutputEmpty(true);
            myPort.notifyOnOverrunError(true);
            myPort.notifyOnParityError(true);
            myPort.notifyOnRingIndicator(true);
            myPort.addEventListener(this);
            setIOStreams(myPort.getInputStream(), myPort.getOutputStream());
        }
        if (myPort == null) {
            System.err.println("IOPackSerialController: open port FAILED");
        } else {
            System.out.println("IOPackSerialController: open port SUCCEEDED");
        }
    }

    /**
	 * close the port
	 *
	 */
    private void closePort() {
        System.out.println("IOPackSerialController: closing port");
        if (myPort == null) return;
        try {
            myPort.close();
        } catch (Exception e) {
        }
        myPort = null;
        setIOStreams(null, null);
    }

    public boolean isStarted() {
        return started;
    }

    /**
	 * from SerialPortEventListener
	 *
	 */
    public void serialEvent(SerialPortEvent ev) {
        switch(ev.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE:
                readInputBuffer();
                break;
            case SerialPortEvent.BI:
                break;
            case SerialPortEvent.CD:
                break;
            case SerialPortEvent.CTS:
                break;
            case SerialPortEvent.DSR:
                break;
            case SerialPortEvent.FE:
                break;
            case SerialPortEvent.OE:
                break;
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.PE:
                break;
            case SerialPortEvent.RI:
                break;
        }
    }
}
