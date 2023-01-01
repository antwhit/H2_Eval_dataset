import javax.swing.*;
import java.awt.geom.*;
import java.util.*;
import java.awt.*;
import java.lang.Math;

/**
 * The hexagonal shape of the Idon
 * 
 * @author Sean Talbot
 * @version (a version number or a date)
 */
public class Hexagon extends JPanel {

    private HexSize size;

    private HexCoordinates coordinates;

    private HashMap<Direction, Hexagon> neighbours;

    /**
     * Create a new hexagon at position 0,0,0
     * (i.e. the first hexagon)
     */
    public Hexagon(HexSize size) {
        this.size = size;
        coordinates = new HexCoordinates(0, 0, 0);
    }

    /**
     * Hexagon. @param size: See Enum Class HexSize
     * for details.
     */
    public Hexagon(HexSize size, Integer x, Integer y, Integer z) {
        this.size = size;
        coordinates = new HexCoordinates(x, y, z);
    }

    /**
     * Specify a pre-existing hexcoordinates object
     * instead of Integers.
     */
    public Hexagon(HexSize size, HexCoordinates hC) {
        this.size = size;
        coordinates = hC;
    }

    /**
     * Make the hexagon visible.
     */
    public void draw() {
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
    }

    private void initialise() {
    }

    /**
     * Returns the hexagon's size.
     */
    public HexSize getHexSize() {
        return size;
    }
}
