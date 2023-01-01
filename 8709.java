import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class Botao extends Sprite {

    int largura = 0;

    int altura = 0;

    BufferedImage img = null;

    boolean precionou = false;

    boolean bAction = false;

    int iAction = 0;

    public boolean isbAction() {
        return bAction;
    }

    public void setbAction(boolean bAction) {
        this.bAction = bAction;
    }

    public int getiAction() {
        return iAction;
    }

    public void setiAction(int iAction) {
        this.iAction = iAction;
    }

    public Botao(BufferedImage image, int x, int y, int largura, int altura) {
        X = x;
        Y = y;
        this.largura = largura;
        this.altura = altura;
        img = image;
    }

    @Override
    public void SimulaSe(int diftime) {
        if (!CanvasPrincipal.instance.CLICK) precionou = false;
        if (!precionou) if (CanvasPrincipal.instance.CLICK) {
            if (mouseBoudingBoxColision()) {
                setbAction(true);
                precionou = true;
            }
        }
    }

    @Override
    public void DesenhaSe(Graphics2D dbg, int xmundo, int ymundo) {
        dbg.drawImage(img, ((int) X - xmundo), ((int) Y - ymundo), largura, altura, null);
    }

    public boolean mouseBoudingBoxColision() {
        float left1 = X;
        float right1 = X + largura;
        float top1 = Y;
        float bottom1 = Y + altura;
        float left2 = CanvasPrincipal.instance.MouseX;
        float right2 = CanvasPrincipal.instance.MouseX + 1;
        float top2 = CanvasPrincipal.instance.MouseY;
        float bottom2 = CanvasPrincipal.instance.MouseY + 1;
        if (bottom1 < top2) return false;
        if (top1 > bottom2) return false;
        if (right1 < left2) return false;
        if (left1 > right2) return false;
        return true;
    }
}
