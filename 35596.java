import java.awt.event.*;

public class LapizAtributo extends Herramienta {

    int xAnt, yAnt;

    public LapizAtributo(Colorator colorator) {
        super(colorator);
    }

    public boolean empezar(int x, int y, int botones) {
        super.empezar(x, y, botones);
        pintarAtributo(x, y);
        xAnt = x;
        yAnt = y;
        return true;
    }

    public boolean movido(int x, int y) {
        if (!super.movido(x, y)) {
            return false;
        }
        pintarAtributo(x, y);
        xAnt = x;
        yAnt = y;
        return true;
    }

    public boolean terminar(int x, int y) {
        if (!super.terminar(x, y)) {
            return false;
        }
        pintarAtributo(x, y);
        return true;
    }

    public void pintarAtributo(int x, int y) {
        boolean tinta = true;
        if (botones == MouseEvent.BUTTON3) {
            tinta = false;
        }
        colorator.pantalla.pintarPixel(x, y, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, true, false);
    }
}
