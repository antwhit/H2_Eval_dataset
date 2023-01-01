import cytoscape.util.CytoscapeAction;
import cytoscape.Cytoscape;
import cytoscape.view.CytoscapeDesktop;
import cytoscape.CyNetwork;
import giny.model.Node;
import giny.view.GraphView;
import giny.view.NodeView;
import java.util.*;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Paint;
import java.awt.Color;
import java.io.File;

public class OccurenceAction extends CytoscapeAction {

    /**
     * The constructor sets the text that should appear on the menu item.
     */
    public OccurenceAction() {
        super("Occurence colouring");
    }

    /**
     * This method is called when the user selects the menu item.
     */
    public void actionPerformed(ActionEvent ae) {
        File preferences = new File("preferences.xml");
        float hue = 0f;
        if (preferences.exists()) {
            hue = 0.3f;
        }
        CyNetwork network = Cytoscape.getCurrentNetwork();
        ArrayList nodeIDs = new ArrayList();
        for (Iterator i = network.nodesIterator(); i.hasNext(); ) {
            Node node = (Node) i.next();
            nodeIDs.add(node.getIdentifier());
        }
        AbstractDatabaseConnect databaseConnect = (AbstractDatabaseConnect) new DatabaseConnect();
        Hashtable node2OrthologousEvent = databaseConnect.getOrthologIDForColoring(nodeIDs);
        int max = 0;
        Integer maxInteger = (Integer) node2OrthologousEvent.get("max");
        max = maxInteger.intValue();
        for (Iterator it = network.nodesIterator(); it.hasNext(); ) {
            Node node = (Node) it.next();
            String nodeID = node.getIdentifier();
            if (node2OrthologousEvent.containsKey(nodeID)) {
                ArrayList orthologousEvents = (ArrayList) node2OrthologousEvent.get(nodeID);
                int value = orthologousEvents.size();
                NodeView nodeView = Cytoscape.getCurrentNetworkView().getNodeView(node);
                float preValue = 1 / (float) max;
                float satValue = preValue * (float) value;
                Color nodeColor = new Color(Color.HSBtoRGB(hue, satValue, (float) 1));
                nodeView.setUnselectedPaint((Paint) nodeColor);
            } else {
                NodeView nodeView = Cytoscape.getCurrentNetworkView().getNodeView(node);
                Color nodeColor = new Color(Color.HSBtoRGB(hue, (float) 0, (float) 1));
                nodeView.setUnselectedPaint((Paint) nodeColor);
            }
        }
        Cytoscape.getCurrentNetworkView().updateView();
    }
}
