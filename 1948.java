import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/** parent class for the ship used by players
 * 
 * @author gldm
 *
 */
abstract class Ship extends SpaceObject {

    protected boolean direzione, colore;

    static final int ShipHeight = 55;

    static final int ShipWidth = 90;

    static final int ShipRadius = 35;

    protected boolean IsDestroyed;

    protected boolean IsSelected;

    protected Image img, xplode;

    /** constructor
	 * 
	 */
    public Ship(double x, double y) {
        super(x, y);
        try {
            xplode = ImageIO.read(getClass().getResource("exploding.jpg"));
        } catch (IOException e) {
        }
        IsDestroyed = false;
        IsSelected = false;
    }

    public abstract void doPaint(Graphics g, double ScaleFactor);

    /** tells if the ship has been hit by the rocket
	 * 
	 */
    public boolean checkCollision(double x, double y) {
        double dx2, dy2, sr2;
        dx2 = Math.pow((CenterX - x), 2);
        dy2 = Math.pow((CenterY - y), 2);
        sr2 = Math.pow(ShipRadius, 2);
        if ((dx2 + dy2) <= sr2) {
            return (true);
        } else {
            return (false);
        }
    }

    /** draw an explosion
	 * 
	 * @param g instance of Graphics2D
	 * @param ScaleFactor the name says all
	 */
    protected void explode(Graphics2D g, double ScaleFactor) {
        BufferedImage bimg = scale(ScaleFactor * ShipWidth / 60, (BufferedImage) xplode);
        TexturePaint texture = new TexturePaint(bimg, new Rectangle((int) (LeftBorder + ScaleFactor * (CenterX - ShipWidth)), (int) (UpperBorder + ScaleFactor * (CenterY - ShipWidth)), (int) (ScaleFactor * 2 * ShipWidth), (int) (ScaleFactor * 2 * ShipWidth)));
        g.setPaint(texture);
        try {
            g.fillRect((int) (LeftBorder + ScaleFactor * (CenterX - ShipWidth)), (int) (UpperBorder + ScaleFactor * (CenterY - ShipWidth)), (int) (ShipWidth * 2 * ScaleFactor), (int) (ShipWidth * 2 * ScaleFactor));
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            g.setColor(Color.yellow);
        }
    }

    /** sets the ship as destroyed
	 * 
	 */
    public void kaboom() {
        IsDestroyed = true;
        IsSelected = false;
    }

    /** 
 * 
 * @return true if it's the turn of corresponding player
 */
    public boolean isSelected() {
        return IsSelected;
    }

    public void setSelected(boolean isSelected) {
        if (!IsDestroyed) IsSelected = isSelected;
    }
}
