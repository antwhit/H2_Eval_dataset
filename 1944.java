import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Properties;

public class PingTestMac extends PingTest {

    public PingTestMac(HashMap params) {
        super(params);
    }

    public boolean onCorrectPlatform(Properties sysprop) {
        try {
            String osname = sysprop.getProperty("os.name").toLowerCase();
            return osname.contains("mac");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean returnsCommand() {
        return true;
    }

    public String getCommand() {
        return "ping -c " + getParam("count") + " -s " + getParam("packetSize") + " " + getParam("target");
    }

    public String toString() {
        return "PingTest MacOS";
    }
}
