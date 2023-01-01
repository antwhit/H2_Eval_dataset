import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class NoMule extends JFrame implements ActionListener {

    public static final String ver = "2008.05.04";

    static int y = 0;

    static LinkedList<MediaProvider> lMediaProvider;

    JButton bAdd;

    JButton bRemove;

    Container c;

    public NoMule() {
        super("NoMule " + ver);
        c = this.getContentPane();
        c.setLayout(null);
        NoMulePanel p = new NoMulePanel();
        p.setLocation(0, 0);
        c.add(p);
        bAdd = new JButton("+");
        bAdd.setLocation(650, p.getHeight());
        bAdd.setSize(50, 20);
        bAdd.addActionListener(this);
        c.add(bAdd);
        bRemove = new JButton("-");
        bRemove.setLocation(590, p.getHeight());
        bRemove.setSize(50, 20);
        bRemove.addActionListener(this);
        c.add(bRemove);
        this.setSize(710, p.getHeight() + 50);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        NoMule n = new NoMule();
        lMediaProvider = new LinkedList<MediaProvider>();
        lMediaProvider.add(new YoutubeMediaProvider());
        lMediaProvider.add(new MyVideoMediaProvider());
        lMediaProvider.add(new YouPornMediaProvider());
        lMediaProvider.add(new GVideoMediaProvider());
        lMediaProvider.add(new DailyMotionMediaProvider());
        lMediaProvider.add(new RedTubeMediaProvider());
        lMediaProvider.add(new PornoTubeMediaProvider());
        lMediaProvider.add(new XTubeMediaProvider());
        lMediaProvider.add(new Tube8MediaProvider());
        lMediaProvider.add(new PornHubMediaProvider());
        lMediaProvider.add(new TimTubeMediaProvider());
        lMediaProvider.add(new KabelEinsMediaProvider());
        lMediaProvider.add(new ProSiebenMediaProvider());
        lMediaProvider.add(new SatEinsMediaProvider());
        lMediaProvider.add(new SpiegelOnlineMediaProvider());
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(bAdd)) {
            NoMulePanel p = new NoMulePanel();
            p.setLocation(0, y + p.getHeight());
            y += p.getHeight();
            c.add(p);
            bAdd.setLocation(650, y + p.getHeight());
            bRemove.setLocation(590, y + p.getHeight());
            this.setResizable(true);
            this.setSize(710, y + p.getHeight() + 50);
            this.setResizable(false);
        }
        if (e.getSource().equals(bRemove)) {
            NoMulePanel p = (NoMulePanel) c.getComponent(c.getComponentCount() - 1);
            y -= p.getHeight();
            c.remove(p);
            bRemove.setLocation(590, y + p.getHeight());
            bAdd.setLocation(650, y + p.getHeight());
            this.setResizable(true);
            this.setSize(710, y + p.getHeight() + 50);
            this.setResizable(false);
        }
    }
}
