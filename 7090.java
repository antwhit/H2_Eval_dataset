import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;

/**
 * Experiments with DND and JTextPane Components
 * "Early Ghost"
 * @author kreed
 *
 */
public class VmcDND1 extends JFrame {

    JTextPane editor;

    private GhostGlassPane glassPane;

    GhostDropListener listener;

    public VmcDND1() {
        super("VMC Prototypen - Drag'n'Drop-Studien");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
        setSize(600, 720);
        setVisible(true);
    }

    private void initGUI() {
        glassPane = new GhostGlassPane();
        setGlassPane(glassPane);
        editor = new JTextPane();
        HTMLDocument doc = (HTMLDocument) editor.getEditorKitForContentType("text/html").createDefaultDocument();
        doc.setAsynchronousLoadPriority(-1);
        editor.setDocument(doc);
        File f = new File("e_sample.html");
        try {
            editor.setPage(f.toURL());
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        JScrollPane editorScroll = new JScrollPane(editor);
        listener = new GhostDropManagerDemo(editorScroll);
        JPanel jPanel = createFruitsPanel();
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JScrollPane fruitsScroll = new JScrollPane(jPanel);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(editorScroll);
        splitPane.setBottomComponent(fruitsScroll);
        splitPane.setResizeWeight(0.7);
        splitPane.setOneTouchExpandable(false);
        splitPane.setDividerSize(0);
        JPanel headerPanel = new JPanel(new BorderLayout());
        HeaderPanel header = new HeaderPanel();
        headerPanel.add(BorderLayout.NORTH, header);
        headerPanel.add(BorderLayout.SOUTH, new JSeparator(JSeparator.HORIZONTAL));
        headerPanel.setBorder(new EmptyBorder(0, 0, 6, 0));
        getContentPane().add(BorderLayout.NORTH, headerPanel);
        getContentPane().add(BorderLayout.CENTER, splitPane);
    }

    /**
	 * Panel which holds thumbnails of created histograms, 
	 * 	 distributions, soms and so on ... 
	 * @return JPanel
	 */
    private JPanel createFruitsPanel() {
        JPanel fruits = new JPanel(new GridLayout(1, 4));
        GhostPictureAdapter pictureAdapter;
        GhostComponentAdapter componentAdapter;
        JLabel label;
        label = UIHelper.createLabel("", "p1", "jpg");
        label.addMouseListener(pictureAdapter = new GhostPictureAdapter(glassPane, "dropTS_into_editorSpace", "images/p1.jpg"));
        pictureAdapter.addGhostDropListener(listener);
        label.addMouseMotionListener(new GhostMotionAdapter(glassPane));
        fruits.add(label);
        label = UIHelper.createLabel("", "p2", "jpg");
        label.addMouseListener(pictureAdapter = new GhostPictureAdapter(glassPane, "dropScatter_into_editorSpace", "images/p2.jpg"));
        pictureAdapter.addGhostDropListener(listener);
        label.addMouseMotionListener(new GhostMotionAdapter(glassPane));
        fruits.add(label);
        label = UIHelper.createLabel("", "p3", "jpg");
        label.addMouseListener(pictureAdapter = new GhostPictureAdapter(glassPane, "dropSOM_into_editorSpace", "images/p3.jpg"));
        pictureAdapter.addGhostDropListener(listener);
        label.addMouseMotionListener(new GhostMotionAdapter(glassPane));
        fruits.add(label);
        label = UIHelper.createLabel("", "p4", "jpg");
        label.addMouseListener(pictureAdapter = new GhostPictureAdapter(glassPane, "dropPDE_intro_editorSpace", "images/p4.jpg"));
        pictureAdapter.addGhostDropListener(listener);
        label.addMouseMotionListener(new GhostMotionAdapter(glassPane));
        fruits.add(label);
        return fruits;
    }

    public static void main(String[] args) {
        VmcDND1 app = new VmcDND1();
    }
}
