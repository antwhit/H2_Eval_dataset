import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket server = serverSocket.accept();
                System.out.println("accept");
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
                    String str = in.readLine();
                    System.out.println("read:" + str);
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())), true);
                    out.println("server message");
                    out.close();
                    in.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                } finally {
                    server.close();
                    System.out.println("close");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String a[]) {
        Thread desktopServerThread = new Thread(new Server());
        desktopServerThread.start();
        System.out.println("hahahahah");
    }
}
