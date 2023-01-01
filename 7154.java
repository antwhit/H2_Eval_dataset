import javax.swing.*;

public class LineItem extends JFrame {

    private JCheckBox chkShortCut, chkWharf;

    private JTextField txtPath;

    private JButton btnIcon, btnPathBrowse;

    public LineItem() {
        btnPathBrowse = new JButton("Browse");
        chkShortCut = new JCheckBox();
        chkWharf = new JCheckBox();
        txtPath = new JTextField(20);
        btnIcon = new JButton("Browse");
    }
}
