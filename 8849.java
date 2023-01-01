import java.awt.*;

public class EPSList extends List {

    private Dimension ps;

    public EPSList(Dimension d) {
        ps = d;
    }

    public EPSList() {
        ps = new Dimension(200, 200);
    }

    public Dimension getMinimumSize() {
        return (new Dimension(50, 15));
    }

    public Dimension getPreferredSize() {
        return (ps);
    }

    public void setPreferredSize(Dimension ps) {
        this.ps = ps;
    }
}
