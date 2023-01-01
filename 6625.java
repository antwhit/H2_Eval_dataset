import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;

public class Comm {

    InetAddress address;

    int port;

    ControlData cdata;

    Data data;

    boolean running;

    byte[] inBuffer;

    byte[] outBuffer;

    String toSend;

    String received;

    DatagramSocket socket;

    DatagramPacket packet;

    MessageParser parser;

    public Comm(InetAddress a, int p, Data d, ControlData cd) throws Exception {
        address = a;
        port = p;
        data = d;
        cdata = cd;
        socket = new DatagramSocket();
        running = false;
    }

    public void handshake() throws Exception {
        socket.setSoTimeout(1000);
        while (running == false) {
            received = "";
            while (received.compareTo("") == 0) {
                toSend = "wcci2008";
                outBuffer = toSend.getBytes();
                socket.send(new DatagramPacket(outBuffer, outBuffer.length, address, port));
                inBuffer = new byte[1024];
                packet = new DatagramPacket(inBuffer, inBuffer.length);
                try {
                    socket.receive(packet);
                    received = new String(packet.getData(), 0, packet.getLength());
                } catch (SocketTimeoutException s) {
                    received = "";
                }
            }
            if (received.trim().compareTo("***identified***") == 0) {
                running = true;
            }
        }
        socket.setSoTimeout(100000);
    }

    public void update() throws Exception {
        toSend = "(accel " + cdata.getAccel() + ") (brake " + cdata.getBrake() + ") (gear " + cdata.getGear() + ") (steer " + cdata.getSteer() + ") (meta 0)";
        outBuffer = toSend.getBytes();
        socket.send(new DatagramPacket(outBuffer, outBuffer.length, address, port));
        inBuffer = new byte[1024];
        packet = new DatagramPacket(inBuffer, inBuffer.length);
        socket.receive(packet);
        received = new String(packet.getData(), 0, packet.getLength());
        if (received.trim().compareTo("***shutdown***") == 0) {
            running = false;
        } else {
            parser = new MessageParser(received, data);
        }
        data.incTSS();
    }

    public boolean isRunning() {
        return running;
    }

    public void restart() throws Exception {
        toSend = "(accel " + cdata.getAccel() + ") (brake " + cdata.getBrake() + ") (gear " + cdata.getGear() + ") (steer " + cdata.getSteer() + ") (meta 1)";
        outBuffer = toSend.getBytes();
        socket.send(new DatagramPacket(outBuffer, outBuffer.length, address, port));
        inBuffer = new byte[1024];
        packet = new DatagramPacket(inBuffer, inBuffer.length);
        socket.receive(packet);
        received = new String(packet.getData(), 0, packet.getLength());
        inBuffer = new byte[1024];
        packet = new DatagramPacket(inBuffer, inBuffer.length);
        socket.receive(packet);
        received = new String(packet.getData(), 0, packet.getLength());
        while (running == true) {
            if (received.trim().compareTo("***shutdown***") == 0) {
                running = false;
            } else if (received.trim().compareTo("***restart***") == 0) {
                running = false;
            } else {
                inBuffer = new byte[1024];
                packet = new DatagramPacket(inBuffer, inBuffer.length);
                socket.receive(packet);
                received = new String(packet.getData(), 0, packet.getLength());
                parser = new MessageParser(received, data);
            }
        }
    }
}
