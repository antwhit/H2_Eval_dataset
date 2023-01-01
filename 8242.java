import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.jgraph.JGraph;

/**
	 * The Class VESTEventRedirector.
	 */
class VESTEventRedirector extends AbstractAction {

    /** The action. */
    protected Action action;

    /** The graph. */
    private JGraph graph;

    /**
		 * Instantiates a new vEST event redirector.
		 * 
		 * @param a the a
		 * @param parentGraph the parent graph
		 */
    public VESTEventRedirector(Action a, JGraph parentGraph) {
        super("", (ImageIcon) a.getValue(Action.SMALL_ICON));
        this.action = a;
        graph = parentGraph;
    }

    public void actionPerformed(ActionEvent e) {
        e = new ActionEvent(graph, e.getID(), e.getActionCommand(), e.getModifiers());
        action.actionPerformed(e);
    }
}
