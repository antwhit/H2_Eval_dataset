import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.UIManager;

public class Graphics extends JFrame {

    private static final long serialVersionUID = 1L;

    private JButton findWay, showConnection, newPlace, newConnection, changeConnection;

    private JPanel north, west, center;

    private JLabel lFrom, lTo;

    private JList fromDest, toDest;

    private JScrollPane scrollFromDest, scrollToDest;

    private JRadioButton selectByMap, selectByList;

    private JMenuBar theMenu;

    private JMenu arkiv, operationer;

    private JMenuItem nytt, avsluta;

    private JMenuItem mFindWay, mShowConnection, mNewPlace, mNewConnection, mChangeConnection;

    public Graphics() {
        super("Wayfinder 0.1");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        theMenu = new JMenuBar();
        arkiv = new JMenu("Arkiv");
        operationer = new JMenu("Operationer");
        nytt = new JMenuItem("Nytt");
        avsluta = new JMenuItem("Avsluta");
        mFindWay = new JMenuItem("Hitta v�g");
        mShowConnection = new JMenuItem("Visa f�rbindelse");
        mNewPlace = new JMenuItem("Ny plats");
        mNewConnection = new JMenuItem("Ny f�rbindelse");
        mChangeConnection = new JMenuItem("�ndra f�rbindelse");
        arkiv.add(nytt);
        arkiv.add(avsluta);
        operationer.add(mFindWay);
        operationer.add(mShowConnection);
        operationer.add(mNewPlace);
        operationer.add(mNewConnection);
        operationer.add(mChangeConnection);
        theMenu.add(arkiv);
        theMenu.add(operationer);
        north = new JPanel();
        findWay = new JButton("Hitta v�g");
        showConnection = new JButton("Visa f�rbindelse");
        newPlace = new JButton("Ny plats");
        newConnection = new JButton("Ny f�rbindelse");
        changeConnection = new JButton("�ndra f�rbindelse");
        north.add(findWay);
        north.add(showConnection);
        north.add(newPlace);
        north.add(newConnection);
        north.add(changeConnection);
        north.setLayout(new BoxLayout(north, BoxLayout.X_AXIS));
        west = new JPanel();
        lFrom = new JLabel("Fr�n:");
        lTo = new JLabel("Till:");
        fromDest = new JList();
        toDest = new JList();
        fromDest.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        fromDest.setLayoutOrientation(JList.VERTICAL);
        toDest.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        toDest.setLayoutOrientation(JList.VERTICAL);
        scrollFromDest = new JScrollPane(fromDest);
        scrollToDest = new JScrollPane(toDest);
        west.add(lFrom);
        west.add(scrollFromDest);
        west.add(lTo);
        west.add(scrollToDest);
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        this.setSize(800, 600);
        this.setJMenuBar(theMenu);
        this.add(north, BorderLayout.NORTH);
        this.add(west, BorderLayout.WEST);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
