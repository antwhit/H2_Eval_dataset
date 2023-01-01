import cytoscape.util.CytoscapeAction;
import cytoscape.Cytoscape;
import cytoscape.view.CytoscapeDesktop;
import cytoscape.CyNetwork;
import giny.model.Node;
import giny.view.GraphView;
import giny.view.NodeView;
import cytoscape.*;
import cytoscape.data.CyAttributes;
import java.util.*;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Paint;
import java.awt.Color;
import java.io.File;
import javax.swing.*;

public class GetNeighboursAction extends CytoscapeAction {

    /**
     * The constructor sets the text that should appear on the menu item.
     */
    public GetNeighboursAction() {
        super("Get Hypothetical Neighbours of selected nodes");
    }

    /**
     * This method is called when the user selects the menu item.
     */
    public void actionPerformed(ActionEvent ae) {
        final CyNetwork network = Cytoscape.getCurrentNetwork();
        final Set nodes = network.getSelectedNodes();
        final ArrayList reactions = new ArrayList();
        String species = "";
        for (Iterator i = nodes.iterator(); i.hasNext(); ) {
            CyNode node = (CyNode) i.next();
            reactions.add(node.getIdentifier());
            if (species == "") {
                CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();
                species = nodeAttributes.getStringAttribute(node.getIdentifier(), "species");
            }
        }
        final String speciesFinal = species;
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                AbstractDatabaseConnect databaseConnect = (AbstractDatabaseConnect) new DatabaseConnect();
                databaseConnect.getHypotheticalNeighbours(reactions, speciesFinal, network.getIdentifier());
            }
        });
    }
}
