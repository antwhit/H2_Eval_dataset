import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author Markus Plessing
 * 
 */
public class HelpFinder {

    private File helpFile = new File(System.getProperty("user.dir") + "/doc/html/");

    private String findString;

    private String[] results;

    private StringBuffer result = new StringBuffer();

    /***/
    public DefaultMutableTreeNode top = new DefaultMutableTreeNode("Suchergebnisse", true);

    /**
     * @param find
     */
    public HelpFinder(String find) {
        findString = find;
        if (find.equals("")) {
            top.setUserObject("Keine Ergebnisse");
        } else {
            results = readDir(helpFile).split("#");
            for (int i = 0; i < results.length; i++) {
                if (!results[i].equals("")) {
                    int isHtml = results[i].indexOf(".");
                    int isSlash = results[i].lastIndexOf("/");
                    String nodeName = results[i].substring(isSlash + 1, isHtml);
                    top.add(new DefaultMutableTreeNode(new NodeInfo(nodeName, "file:///" + results[i])));
                } else {
                    top.setUserObject("Keine Ergebnisse");
                    break;
                }
            }
        }
    }

    /**
     * readDir : read the given Directory recursively and create a String[] - Object
     * @param dir File the Directory to read
     * @return String
     * @see java.io.File
     */
    public String readDir(File dir) {
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("not a directory: " + dir.getName());
        }
        File[] entries = dir.listFiles();
        for (int i = 0; i < entries.length; ++i) {
            if (!entries[i].isDirectory()) {
                if (entries[i].toString().indexOf(".html") > 0 && entries[i].toString().indexOf("index") < 0) {
                    if (searchFile(entries[i])) {
                        result.append(entries[i].toString()).append("#");
                    }
                }
            } else {
                readDir(entries[i]);
            }
        }
        return result.toString();
    }

    /**
     * 
     * @param file
     * @return boolean
     */
    public boolean searchFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String str;
            while ((str = reader.readLine()) != null) {
                if (str.indexOf(findString) >= 0) {
                    return true;
                }
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            new ErrorHandler(ex);
        }
        return false;
    }

    /**
     * 
     * @return JList
     */
    public JTree getTree() {
        final JTree tree = new JTree(top);
        tree.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node != null) {
                    if (node.getParent() != null) {
                        Object nodeInfo = node.getUserObject();
                        NodeInfo info = (NodeInfo) nodeInfo;
                        try {
                            HTMLHelp.getEditorPane().setPage(info.getFile());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            new ErrorHandler(ex);
                        }
                    }
                }
            }
        });
        return tree;
    }
}
