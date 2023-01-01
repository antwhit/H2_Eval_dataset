import java.awt.event.*;
import javax.swing.*;

public class ActChrono extends AbstractAction {

    PanelChronoV chrono;

    PanelReboursV rebours;

    PanelAlarmeV alarme;

    JPanel masquer;

    ApplicationV fenetre;

    public ActChrono(PanelChronoV c, PanelReboursV r, PanelAlarmeV a, JPanel m, ApplicationV f) {
        super("Chronometre");
        chrono = c;
        rebours = r;
        alarme = a;
        masquer = m;
        fenetre = f;
    }

    public void actionPerformed(ActionEvent e) {
        if (!chrono.isVisible()) {
            chrono.setVisible(true);
            alarme.setVisible(false);
            rebours.setVisible(false);
            masquer.setVisible(true);
            fenetre.setSizeFenetre(350);
        }
    }
}
