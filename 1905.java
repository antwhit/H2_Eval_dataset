import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HelloSwing2 extends JApplet {

    JLabel imgLabel;

    public void init() {
        JButton bttn = new JButton("Click Me!");
        bttn.setSize(100, 20);
        imgLabel = new JLabel("Hey here is a card");
        imgLabel.setSize(100, 100);
        bttn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                String title = "Greetings";
                String message = "Another hello from Swing.";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                String imagePath = System.getProperty("user.dir") + "\\ch4\\Images\\cards\\2s.gif";
                System.out.println(imagePath);
                ImageIcon icon = new ImageIcon(imagePath);
                imgLabel.setIcon(icon);
            }
        });
        getContentPane().add(bttn);
        getContentPane().add(imgLabel);
    }
}
