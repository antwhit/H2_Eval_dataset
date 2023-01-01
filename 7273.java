import java.awt.*;

/** Convenience class for creating grid bag constraints. */
public class GridBagConstraints2 extends GridBagConstraints {

    public GridBagConstraints2() {
        super();
    }

    public GridBagConstraints2(int gridx, int gridy, int gridw, int gridh, double weightx, double weighty, int anchor, int fill, Insets insets, int ipadx, int ipady) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridw;
        this.gridheight = gridh;
        this.weightx = weightx;
        this.weighty = weighty;
        this.anchor = anchor;
        this.fill = fill;
        this.insets = insets;
        this.ipadx = ipadx;
        this.ipady = ipady;
    }
}
