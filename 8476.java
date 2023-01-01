import java.net.URL;
import java.io.*;
import org.apache.xpath.XPath;
import org.w3c.dom.Document;
import hypercast.*;

public class HelloWorld_NoConfigFile implements I_ReceiveCallback {

    String MyString = new String("Hello World");

    I_OverlaySocket MySocket = null;

    HyperCastConfig ConfObj = null;

    public static void main(String[] args) {
        HelloWorld_NoConfigFile hw = new HelloWorld_NoConfigFile();
    }

    public void ReceiveCallback(I_OverlayMessage msg) {
        byte[] data = msg.getPayload();
        String Src = msg.getSourceAddress().toString();
        if (!msg.getSourceAddress().equals(MySocket.getLogicalAddress())) System.out.println("Received <" + new String(data) + "> from logical address: " + Src + ".");
    }

    public HelloWorld_NoConfigFile() {
        URL MyServer = null;
        try {
            MyServer = new URL("http://128.100.11.52:8080");
        } catch (IOException e) {
        }
        HyperCastConfig ConfObj = new HyperCastConfig(MyServer, "142.150.142.113:8080TS1158957201906");
        System.out.println("Downloaded configuration.");
        MySocket = ConfObj.createOverlaySocket(this);
        System.out.println("Created overlay socket.");
        MySocket.joinOverlay();
        String MyLogicalAddress = MySocket.getLogicalAddress().toString();
        System.out.println("Logical address is " + MyLogicalAddress + ".");
        for (int i = 1; i < 100; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            byte[] MyData = MyString.getBytes();
            I_OverlayMessage MyMessage = MySocket.createMessage(MyData);
            MySocket.sendToAll(MyMessage);
            System.out.println("<HelloWorld> message sent to other members ...");
        }
        MySocket.closeSocket();
    }
}
