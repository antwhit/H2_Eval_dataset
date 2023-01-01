import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ResourceList extends Box implements ResourceActivationListener {

    public class ResourceItem extends Box {

        protected Resource r;

        protected Vector raListeners = new Vector();

        public ResourceItem(Resource r) {
            super(BoxLayout.X_AXIS);
            this.r = r;
            add(new JLabel(r.getIcon()));
            add(new JLabel(r.getStringIdentifier()));
            addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    notifyClick();
                }
            });
        }

        public void addResourceActivationListener(ResourceActivationListener l) {
            raListeners.add(l);
        }

        public void notifyClick() {
            for (Iterator i = raListeners.iterator(); i.hasNext(); ) ((ResourceActivationListener) i.next()).resourceActivated(r);
        }
    }

    protected Vector raListeners = new Vector();

    public ResourceList() {
        super(BoxLayout.Y_AXIS);
    }

    public void addResourceActivationListener(ResourceActivationListener l) {
        raListeners.add(l);
    }

    public void resourceActivated(Resource r) {
        for (Iterator i = raListeners.iterator(); i.hasNext(); ) ((ResourceActivationListener) i.next()).resourceActivated(r);
    }

    public void addResource(Resource r) {
        ResourceItem ri = new ResourceItem(r);
        ri.addResourceActivationListener(this);
        add(ri);
        revalidate();
    }
}
