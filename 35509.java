import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Properties;

/**
 * Uses the Ping command native to windows to collect the average latency and
 * the packetloss to a certain target.
 * 
 */
public class PingTestWindows extends PingTest {

    public PingTestWindows(HashMap params) {
        super(params);
    }

    public boolean onCorrectPlatform(Properties sysprop) {
        try {
            String osname = sysprop.getProperty("os.name").toLowerCase();
            return osname.contains("windows");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean returnsCommand() {
        return true;
    }

    public String getCommand() {
        return "ping -n " + getParam("count") + " -l " + getParam("packetSize") + " " + getParam("target");
    }

    public String toString() {
        return "PingTest Windows";
    }
}
