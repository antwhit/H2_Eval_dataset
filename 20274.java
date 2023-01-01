import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Seoyoung extends Frame implements ActionListener {

    JButton course, sche;

    public Seoyoung() {
        super("Seoyoung");
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        Panel center = new Panel();
        Panel pcourse = new Panel();
        ImageIcon cm = new ImageIcon("images/student.png");
        pcourse.add(course = new JButton(cm));
        course.addActionListener(this);
        Panel psche = new Panel();
        ImageIcon se = new ImageIcon("images/professor.png");
        psche.add(sche = new JButton(se));
        sche.addActionListener(this);
        center.add(pcourse);
        center.add(psche);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                System.exit(0);
            }
        });
        add("Center", center);
    }

    public void actionPerformed(ActionEvent e) {
        JButton c = (JButton) e.getSource();
        try {
            if (c == course) {
                Seoyoung2 seoyoung2 = new Seoyoung2();
                seoyoung2.setSize(800, 500);
                seoyoung2.setVisible(true);
            } else if (c == sche) {
                Diary di = new Diary();
                di.setSize(1400, 500);
                di.setVisible(true);
            }
        } catch (Exception ex) {
        }
        return;
    }

    public static void main(String[] args) {
        Seoyoung seoyoung = new Seoyoung();
        seoyoung.setSize(500, 240);
        seoyoung.setVisible(true);
    }
}
