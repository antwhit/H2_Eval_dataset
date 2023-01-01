import java.awt.Dimension;
import java.util.Properties;
import java.util.StringTokenizer;
import org.grafki.math.ComplexNumber;
import org.grafki.math.ComplexRectangle;
import fi.hip.gb.utils.CaselessProperties;

/**
 * Properties for mandelbrot images.
 * <p>
 * Can contain following keys (case intensive):
 * <ul>
 * <li>AREA=x,y,z,w (with double values, defines real and complex values)
 * <li>RESOLUTION=x,y (integers, size of the image, by default 500x500)
 * <li>ITERATIONS=x (integer, how many iterations maximum, 1000 be default)
 * <li>SLICES=x (integer, says how many different slices)
 * </ul>
 *
 * <p>
 * Multiple items are separated with semicolon or linebreak
 *
 * @author Juho Karppinen
 * @version $Id: MandelbrotProperties.java 102 2004-11-12 14:31:37Z jkarppin $
 */
public class MandelbrotProperties extends CaselessProperties {

    /**
	 * Load case insensitive properties from old properties
	 * @param prop properties
	 */
    public MandelbrotProperties(Properties prop) {
        super(prop);
    }

    /**
	 * Define the image properties
	 * @param area area to be drawn
	 * @param resolution resolotion of the image
	 * @param iterations how many iterations
	 * @param slices how many slices there are
	 */
    public MandelbrotProperties(ComplexRectangle area, Dimension resolution, int iterations, int slices) {
        setProperty("AREA", area.getLowerLeftCorner().getReal() + "," + area.getLowerLeftCorner().getComplex() + "," + area.getUpperRightCorner().getReal() + "," + area.getUpperRightCorner().getComplex());
        setProperty("RESOLUTION", new Double(resolution.getWidth()).intValue() + "," + new Double(resolution.getHeight()).intValue());
        setProperty("ITERATIONS", Integer.toString(iterations));
        setProperty("SLICES", Integer.toString(slices));
    }

    /**
	 * Do we have already defined all needed parameter to start processing 
	 * @return true if defined, false otherwise
	 */
    public boolean isDefined() {
        return getProperty("RESOLUTION") != null && getProperty("AREA") != null && getProperty("ITERATIONS") != null;
    }

    /**
	 * Gets the image resolution
	 * @return dimension of the image
	 */
    public Dimension getResolution() {
        String str = getProperty("RESOLUTION", "500,500");
        StringTokenizer st = new StringTokenizer(str, ",", false);
        return new Dimension(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    /**
	 * Gets the area covered
	 * @return Rectangle of the area
	 */
    public ComplexRectangle getArea() {
        String str = getProperty("AREA", "-1.0,1,1.5,-1");
        StringTokenizer st = new StringTokenizer(str, ",", false);
        double[] area = new double[4];
        for (int i = 0; st.hasMoreElements(); i++) area[i] = Double.parseDouble(st.nextToken());
        return new ComplexRectangle(new ComplexNumber(area[0], area[1]), new ComplexNumber(area[2], area[3]));
    }

    /**
	 * Gets maximum amouth of iterations
	 * @return iterations as integer
	 */
    public int getIterations() {
        String str = getProperty("ITERATIONS", Integer.toString(100));
        return Integer.parseInt(str);
    }

    /**
	 * Gets number of slices
	 * @return number of slices
	 */
    public int getSlices() {
        String str = getProperty("SLICES", Integer.toString(1));
        return Integer.parseInt(str);
    }
}
