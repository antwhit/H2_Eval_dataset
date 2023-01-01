import java.awt.Component;

/**
 * Clase Timmy que extiende la clase Jugador
 *
 */
public class Timmy extends Jugador {

    /**
	 * Contructor de la clase Timmy
	 * @param x
	 * @param y
	 * @param parent
	 */
    public Timmy(int x, int y, Component parent) {
        super(x, y, parent);
    }

    /**
     * Metodo desaparecer
     * Metodo que hace invisible al usuario un objeto de la clase Timmy
     */
    public void desaparecer() {
        super.resetPlayer(-2, -2);
    }
}
