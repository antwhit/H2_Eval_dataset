import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recepteur extends Thread {

    private int numPort;

    private Process process;

    private Tampon tamponReception;

    public Recepteur(Process _process, int numPort) {
        this.process = _process;
        this.numPort = numPort;
        this.tamponReception = this.process.getTamponDeReception();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(numPort);
            boolean test = true;
            try {
                while (test) {
                    Socket socket = serverSocket.accept();
                    ObjectInputStream fluxDEntree = new ObjectInputStream(socket.getInputStream());
                    try {
                        Message message = (Message) fluxDEntree.readObject();
                        this.tamponReception.setElementTampon(message);
                        MessageUtile messageTest = (MessageUtile) message;
                        if (messageTest.getContenu().equals("Fin")) {
                            System.out.println("Fermeture du thread recepteur du processus " + process.getIdProcess());
                            test = false;
                        }
                    } catch (ClassNotFoundException e) {
                        System.err.println(e.getMessage());
                    }
                    socket.close();
                }
            } catch (IOException e2) {
                System.err.println(e2.getMessage());
            }
        } catch (IOException e1) {
            System.err.println(e1.getMessage());
        }
    }
}
