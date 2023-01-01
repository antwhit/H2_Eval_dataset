public class ListButton extends javax.swing.JToggleButton implements java.awt.event.ActionListener {

    Frame myFrame;

    public ListButton(String name, Frame frame) {
        super(name);
        myFrame = frame;
        setBorderPainted(false);
        setFont(new java.awt.Font("Dialog", 0, 10));
        addActionListener(this);
    }

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (myFrame.isSelected()) {
            myFrame.setVisible(false);
            try {
                myFrame.setSelected(false);
            } catch (Exception e) {
            }
            setSelected(false);
        } else {
            myFrame.setVisible(true);
            setRed(false);
            try {
                myFrame.setSelected(true);
            } catch (Exception e) {
            }
        }
    }

    public void setRed(boolean isRed) {
        if (isRed) setForeground(java.awt.Color.RED); else setForeground(java.awt.Color.BLACK);
    }
}
