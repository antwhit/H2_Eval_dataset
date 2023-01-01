import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Emetteur extends Thread {

    private Tampon tamponDEmission;

    private Process process;

    private int numPort;

    private int idProcessDistant;

    public Emetteur(Process _process, int _numPort) {
        this.process = _process;
        this.tamponDEmission = this.process.getTamponDEmission();
        this.numPort = _numPort;
    }

    public Emetteur(Process _process, int _numPort, Tampon _tamponEmission, int _processDistant) {
        this.process = _process;
        this.tamponDEmission = _tamponEmission;
        this.numPort = _numPort;
        this.idProcessDistant = _processDistant;
    }

    @Override
    public void run() {
        try {
            Socket socket = null;
            ObjectOutputStream fluxDeSortie = null;
            boolean test = true;
            while (test) {
                socket = new Socket(InetAddress.getByName("localhost"), numPort);
                fluxDeSortie = new ObjectOutputStream(socket.getOutputStream());
                System.out.println(process.getIdProcess() + " : Emetteur  : Emeteur connecte au recepteur");
                Message message = this.tamponDEmission.getElementTampon();
                fluxDeSortie.writeObject(message);
                fluxDeSortie.flush();
                socket.close();
                MessageUtile messageTest = (MessageUtile) message;
                if (messageTest.getContenu().equals("Fin")) {
                    System.out.println("Fermeture du thread emetteur du processus " + process.getIdProcess());
                    test = false;
                }
            }
        } catch (ConnectException e) {
            System.err.println(e.getMessage());
        } catch (UnknownHostException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public int getIdProcessDistant() {
        return idProcessDistant;
    }

    public Process getProcess() {
        return process;
    }
}
