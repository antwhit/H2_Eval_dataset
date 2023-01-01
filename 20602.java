import javax.swing.*;
import java.io.IOException;
import java.awt.*;

public class Dijkstra extends JFrame {

    DjNetViewer netViewer;

    /** Main constructor */
    public Dijkstra() {
        netViewer = new DjNetViewer(this);
        try {
            netViewer.loadFile("dijkstra.net");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Cannot load dijkstra.net\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setTitle("Dijkstra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(netViewer);
        pack();
    }

    /** Main function */
    public static void main(String[] args) {
        final Dijkstra f = new Dijkstra();
        f.setVisible(true);
    }
}
