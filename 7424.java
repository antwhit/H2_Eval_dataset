import java.io.IOException;
import jpcap.JpcapCaptor;

/** This Class creates a new JpcapCaptor instance with the default NetworkDevice
 * and listens for traffic on port 3074, only UDP packets will be intercepted.
 *
 * These packets are sent to the PacketHandler for processing.
 * */
public class PacketListener {

    public void startListener() throws IOException {
        NetworkDeviceList devicesToUse = new NetworkDeviceList();
        JpcapCaptor jpcaper = JpcapCaptor.openDevice(devicesToUse.returnDefault(), 4000, true, 20);
        jpcaper.setFilter("ether proto udp && src port 3074", true);
        PacketHandler packetSystem = new PacketHandler();
        jpcaper.loopPacket(-1, packetSystem);
    }
}
