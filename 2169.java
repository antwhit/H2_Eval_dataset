import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import TCP.TCP_Client;
import TCP.TCP_Client_Callbacks;

public class TCP_IM_Client extends TCP_Client_Callbacks implements Runnable {

    private TCP_Client cli;

    private Thread sendThread;

    private String name;

    public TCP_IM_Client(String userName, InetAddress addr) throws IOException {
        name = userName;
        cli = new TCP_Client(addr, 864, this);
        sendThread = new Thread(this);
        sendThread.start();
    }

    public void run() {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.println("--------------- Chat started ------------------");
        while (!sendThread.isInterrupted()) {
            if (scan.hasNext()) {
                String message = scan.next().trim();
                if (message.equalsIgnoreCase(".quit")) break;
                message = name + ": " + message;
                byte[] sendBuff = new byte[message.length()];
                for (int i = 0; i < sendBuff.length; i++) {
                    sendBuff[i] = (byte) (((int) message.charAt(i)) & 0xff);
                }
                try {
                    cli.getSocket().getOutputStream().write(sendBuff);
                    cli.getSocket().getOutputStream().flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("---------------- Chat ended -------------------");
        disconnect();
    }

    private void disconnect() {
        try {
            cli.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DataReceived(byte[] data) {
        char[] charArray = new char[data.length];
        for (int i = 0; i < data.length; i++) charArray[i] = (char) data[i];
        System.out.println(new String(charArray));
    }

    public void ReceiveException(Exception e) {
        e.printStackTrace();
    }
}
