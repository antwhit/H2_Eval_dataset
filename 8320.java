import java.io.*;
import java.net.*;

public class server {

    private Socket connection;

    public server() {
    }

    public void StartServer(int port, int q_length) {
        ServerSocket server;
        OutputStream output;
        try {
            System.out.println("Creating server socket...");
            server = new ServerSocket(port, q_length);
            connection = server.accept();
            System.out.println("Connection recieved...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String a[]) {
    }

    public int ReceiveMove() {
        int Position = 0;
        try {
            DataInputStream in = new DataInputStream(connection.getInputStream());
            Position = in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (Position);
    }

    public void SendMove(int Position) {
        try {
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(Position);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CloseServSock() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
