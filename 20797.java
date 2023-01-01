import java.awt.Color;
import java.awt.Graphics2D;

public class Particula extends Sprite {

    public float velx = 0;

    public float vely = 0;

    public boolean vivo = false;

    public int timer = 0;

    public int tempodevida = 0;

    public Color cor = null;

    public int cr, cg, cb;

    int alpha = 0;

    public Particula(float X, float Y, float velx, float vely, int tempodevida, Color cor) {
        this.X = X;
        this.Y = Y;
        this.velx = velx;
        this.vely = vely;
        this.tempodevida = tempodevida;
        this.cor = cor;
        vivo = true;
        cr = cor.getRed();
        cg = cor.getGreen();
        cb = cor.getBlue();
    }

    @Override
    public void SimulaSe(int diftime) {
        timer += diftime;
        X += velx * diftime / 1000.0f;
        Y += vely * diftime / 1000.0f;
        if (timer > tempodevida) {
            vivo = false;
        }
        alpha = (int) (255 - (255 * (timer / (float) tempodevida)));
    }

    @Override
    public void DesenhaSe(Graphics2D dbg, int xmundo, int ymundo) {
        cor = new Color(cr, cg, cb, alpha);
        dbg.setColor(cor);
        dbg.fillRect((int) (X - xmundo - 1), (int) (Y - ymundo - 1), 3, 3);
    }
}
