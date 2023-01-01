import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

public class Loja extends Sprite {

    int largura = 0;

    int altura = 0;

    BufferedImage image = null;

    ArrayList<Iten> ListaDeItens = new ArrayList<Iten>();

    boolean ehInvetorio = false;

    boolean adicionaIten = false;

    boolean persAdicionaIten = false;

    public Loja(BufferedImage img, int x, int y, int largura, int altura) {
        this.image = img;
        this.X = x;
        this.Y = y;
        this.largura = largura;
        this.altura = altura;
    }

    public void pegaIten(ArrayList<Iten> list) {
        for (int i = 0; i < ListaDeItens.size(); i++) {
            Iten iten = (Iten) ListaDeItens.get(i);
            System.out.println("iten add" + iten.image);
            if (iten.isbAction()) {
                System.out.println("iten add" + iten.image);
                iten.setbAction(false);
                list.add(iten);
            }
        }
    }

    public void SimulaSe(int diftime) {
        for (int i = 0; i < ListaDeItens.size(); i++) {
            Iten iten = ListaDeItens.get(i);
            iten.SimulaSe(diftime);
            if (iten.isbAction()) {
                iten.setbAction(false);
            }
        }
    }

    @Override
    public void DesenhaSe(Graphics2D dbg, int xmundo, int ymundo) {
        dbg.drawImage(image, (int) X, (int) Y, largura, altura, null);
        for (int i = 0; i < ListaDeItens.size(); i++) {
            Iten iten = ListaDeItens.get(i);
            iten.DesenhaSe(dbg, xmundo, ymundo);
        }
    }

    public void inserePotionLife(int x, int y) {
        Iten iten = new Iten(x, y, GamePanel.instance.potionLife, 20, 20, 0, true);
        ListaDeItens.add(iten);
        iten = null;
    }

    public void inserePotionMana(int x, int y) {
        Iten iten = new Iten(x, y, GamePanel.instance.potionMana, 20, 20, 0, true);
        ListaDeItens.add(iten);
        iten = null;
    }

    public void addIten(Iten iten) {
        ListaDeItens.add(iten);
    }
}
