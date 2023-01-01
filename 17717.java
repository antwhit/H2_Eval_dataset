import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Cette classe permet d'afficher la fenêtre de promotion du pion.
 * Le joueur concerné pourra choisir la pièce qu'il désire
 *  
 **/
public class PromotionFenetre extends JFrame implements MouseListener, WindowListener {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private JPanel panel = new JPanel();

    private JLabel tour;

    private JLabel fou;

    private JLabel reine;

    private JLabel cavalier;

    final ImageIcon tourB = createImageIcon("/images2/tourb.png");

    final ImageIcon fouB = createImageIcon("/images2/foub.png");

    final ImageIcon cavalierB = createImageIcon("/images2/cavalierb.png");

    final ImageIcon reineB = createImageIcon("/images2/dameb.png");

    final ImageIcon tourN = createImageIcon("/images2/tourn.png");

    final ImageIcon fouN = createImageIcon("/images2/foun.png");

    final ImageIcon cavalierN = createImageIcon("/images2/cavaliern.png");

    final ImageIcon reineN = createImageIcon("/images2/damen.png");

    private boolean Blanc;

    private Alice a;

    private Piece p;

    private Fenetre fTmp;

    public PromotionFenetre(boolean blanc, Alice al, Piece pi, Fenetre f) {
        setLocation(350, 300);
        setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(1, 4));
        setTitle("Choix de la nouvelle Piece");
        setVisible(true);
        Blanc = blanc;
        a = al;
        p = pi;
        fTmp = f;
        fTmp.disable();
        if (blanc) {
            tour = new JLabel(tourB);
            reine = new JLabel(reineB);
            fou = new JLabel(fouB);
            cavalier = new JLabel(cavalierB);
        } else {
            tour = new JLabel(tourN);
            reine = new JLabel(reineN);
            fou = new JLabel(fouN);
            cavalier = new JLabel(cavalierN);
        }
        panel.add(tour);
        panel.add(fou);
        panel.add(cavalier);
        panel.add(reine);
        tour.addMouseListener(this);
        fou.addMouseListener(this);
        cavalier.addMouseListener(this);
        reine.addMouseListener(this);
        addWindowListener(this);
        this.getContentPane().add(panel);
        this.pack();
        this.setSize(350, 100);
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Alice.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tour) {
            if (Blanc) {
                System.out.println(p);
                p.setM_type("tb");
            } else p.setM_type("tn");
        } else if (e.getSource() == fou) {
            if (Blanc) p.setM_type("fb"); else p.setM_type("fn");
        } else if (e.getSource() == cavalier) {
            if (Blanc) p.setM_type("cb"); else p.setM_type("cn");
        } else if (e.getSource() == reine) {
            if (Blanc) p.setM_type("rb"); else p.setM_type("rn");
        }
        a.getLayeredPane().removeAll();
        a.afficherPiecesJoueurs(false);
        a.getLayeredPane().repaint();
        System.out.println("dans promF" + p.getM_type());
        this.dispose();
        fTmp.enable();
    }

    public void windowClosing(WindowEvent e) {
        close();
    }

    public void close() {
        dispose();
        fTmp.enable();
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }
}
