import waba.fx.Graphics;

public class VJUltimaWorldShapeMini extends VJUltimaWorldShape {

    private Graphics m_oGraphics = null;

    private int m_baShape[];

    private int m_iOffsetX;

    private int m_iOffsetY;

    private int m_iColumn;

    private int m_iLine;

    private int m_iSize;

    /**
 * Initializer of VJUltimaWorldShape.
 * <p>
 * The function computes the number of square to display from the width (w) and heigth (h) 
 * and rounded to the lowest nearest value. The computed value can be retrieved using line() 
 * and column() functions.
 * <p>
 * The function constructs the double buffer used in the graphic engine.
 *
 * @param x horizontal offset in pixels
 * @param y vertical offset in pixels
 * @param w width in pixels
 * @param h height in pixels
 * @param s size in pixels of a square (should be even and greater than 2, if not rounded to the lowest even number and not lower than 2)
 * @see #line
 * @see #column
 */
    public VJUltimaWorldShapeMini(int x, int y, int w, int h, int s) {
        super(0, 0, 0, 0);
        m_iSize = s - (s % 2);
        if (m_iSize < 2) m_iSize = 2;
        m_iOffsetX = x;
        m_iOffsetY = y;
        m_iColumn = w / m_iSize;
        m_iLine = h / m_iSize;
        m_baShape = new int[m_iColumn * m_iLine];
        for (int i = 0; i < (m_iColumn * m_iLine); i++) m_baShape[i] = VJ_SHAPE_UNKNOWN;
    }

    /**
 * Return the number of lines (expressed in tiles).
 */
    public int line() {
        return m_iLine;
    }

    /**
 * Return the number of columns (expressed in tiles).
 */
    public int column() {
        return m_iColumn;
    }

    /**
 * Draw a tile at the given position (expressed in tiles).
 * <P>
 * <LI>The VJ_SHAPE_WATER is drawn in blue.
 * <LI>The VJ_SHAPE_GRASS is drawn in green.
 * <LI>The VJ_SHAPE_FOREST is drawn in dark green.
 * <LI>The VJ_SHAPE_MOUNTAIN is drawn in light grau.
 * <LI>All other shapes (VJ_SHAPE_CASTLE, VJ_SAHPE_SIGN, VJ_SHAPE_TOWN, VJ_SHAPE_DUNGEON) are drawn in white.
 */
    public void drawC(int x, int y, int c) {
        int s;
        if (m_baShape[y * m_iColumn + x] != c) {
            m_baShape[y * m_iColumn + x] = c;
            if (m_oGraphics != null) {
                switch(c) {
                    case VJ_SHAPE_WATER:
                        m_oGraphics.setColor(0x00, 0x00, 0xFF);
                        break;
                    case VJ_SHAPE_GRASS:
                        m_oGraphics.setColor(0x00, 0xFF, 0x00);
                        break;
                    case VJ_SHAPE_FOREST:
                        m_oGraphics.setColor(0x00, 0x80, 0x00);
                        break;
                    case VJ_SHAPE_MOUNTAIN:
                        m_oGraphics.setColor(0xC0, 0xC0, 0xC0);
                        break;
                    default:
                        m_oGraphics.setColor(0xFF, 0xFF, 0xFF);
                }
                for (s = 0; s < (m_iSize / 2); s++) {
                    m_oGraphics.drawRect(x * m_iSize + m_iOffsetX + s, y * m_iSize + m_iOffsetY + s, m_iSize - s * 2, m_iSize - s * 2);
                }
            }
        }
    }

    /**
 * Process a onTics event.
 */
    public void onTics() {
    }

    /**
 * Process a onPaint event.
 */
    public void onPaint(Graphics g) {
        int c;
        m_oGraphics = g;
        for (int y = 0; y < m_iLine; y++) {
            for (int x = 0; x < m_iColumn; x++) {
                c = m_baShape[y * m_iColumn + x];
                m_baShape[y * m_iColumn + x] = VJ_SHAPE_UNKNOWN;
                drawC(x, y, c);
            }
        }
    }
}
