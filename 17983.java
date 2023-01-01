import java.awt.event.*;

public class Linea extends Herramienta {

    int x0, y0;

    public Linea(Colorator colorator) {
        super(colorator);
    }

    public boolean empezar(int x, int y, int botones) {
        if (!super.empezar(x, y, botones)) {
            return false;
        }
        x0 = x;
        y0 = y;
        return true;
    }

    public boolean movido(int x, int y) {
        if (!super.movido(x, y)) {
            return false;
        }
        colorator.pantalla.copiarDe((Pantalla) (colorator.undo.elementAt(colorator.indexUndo)));
        pintarLinea(x0, y0, x, y);
        return true;
    }

    public boolean terminar(int x, int y) {
        if (!super.terminar(x, y)) {
            return false;
        }
        pintarLinea(x0, y0, x, y);
        return true;
    }

    public void pintarLinea(int x0, int y0, int x1, int y1) {
        boolean tinta = true;
        if (botones == MouseEvent.BUTTON3) {
            tinta = false;
        }
        colorator.pantalla.pintarPixel(x0, y0, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
        if (x0 == x1 && y0 == y1) {
            return;
        }
        int dx = x1 - x0;
        int dy = y1 - y0;
        int incX = 1;
        if (x1 < x0) {
            incX = -1;
        }
        int incY = 1;
        if (y1 < y0) {
            incY = -1;
        }
        if (dy == 0) {
            int x = x0;
            do {
                colorator.pantalla.pintarPixel(x, y0, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
                x += incX;
            } while (x != x1);
            colorator.pantalla.pintarPixel(x, y0, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
            return;
        }
        if (dx == 0) {
            int y = y0;
            do {
                colorator.pantalla.pintarPixel(x0, y, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
                y += incY;
            } while (y != y1);
            colorator.pantalla.pintarPixel(x0, y, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
            return;
        }
        if (dx < 0) {
            dx = -dx;
        }
        if (dy < 0) {
            dy = -dy;
        }
        int x = x0;
        int y = y0;
        if (dx > dy) {
            int acum = dx / 2;
            do {
                x += incX;
                acum += dy;
                if (acum >= dx) {
                    y += incY;
                    acum -= dx;
                }
                colorator.pantalla.pintarPixel(x, y, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
            } while (x != x1);
        } else {
            int acum = dy / 2;
            do {
                y += incY;
                acum += dx;
                if (acum >= dy) {
                    x += incX;
                    acum -= dy;
                }
                colorator.pantalla.pintarPixel(x, y, tinta, colorator.attrsActual, colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, false);
            } while (y != y1);
        }
    }
}
