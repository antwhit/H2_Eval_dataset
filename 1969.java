import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    public static final int FIELD_HEIGHT = 15;

    public static final int FIELD_WIDTH = 10;

    public static GamePanel gp;

    private InputManager input = new InputManager();

    private Cursor cursor = new Cursor(input);

    private GameThread gt = new GameThread(input, cursor, this);

    public GamePanel() {
        super();
        gp = this;
        this.addKeyListener(input);
        this.setFocusable(true);
        this.requestFocus();
        new Thread(gt).start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Block b : EntityManager.entities) if (b != null) {
            g.setColor(Color.RED);
            g.fillRect(b.getGrX(), b.getGrY(), b.getImg().getWidth(this), b.getImg().getHeight(this));
            g.drawImage(b.getImg(), b.getGrX(), b.getGrY(), null);
        }
    }
}
