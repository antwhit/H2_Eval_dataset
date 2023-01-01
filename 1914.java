import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.EditorKit;
import javax.swing.text.html.HTMLEditorKit;

public class pamHelper extends JFrame {

    public pamHelper(String resourceStr, String titleStr) {
        JEditorPane jep = new JEditorPane();
        EditorKit ek = new HTMLEditorKit();
        ek.install(jep);
        jep.setEditorKit(ek);
        try {
            jep.read(getClass().getResourceAsStream(resourceStr), "");
        } catch (Exception e) {
            jep.setText("<html>Не удается прочитать [" + titleStr + "]</html>");
        }
        jep.setEditable(false);
        JScrollPane jsp = new JScrollPane(jep);
        jsp.setBorder(new TitledBorder(titleStr + " - Help topics"));
        dlg.getContentPane().add(jsp, "Center");
        JButton bOk = new JButton("Ok");
        dlg.getContentPane().add(jsp, "Center");
        JPanel pOk = new JPanel(new FlowLayout());
        pOk.add(bOk);
        bOk.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                dlg.dispose();
            }
        });
        dlg.getContentPane().add(pOk, "South");
        dlg.setSize(700, 640);
        Dimension winSize = dlg.getSize();
        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        dlg.setLocation(scrSize.width - winSize.width >> 1, scrSize.height - winSize.height >> 1);
    }

    public void show() {
        dlg.show();
    }

    final JDialog dlg = new JDialog(this, "Help", false);
}
