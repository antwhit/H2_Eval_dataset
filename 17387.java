import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;

/**
 * La classe du bouton checkbox "Afficher le dernier coup" dans le menu
 */
public class AffichDerCoupAction extends AbstractAction {

    public AffichDerCoupAction(String titre) {
        super(titre);
    }

    public void actionPerformed(ActionEvent e) {
        boolean selected = ((JCheckBoxMenuItem) e.getSource()).getState();
        System.out.println("Vous avez cliqué sur le bouton checkbox \"Afficher le dernier coup\" => coché: " + selected);
    }
}
