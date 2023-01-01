import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/** the parent class of almost every other object in the battle field
 * 
 * @author gldm
 *
 */
public abstract class SpaceObject {

    protected double CenterX, CenterY;

    static final int UpperBorder = 0;

    static final int LeftBorder = 0;

    public SpaceObject(double x, double y) {
        CenterX = x;
        CenterY = y;
    }

    public double posX() {
        return (CenterX);
    }

    public double posY() {
        return (CenterY);
    }

    public abstract boolean checkCollision(double x, double y);

    /** gets the x position of the object
	 * 
	 * @return CenterX the x position of the object
	 */
    public double getCenterX() {
        return CenterX;
    }

    /** sets the x position of the object
 * 
 * @param centerX the x position of the object
 */
    public void setCenterX(double centerX) {
        CenterX = centerX;
    }

    /** gets the y position of the object
 * 
 * @return the y position of the object
 */
    public double getCenterY() {
        return CenterY;
    }

    /** sets the y position of the object
 * 
 * @param centerY the y position of the object
 */
    public void setCenterY(double centerY) {
        CenterY = centerY;
    }

    public static BufferedImage scale(double scale, BufferedImage srcImg) {
        if (scale == 1) {
            return srcImg;
        }
        AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(scale, scale), null);
        return op.filter(srcImg, null);
    }
}
