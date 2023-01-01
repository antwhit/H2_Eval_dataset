import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

public class Sprite extends Rectangle2D.Double implements Drawable {

    BufferedImage bild;

    final double SPEED = 400;

    double horizontalSpeed;

    double verticalSpeed;

    GamePanel spielfeld;

    /**
	 * der Konstruktor eines Blocks erfordert die angabe der xy- Position, der h�he, der weite
	 * und eines BufferedImage- array.
	 * @param _x X- position
	 * @param _y Y Position
	 * @param _h H�he
	 * @param _w Breite
	 * @param _bildAnimation das Animations- array
	 */
    public Sprite(double _x, double _y, double _h, double _w, BufferedImage _bild, GamePanel _spielfeld) {
        this.bild = _bild;
        this.height = _h;
        this.width = _w;
        this.x = _x;
        this.y = _y;
        this.spielfeld = _spielfeld;
    }

    /**
	 * Diese Methode sorgt f�r Bewegung des Blocks.
	 * Dabei wird die x position um die horizontale, und die
	 * y position um die vertikale geschwindigkeit erh�ht.
	 */
    public void bewegeDich(long delta) {
        if (this.getX() < (spielfeld.getWidth() - this.getWidth()) && this.getY() < (spielfeld.getHeight() - this.getHeight()) && this.getX() > 0) {
            this.x += horizontalSpeed * (delta / 1e9);
            this.y += verticalSpeed * (delta / 1e9);
        } else if (this.getX() > 0) {
            this.x -= 0.5;
        } else if (this.getX() < 0) {
            this.x += 0.5;
        }
    }

    /**
	 * Hier wird eine neue  Horizontale Geschwindigkeit gesetzt
	 * @param _newSpeed neue geschwindigkeit
	 */
    public void setHorizontalSpeed(double _newSpeed) {
        this.horizontalSpeed = _newSpeed;
    }

    /**
	 * Hier wird die neue Vertikale Geschwindigkeit gesetzt
	 * @param _newSpeed neue Vertikale geschwindigkeit
	 */
    public void setVerticalSpeed(double _newSpeed) {
        this.verticalSpeed = _newSpeed;
    }

    /**
	 * Diese Methode zeichnet das aktuelle animationsbild an die XY- Position, die das Block-
	 * objekt gerade hat.
	 * Die Methode stammt aus dem Interface Drawable
	 */
    public void zeichneDich(Graphics g) {
        g.drawImage(this.bild, (int) x, (int) y, null);
    }
}
