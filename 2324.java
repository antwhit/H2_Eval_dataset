import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author Stefan Kornemann
 *
 */
public class MainFrame extends JFrame implements MouseListener {

    /**
	 * 
	 */
    public MainFrame() {
        super();
        this.setTitle("PortALa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tree = new JTree();
        tree.setModel(new DefaultTreeModel(this.set_Tree("C:\\PortableApps")));
        tree.addMouseListener(this);
        this.add(new JScrollPane(tree));
        this.add(tree);
        pack();
    }

    JTree tree;

    private DefaultMutableTreeNode set_Tree(String Dir) {
        DirWorker dw = new DirWorker(Dir);
        ArrayList<File> progs = dw.get_Programs();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode((new File(Dir)).getAbsolutePath());
        DefaultMutableTreeNode dirNode = null;
        boolean isNewDirectory = false;
        for (File file : progs) {
            if (isNewDirectory) {
                root.add(dirNode);
                isNewDirectory = false;
            }
            if (file.isDirectory()) {
                dirNode = new DefaultMutableTreeNode(file.getAbsoluteFile());
                isNewDirectory = true;
            } else {
                DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file.getAbsoluteFile());
                dirNode.add(fileNode);
            }
        }
        return root;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JTree tree = (JTree) e.getSource();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
            if (((File) node.getUserObject()).isFile()) {
                try {
                    Runtime.getRuntime().exec(((File) node.getUserObject()).getAbsoluteFile().toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error while starting application", JOptionPane.OK_OPTION);
                }
            }
        }
        pack();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
