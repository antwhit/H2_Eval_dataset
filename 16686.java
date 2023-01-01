import javax.swing.*;

class JListCodeRender extends JLabel implements ListCellRenderer {

    static final ImageIcon blankIcon = new ImageIcon("images/blank_dot.gif");

    static final ImageIcon breakIcon = new ImageIcon("images/break_dot.gif");

    static final ImageIcon arrowIcon = new ImageIcon("images/arrow.gif");

    static final ImageIcon arrow_bp_Icon = new ImageIcon("images/arrow_bp.gif");

    BreakpointDebugWindow bpwindow;

    ProgramMemoryWindow progwindow;

    JListCodeRender(BreakpointDebugWindow bp, ProgramMemoryWindow progwin) {
        super();
        bpwindow = bp;
        progwindow = progwin;
    }

    public java.awt.Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String s = (String) value;
        setIcon(null);
        if (s != null) {
            setText(s);
            setToolTipText(progwindow.getToolTip(index));
            if (bpwindow.parent.cpu.getPc() == index) setIcon(arrowIcon);
            if (bpwindow.isBreakpoint(index)) {
                if (bpwindow.parent.cpu.getPc() == index) setIcon(arrow_bp_Icon); else setIcon(breakIcon);
            }
            if (getIcon() == null) setText("   " + s);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
        } else setText("");
        return this;
    }
}
