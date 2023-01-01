import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Hashtable;

public class ThemeManager extends DefaultHandler {

    private Toolkit tk;

    private String parseEnCours;

    private Image ball;

    private Image paddle;

    private Image paddleShoot;

    private Image bullet;

    private Image background;

    private Image net;

    private Image foreground;

    private Hashtable bricks;

    private Hashtable bonus;

    public ThemeManager(Toolkit tk) {
        this.tk = tk;
        init("default");
    }

    public ThemeManager(Toolkit tk, String theme) {
        this.tk = tk;
        init(theme);
    }

    private void init(String theme) {
        bricks = new Hashtable();
        bonus = new Hashtable();
        DefaultHandler handler = this;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File("themes/" + theme + ".xml"), handler);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) {
        String eName = sName;
        if ("".equals(eName)) eName = qName;
        if (eName == "ball") this.ball = tk.createImage(attrs.getValue("src"));
        if (eName == "foreground") this.foreground = tk.createImage(attrs.getValue("src"));
        if (eName == "paddle") {
            String type = attrs.getValue("type");
            if (type.compareTo("shoot") == 0) {
                this.paddleShoot = tk.createImage(attrs.getValue("src"));
            } else {
                this.paddle = tk.createImage(attrs.getValue("src"));
            }
        }
        if (eName == "bullet") {
            this.bullet = tk.createImage(attrs.getValue("src"));
        }
        if (eName == "net") {
            this.net = tk.createImage(attrs.getValue("src"));
        }
        if (eName == "background") this.background = tk.createImage(attrs.getValue("src"));
        if (eName == "bonus") this.bonus.put(attrs.getValue("type"), tk.createImage(attrs.getValue("src")));
        if (eName == "brick") {
            int life = new Integer(attrs.getValue("life")).intValue();
            if (this.bricks.containsKey(attrs.getValue("type"))) {
                Image[] brickImages = (Image[]) this.bricks.get(attrs.getValue("type"));
                brickImages[life] = tk.createImage(attrs.getValue("src"));
            } else {
                Image[] brickImages = new Image[5];
                brickImages[life] = tk.createImage(attrs.getValue("src"));
                this.bricks.put(attrs.getValue("type"), brickImages);
            }
        }
    }

    public Image getBall() {
        return this.ball;
    }

    public Image getPaddle() {
        return this.paddle;
    }

    public Image getPaddleShoot() {
        return this.paddleShoot;
    }

    public Image getBullet() {
        return this.bullet;
    }

    public Image getBackground() {
        return this.background;
    }

    public Image getNet() {
        return this.net;
    }

    public Image getBrick(String nomBrick, int life) {
        Image[] brickImages = (Image[]) this.bricks.get(nomBrick);
        return brickImages[life];
    }

    public Image getBonus(String nomBonus) {
        return (Image) this.bonus.get(nomBonus);
    }

    public Image getForeground() {
        return this.foreground;
    }
}
