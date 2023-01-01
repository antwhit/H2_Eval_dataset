import java.awt.*;
import javax.swing.*;

public class UnitButton extends JButton {

    private int unitID;

    public UnitButton(int unitID, String unitName, ImageIcon unitIcon) {
        super(unitName, unitIcon);
        this.unitID = unitID;
        setFont(new Font("Helvetica", Font.BOLD, 9));
        setVerticalTextPosition(SwingConstants.BOTTOM);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setAlignmentX(.5F);
        setAlignmentY(.5F);
    }

    public int getUnitID() {
        return unitID;
    }
}
