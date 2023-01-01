import java.awt.Color;

/**
 *
 * @author Cseki
 */
public class PlainDestinationView extends DestinationView {

    public PlainDestinationView(Destination da, int x_, int y_) {
        d = da;
        x = x_;
        y = y_;
        d.setView(this);
    }

    public void createView() {
    }

    public void notifyView() {
        gr.setColor(new Color(100, 100, 100));
        gr.fillRect(x * 30, y * 30, 30, 30);
        if (d.getVehicle() != null) {
            d.getVehicle().getView().notifyView();
        }
    }
}
