import computational.geometry.pointlocation.*;

public class KirkpatrickCanvas extends PointLocationCanvas {

    private static final long serialVersionUID = 417289512977935177L;

    public KirkpatrickCanvas(CGDesktop desktop) {
        super(desktop, new Kirkpatrick());
    }
}
