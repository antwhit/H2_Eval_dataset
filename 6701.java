import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButtonMenuItem;

public class RepresentationItem extends JRadioButtonMenuItem implements ActionListener {

    private Representation repr;

    public RepresentationItem(Representation repr) {
        addActionListener(this);
        this.repr = repr;
        if (repr == null) {
            setText("Quiver");
        } else {
            setText(repr.getName());
        }
    }

    public Representation getRepresentation() {
        return repr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RepresentationItem selectedItem = (RepresentationItem) e.getSource();
        Representation repr = selectedItem.getRepresentation();
        QuiverFrame.getInstance().getActivePanel().setActiveRepresentation(repr);
    }
}
