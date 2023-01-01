/** low density planet
 * @author gldm
 * 
 */
public class LiquidPlanet extends Planet {

    /**
	 * @param x x position of the center
	 * @param y y position of the center
	 * @param r radius of the sphere
	 */
    public LiquidPlanet(double x, double y, int r) {
        super(x, y, r);
        density = 5;
        mass = 4 / 3 * Math.PI * Math.pow(radius, 3) * density;
    }
}
