import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class MoveIt extends Applet implements ActionListener {

    private Button keysArray[];

    private Image cup;

    private Panel keyPad;

    public int top = 15;

    public int left = 15;

    private boolean foundKey;

    public void init() {
        cup = getImage(getDocumentBase(), "cup.gif");
        Canvas myCanvas = new Canvas();
        keyPad = new Panel();
        keysArray = new Button[5];
        keysArray[0] = new Button("Up");
        keysArray[1] = new Button("Left");
        keysArray[2] = new Button("Right");
        keysArray[3] = new Button("Down");
        keysArray[4] = new Button("Center");
        keysArray[0].setActionCommand("Up");
        keysArray[1].setActionCommand("Left");
        keysArray[2].setActionCommand("Right");
        keysArray[3].setActionCommand("Down");
        keysArray[4].setActionCommand("Center");
        setBackground(Color.blue);
        setLayout(new BorderLayout());
        keyPad.setLayout(new BorderLayout());
        keyPad.add(keysArray[0], BorderLayout.NORTH);
        keyPad.add(keysArray[1], BorderLayout.WEST);
        keyPad.add(keysArray[2], BorderLayout.EAST);
        keyPad.add(keysArray[3], BorderLayout.SOUTH);
        keyPad.add(keysArray[4], BorderLayout.CENTER);
        add(myCanvas, BorderLayout.NORTH);
        add(keyPad, BorderLayout.SOUTH);
        for (int i = 0; i < keysArray.length; i++) keysArray[i].addActionListener(this);
    }

    public void paint(Graphics g) {
        g.drawImage(cup, left, top, this);
    }

    public void actionPerformed(ActionEvent e) {
        String arg = e.getActionCommand();
        if (arg == "Up") top = top - 15;
        if (arg == "Left") left = left - 15;
        if (arg == "Right") left = left + 15;
        if (arg == "Down") top = top + 15;
        if (arg == "Center") {
            top = 60;
            left = 125;
        }
        repaint();
    }
}
