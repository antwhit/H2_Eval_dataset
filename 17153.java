import java.awt.*;
import java.text.DecimalFormat;

public class PoneInputDMS extends PoneInput {

    private NSEW OArea;

    private Label dgLabel = new Label("°", Label.LEFT);

    private Label mnLabel = new Label("\'", Label.LEFT);

    private Label secLabel = new Label("\"", Label.LEFT);

    private PosField PosSec;

    private PosField PosMin;

    private PosField PosDegree;

    public PoneInputDMS(boolean isLat) {
        OArea = isLat ? new NSEW('N') : new NSEW('E');
        PosDegree = new PosField(false, 2, isLat ? 90d : 180d);
        PosMin = new PosField(false, 2, 60d);
        PosSec = new PosField(false, 2, 60d);
        add(PosDegree);
        add(dgLabel);
        add(PosMin);
        add(mnLabel);
        add(PosSec);
        add(secLabel);
        add(OArea);
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    }

    public void reset() {
        PosDegree.setText("0");
        PosMin.setText("0");
        PosSec.setText("0");
    }

    public void setPone(Pone newP) {
        PosDegree.setText("" + (int) (Math.abs(newP.p)));
        PosMin.setText(newP.toStringMin0());
        PosSec.setText("" + ((int) newP.toStringSec()));
        OArea.setSign(newP.p);
    }

    public Pone getPone() {
        return (new Pone((PosDegree.val() + PosMin.val() / 60d + PosSec.val() / 3600d) * OArea.sign));
    }
}
