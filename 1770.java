import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import net.sourceforge.steelme.*;

/**
 *  Simple app demonstrating steelme themes.
 *
 *@author     TJ Willis
 *@created    May 29, 2004
 */
public class NewDemo extends JFrame implements ActionListener {

    /**
	 *  Constructor for the NewDemo object
	 */
    public NewDemo() {
        super("steelme Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Box box = new Box(BoxLayout.Y_AXIS);
        JLabel lab = new JLabel("Simple steelme Demo");
        JButton but = new JButton("Show Dialog Button");
        but.addActionListener(this);
        JRadioButton rad = new JRadioButton("Radio Button");
        JCheckBox check = new JCheckBox("Checkbox");
        String[] listitems = { "one", "two", "three", "four" };
        JList list = new JList(listitems);
        JComboBox combo = new JComboBox(listitems);
        JSlider slid = new JSlider(0, 100);
        JTextField field = new JTextField("Blah, blah, blah");
        box.add(lab);
        JPanel btnBox = new JPanel();
        Border bord = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        bord = BorderFactory.createTitledBorder(bord, "Buttons");
        btnBox.setBorder(bord);
        btnBox.setLayout(new BoxLayout(btnBox, BoxLayout.Y_AXIS));
        btnBox.add(but);
        btnBox.add(rad);
        btnBox.add(check);
        box.add(btnBox);
        JPanel listBox = new JPanel();
        Border bord2 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        bord2 = BorderFactory.createTitledBorder(bord2, "Lists");
        listBox.setBorder(bord2);
        listBox.setLayout(new BoxLayout(listBox, BoxLayout.X_AXIS));
        listBox.add(list);
        listBox.add(combo);
        box.add(listBox);
        JPanel thirdBox = new JPanel();
        Border bord3 = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        bord3 = BorderFactory.createTitledBorder(bord3, "Other");
        thirdBox.setBorder(bord3);
        thirdBox.setLayout(new BoxLayout(thirdBox, BoxLayout.Y_AXIS));
        thirdBox.add(slid);
        JScrollPane sp = new JScrollPane(field);
        thirdBox.add(sp);
        box.add(thirdBox);
        getContentPane().add(box);
        JMenuBar menubar = new JMenuBar();
        File g = new File("resources/themes");
        JMenu men = new ThemeMenu(getRootPane(), g);
        menubar.add(men);
        setJMenuBar(menubar);
        pack();
        setVisible(true);
    }

    /**
	 *  The main program for the NewDemo class
	 *
	 *@param  args  The command line arguments
	 */
    public static void main(String[] args) {
        new NewDemo();
    }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "This is what a dialog looks like", "Sample Dialog", JOptionPane.INFORMATION_MESSAGE);
    }
}
