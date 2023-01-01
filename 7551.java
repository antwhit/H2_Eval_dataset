import net.sf.shox.simulator.Position;
import net.sf.shox.simulator.kernel.Configuration;
import net.sf.shox.simulator.movement.StartPositionGenerator;
import net.sf.shox.simulator.node.Node;
import net.sf.shox.xml.ConfigurationException;

public class FixedIDStartPosition extends StartPositionGenerator {

    /**
	 * xSize (from the Configuration).
	 */
    private double xSize;

    /**
	 * ySize (from the Configuration).
	 */
    private double ySize;

    /**
	 * {@inheritDoc}
	 */
    public void initConfiguration(Configuration config) throws ConfigurationException {
        super.initConfiguration(config);
        xSize = config.getXSize();
        ySize = config.getYSize();
    }

    /**
	 * @see net.sf.shox.simulator.movement.StartPositionGenerator.
	 * @param node The node for which the new position should be generated.
	 * @return A new position for a node
	 */
    @Override
    public final Position newPosition(Node node) {
        double x = node.getId().asInt() * xSize;
        x = Math.floor(x) / 100;
        double y = node.getId().asInt() * ySize;
        y = Math.floor(y) / 100;
        Position realPos = new Position(x, y);
        return realPos;
    }
}
