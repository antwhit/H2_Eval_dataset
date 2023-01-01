import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Rules extends JFrame implements ActionListener {

    public ClientApplet CA;

    JTextArea textArea;

    JScrollPane content;

    JButton btnClose;

    String lobbyName;

    public Rules(String lobbyName) {
        int rows = 7;
        int cols = 2;
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        int top, left, bottom, right;
        top = left = bottom = right = 20;
        panel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        String rulesText = "These are the Rules for Reversi\n\n...\n...\n...\n...";
        textArea = new JTextArea(rulesText);
        content = new JScrollPane(textArea);
        panel.add(content);
        add(panel);
        panel.setVisible(true);
        setSize(500, 300);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e1) {
        JButton button = (JButton) e1.getSource();
        if (button.equals(btnClose)) {
            this.dispose();
        }
    }
}
