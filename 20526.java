import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;
import java.net.NetworkInterface;

public class SipSession {

    DatagramSocket socket;

    int localPort;

    String localAddress;

    String localUser;

    String localTag;

    String remoteUser;

    String remoteAddress;

    int remotePort;

    String remoteTag = null;

    SipSession(DatagramSocket socket, String localUser, String remoteUser, String remoteAddress, int remotePort) throws UnknownHostException {
        this.socket = socket;
        this.localUser = localUser;
        InetAddress localInetAddress = getLocalIPAddress();
        localAddress = localInetAddress.getHostAddress();
        localPort = socket.getLocalPort();
        Random randomGenerator = new Random();
        localTag = Integer.toHexString(randomGenerator.nextInt(Integer.MAX_VALUE));
        this.remoteUser = remoteUser;
        this.remoteAddress = remoteAddress;
        this.remotePort = remotePort;
    }

    /** Get my IPAddress from interfaces list
	 * @return
	 * @throws UnknownHostException 
	 */
    InetAddress getLocalIPAddress() throws UnknownHostException {
        InetAddress inetAddress1 = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface interfacep = interfaces.nextElement();
                if (interfacep.isLoopback()) continue;
                if (!interfacep.supportsMulticast()) continue;
                Enumeration<InetAddress> inetAddresses = interfacep.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!(inetAddress instanceof Inet4Address)) continue;
                    System.out.println("IP:" + inetAddress.getHostAddress() + " " + inetAddress.getHostName() + " ");
                    if (inetAddress1 == null) {
                        inetAddress1 = inetAddress;
                        return inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
        }
        if (inetAddress1 == null) {
            inetAddress1 = InetAddress.getLocalHost();
        }
        return inetAddress1;
    }

    String createOptionsHeader() {
        return "OPTIONS sip:" + remoteUser + "@" + remoteAddress + " SIP/2.0\r\n" + "Via: SIP/2.0/UDP " + localAddress + ":" + localPort + ";branch=z9hG4bK.26c0e292;rport;alias\r\n" + "From: sip:SipTest@" + localAddress + ":" + localPort + ";tag=4e33dece\r\n" + "To: " + remoteUser + "@" + remoteAddress + "\r\n" + "Call-ID: 1295248006@127.0.0.1\r\n" + "CSeq: 1 OPTIONS\r\n" + "Contact: sip:" + localUser + "@" + localAddress + ":" + localPort + " \r\n" + "Content-Length: 0\r\n" + "Max-Forwards: 70\r\n" + "User-Agent: SipTest 1.0\r\n" + "Accept: text/plain,application/octet-stream\r\n" + "\r\n";
    }
}
