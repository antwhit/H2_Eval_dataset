import computational.geometry.raycrosssegmentset.*;

/**
 * @author Massimo Bartoletti
 * @version 1.1
 */
public class BSPTreeRayCrossSegmentSetCanvas extends RayCrossSegmentSetCanvas {

    public BSPTreeRayCrossSegmentSetCanvas(CGDesktop desktop) {
        super(desktop, new BSPTreeRayCrossSegmentSet());
    }
}
