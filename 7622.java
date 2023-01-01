import net.tinyos.message.*;
import net.tinyos.util.*;
import java.io.*;

public class Oscilloscope implements MessageListener {

    MoteIF mote;

    Data data;

    Window window;

    int interval = Constants.DEFAULT_INTERVAL;

    int version = -1;

    void run() {
        data = new Data(this);
        window = new Window(this);
        window.setup();
        mote = new MoteIF(PrintStreamMessenger.err);
        mote.registerListener(new OscilloscopeMsg(), this);
    }

    void newNode(int nodeId) {
        window.newNode(nodeId);
    }

    public synchronized void messageReceived(int dest_addr, Message msg) {
        if (msg instanceof OscilloscopeMsg) {
            OscilloscopeMsg omsg = (OscilloscopeMsg) msg;
            periodUpdate(omsg.get_version(), omsg.get_interval());
            data.update(omsg.get_id(), omsg.get_count(), omsg.get_readings());
            window.newData();
        }
    }

    void periodUpdate(int moteVersion, int moteInterval) {
        if (moteVersion > version) {
            version = moteVersion;
            interval = moteInterval;
            window.updateSamplePeriod();
        } else if (moteVersion < version) {
            sendInterval();
        }
    }

    synchronized boolean setInterval(int newPeriod) {
        if (newPeriod < 1 || newPeriod > 65535) {
            return false;
        }
        interval = newPeriod;
        version++;
        sendInterval();
        return true;
    }

    void sendInterval() {
        OscilloscopeMsg omsg = new OscilloscopeMsg();
        omsg.set_version(version);
        omsg.set_interval(interval);
        try {
            mote.send(MoteIF.TOS_BCAST_ADDR, omsg);
        } catch (IOException e) {
            window.error("Cannot send message to mote");
        }
    }

    void clear() {
        data = new Data(this);
    }

    public static void main(String[] args) {
        Oscilloscope me = new Oscilloscope();
        me.run();
    }
}
