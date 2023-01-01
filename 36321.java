import java.io.IOException;
import java.util.ArrayList;

public class Pere extends Thread {

    private Process process;

    private ArrayList<Tampon> tableauTamponEmission;

    private Tampon tamponDeTrace;

    private Tampon tamponDeReception;

    private HorlogeLamport horloge;

    private boolean testTirage = false;

    private int numTirage;

    public Pere(Process _process) {
        this.process = _process;
        tableauTamponEmission = this.process.getTableauTamponEmetteur();
        this.tamponDeTrace = this.process.getTamponDeTrace();
        this.tamponDeReception = this.process.getTamponDeReception();
        this.horloge = this.process.getHorloge();
    }

    @Override
    public void run() {
        MessageUtile message = null;
        boolean test = true;
        while (test) {
            System.out.println(process.getIdProcess() + " : Pere      : Attente d'un message ---------------------------> " + horloge.getCompteur());
            while (this.process.getTamponDeReception().getTampon().size() != 0) {
                receptionMessage();
            }
            if (message.getContenu().equals("Fin")) {
                System.out.println("Fermeture du thread p�re du processus " + process.getIdProcess());
                MessageUtile messageFin = new MessageUtile("Fin", 0, 0);
                test = false;
            } else {
                int numAleatoire;
                numAleatoire = (int) (Math.random() * 30);
                if (numAleatoire >= 0 && numAleatoire < 9) {
                } else if (numAleatoire >= 9 && numAleatoire < 18) {
                    int nbProcess = this.process.getTableauProcess().size() - 1;
                    int random = (int) (Math.random() * (nbProcess - 1)) + 1;
                    MessageUtile messageEnvoyer = new MessageUtile();
                    horloge.incrementeHorloge();
                    envoiMessage(messageEnvoyer, random);
                } else if (numAleatoire >= 18 && numAleatoire < 28) {
                    MessageUtile messageDiffusion = new MessageUtile();
                    horloge.incrementeHorloge();
                } else {
                    System.out.println(process.getIdProcess() + " : Pere      : Fermeture du SVG -------------------------------> " + horloge.getCompteur());
                    MessageUtile messageFin = new MessageUtile("Fin", 0, 0);
                }
            }
        }
    }

    public void envoiMessage(Message message, int idProcessDestinataire) {
        int indiceTabThreadEmetteur = 0;
        for (int i = 0; i < this.process.getTableauEmetteur().size(); i++) {
            if (this.process.getTableauEmetteur().get(i).getIdProcessDistant() == idProcessDestinataire) {
                indiceTabThreadEmetteur = i;
                i = this.process.getTableauEmetteur().size();
            }
        }
        tableauTamponEmission.get(indiceTabThreadEmetteur).setElementTampon(message);
        System.out.println(process.getIdProcess() + " : Pere      : Envoi d'un message -----------------------------> " + horloge.getCompteur());
    }

    public MessageUtile receptionMessage() {
        MessageUtile message = (MessageUtile) tamponDeReception.getElementTampon();
        horloge.incrementeHorloge(message.getEstampille());
        envoiTrace(message);
        System.out.println(process.getIdProcess() + " : Pere      : Reception d'un message -------------------------> " + horloge.getCompteur());
        System.out.println(this.process.getIdProcess() + " Recoit le message : Contenu du message" + message.getContenu() + " Id du process depart : " + message.getIdProcess() + " Estampille :" + message.getEstampille());
        return message;
    }

    public void envoiTrace(MessageUtile message) {
        System.out.println(process.getIdProcess() + " : Pere      : Tracer --> " + message);
        Message ordre = new MessageOrdre(message.getContenu(), message.getIdProcess(), this.process.getIdProcess(), message.getEstampille(), this.horloge.getCompteur());
        tamponDeTrace.setElementTampon(ordre);
        System.out.println(process.getIdProcess() + " : Pere      : Envoi de --> " + ordre);
    }

    public void changeTestTirage() {
        this.testTirage = true;
    }

    public void setNumTirage(int num) {
        this.numTirage = num;
    }
}
