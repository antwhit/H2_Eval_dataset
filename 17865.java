import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class DeplacementBalle extends AbstractAction {

    public DeplacementBalle(ClassBalle gallery) {
        super();
        this.gallery = gallery;
    }

    private ClassBalle gallery;

    private int dx;

    public void actionPerformed(ActionEvent arg0) {
        dx = dx - 10;
        gallery.setxsprite(gallery.getxsprite() + dx);
        System.out.println("machin");
    }
}
