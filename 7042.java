import java.awt.*;

/**
 *
 * @author gldm
 */
class Rettangolo extends OggettoGrafico {

    int x1, y1;

    /**
   * Constructor
   * @param x0
   * @param y0
   * @param x1
   * @param y1
   * @param nome
   */
    public Rettangolo(int x0, int y0, int x1, int y1, String nome) {
        super(x0, y0, nome);
        codice = 4;
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
   *
   * @param g
   * @param scalafactor
   */
    public void Draw(Graphics g, double scalafactor) {
        int x0_temp = (int) (x0 * scalafactor) + x_shift;
        int y0_temp = (int) (y0 * scalafactor) + y_shift;
        int x1_temp = (int) (x1 * scalafactor) + x_shift;
        int y1_temp = (int) (y1 * scalafactor) + y_shift;
        int base = x1_temp - x0_temp;
        int altezza = y1_temp - y0_temp;
        int dimensione_font = (int) (14 * scalafactor);
        if (colore == true) g.setColor(Color.red); else g.setColor(Color.black);
        g.drawRect(x0_temp, y0_temp, base, altezza);
        g.setFont(new Font("Courier", Font.PLAIN, dimensione_font));
        g.drawString(etichetta, x1_temp + 3, y0_temp - 3);
        if (scalafactor == 1) {
            fome = g.getFontMetrics();
            lung_string = fome.stringWidth(etichetta);
        }
    }

    /**
   *
   * @return
   */
    @Override
    public int GetXPosition() {
        return ((int) ((x0 + x1) / 2));
    }

    /**
   *
   * @return
   */
    @Override
    public int GetYPosition() {
        return ((int) ((y0 + y1) / 2));
    }

    /**
   *
   * @param x
   * @param y
   * @return
   */
    public boolean IsHere(int x, int y) {
        int xm = (int) ((x1 + x0) / 2);
        int ym = (int) ((y1 + y0) / 2);
        if (((x <= xm + 15) && (x > xm - 15)) && ((y <= ym + 15) && (y > ym - 15))) return true; else return false;
    }
}
