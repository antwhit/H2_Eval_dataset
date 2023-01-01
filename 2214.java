import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tampon {

    private ArrayList<Message> tampon;

    private int taille = 3;

    private Semaphore mutex = new Semaphore(1);

    private Semaphore vide = new Semaphore(taille);

    private Semaphore plein = new Semaphore(0);

    private Process process;

    public Tampon(Process _process) {
        this.tampon = new ArrayList<Message>();
        this.process = _process;
    }

    public Message getElementTampon() {
        Message message = null;
        try {
            plein.acquire();
            mutex.acquire();
            message = tampon.remove(0);
            mutex.release();
            vide.release();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Tampon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public void setElementTampon(Message message) {
        try {
            vide.acquire();
            mutex.acquire();
            tampon.add(message);
            mutex.release();
            plein.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tampon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Message> getTampon() {
        return this.tampon;
    }

    public int size() {
        return tampon.size();
    }
}
