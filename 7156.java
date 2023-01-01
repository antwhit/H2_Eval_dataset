import java.io.IOException;

/**
 *
 * @author Nicolas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int numPortRecepteurProcess1 = 8082;
        int numPortRecepteurProcess2 = 8083;
        System.out.println("-- Création du Process 1");
        Process process1 = new Process(1, numPortRecepteurProcess1, numPortRecepteurProcess2);
        System.out.println("-- Création du Process 2");
        Process process2 = new Process(2, numPortRecepteurProcess2, numPortRecepteurProcess1);
        process2.lanceReception();
        process1.lanceEmission();
        process1.lancePere();
        process2.lancePere();
        MessageUtile messageUtile = new MessageUtile("REP", 1, 1);
        System.out.println("Envoi du message");
        process1.getPere().envoiMessage(messageUtile);
    }
}
