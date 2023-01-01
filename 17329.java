import java.io.*;
import java.util.*;
import javax.comm.*;

public class CommPort {

    CommPortIdentifier portId;

    Enumeration portList;

    InputStream inputStream;

    OutputStream outputStream;

    SerialPort serialPort;

    static final int MAXSIZE = 256;

    public void Init(int portno) throws IOException {
        String portname = "/dev/ttyS" + portno;
        System.out.println("Open " + portname);
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(portname)) {
                    return;
                }
            }
        }
        throw (new IOException("Can't find port"));
    }

    public CommPort(int portno, int baudrate, int data_bits, int stop_bits, int parity) throws IOException {
        Init(portno);
        try {
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (PortInUseException e) {
            System.out.println("Port is in use!");
            throw (new IOException("Port in use"));
        }
        inputStream = serialPort.getInputStream();
        outputStream = serialPort.getOutputStream();
        try {
            serialPort.setSerialPortParams(baudrate, data_bits, stop_bits, parity);
            serialPort.setInputBufferSize(1024);
        } catch (UnsupportedCommOperationException e) {
        }
    }

    public void close() {
        serialPort.close();
        System.out.println("comm port closed");
    }

    public void serialSend(int nBuf) throws IOException {
        byte[] buffer = new byte[4];
        for (int i = 0; i < 4; i++) {
            buffer[3 - i] = (byte) ((nBuf >> 8 * i) & 0xFF);
        }
        serialSend(buffer, 4);
    }

    public void serialSend(byte[] buf, int length) throws IOException {
        for (int i = 0; i < length; i++) {
            byte ch = buf[i];
            try {
                outputStream.write((int) (ch & 0xff));
            } catch (IOException e) {
                System.out.println("Error while sending");
            }
        }
    }

    public byte[] serialReceiveInt() throws IOException {
        return serialReceive(4);
    }

    public byte[] serialReceive(int size) throws IOException {
        int bytesRead = 0;
        int offset = 0;
        byte[] readBuffer = new byte[size];
        while (bytesRead < size) {
            bytesRead += inputStream.read(readBuffer, offset, (size - bytesRead));
            offset = bytesRead;
        }
        return (bytesRead > 0) ? readBuffer : null;
    }

    public byte[] serialReceive() throws IOException {
        int bytesRead = 0;
        byte[] readBuffer = new byte[MAXSIZE];
        while (inputStream.available() > 0) {
            bytesRead = inputStream.read(readBuffer);
        }
        return (bytesRead > 0) ? readBuffer : null;
    }
}
