import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonListener implements ActionListener {

    GrapherJPanel myGrapherJPanel;

    ButtonListener(GrapherJPanel grapherJPanel) {
        super();
        myGrapherJPanel = grapherJPanel;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("plotGraph")) {
            myGrapherJPanel.repaint();
        }
    }
}
