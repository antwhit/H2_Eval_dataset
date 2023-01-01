import java.awt.event.*;

public class HerramientaCopiar extends Herramienta {

    int x0, y0;

    boolean porBloques;

    public HerramientaCopiar(Colorator colorator) {
        super(colorator);
    }

    public boolean empezar(int x, int y, int botones) {
        if (!super.empezar(x, y, botones)) {
            return false;
        }
        if (!colorator.pantalla.seleccion.haySeleccion) {
            return false;
        }
        porBloques = botones == MouseEvent.BUTTON3;
        if (porBloques) {
            x = x - x % 8;
            y = y - y % 8;
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
        copiar(x, y);
        return true;
    }

    public boolean terminar(int x, int y) {
        if (!super.terminar(x, y)) {
            return false;
        }
        copiar(x, y);
        return true;
    }

    private void copiar(int x, int y) {
        if (porBloques) {
            x = x - x % 8;
            y = y - y % 8;
        }
        int desplX = x - x0;
        int desplY = y - y0;
        Pantalla pantalla = colorator.pantalla;
        Seleccion seleccion = colorator.pantalla.seleccion;
        for (int j = 0; j < Pantalla.BITMAP_Y; j++) {
            for (int i = 0; i < Pantalla.BITMAP_X; i++) {
                if (seleccion.haySeleccionXY(i, j)) {
                    pantalla.pintarPixel(i + desplX, j + desplY, pantalla.getBitmap(i, j), pantalla.getAttributePixel(i, j), colorator.modoTransparentePaper, colorator.modoTransparenteInk, colorator.modoTransparenteBright, false, true);
                }
            }
        }
    }
}
