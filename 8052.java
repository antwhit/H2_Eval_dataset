import java.util.Vector;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;

class Igraph extends JPanel {

    Idata idata;

    EEG eeg;

    Dimension csize = new Dimension(0, 0);

    Color colback = Color.lightGray;

    Color coltrace = new Color(0.0F, 0.0F, 0.5F);

    Color[] colscarr;

    Color coldiff = Color.black;

    Color colsamp = new Color(0.7F, 0.0F, 0.7F);

    public int currelec = 0;

    public int currsub = -1;

    Igraph(EEG leeg, Idata lidata) {
        super(true);
        eeg = leeg;
        idata = lidata;
        colscarr = new Color[50];
        for (int i = 0; i < colscarr.length; i++) {
            float h = (i * 43 % 100) / 100.0F;
            colscarr[i] = new Color(h * 0.7F, 0.0F, (1.0F - h) * 0.7F);
        }
    }

    void drawEtrace(Graphics g, double[][] ins, int i, double scay, double offy) {
        g.drawLine(0, csize.height - (int) offy, csize.width, csize.height - (int) offy);
        int prevx = 0;
        int prevy = 0;
        for (int j = 0; j < idata.nPoints; j++) {
            int nextx = (j * csize.width) / (idata.nPoints - 1);
            int nexty = csize.height - (int) (ins[j][i] * scay + offy);
            if (j != 0) g.drawLine(prevx, prevy, nextx, nexty);
            prevx = nextx;
            prevy = nexty;
        }
    }

    public void paintComponent(Graphics g) {
        if ((getSize().height != csize.height) || (getSize().width != csize.width)) {
            csize.height = getSize().height;
            csize.width = getSize().width;
        }
        g.setColor(colback);
        g.fillRect(0, 0, csize.width, csize.height);
        if (eeg.imap.activenode != -1) {
            g.setColor(coltrace);
            drawEtrace(g, idata.electrodeins, eeg.imap.activenode, idata.insfac * csize.height / 2.0F, csize.height / 2.0F);
        }
        for (int i = 0; i < eeg.ibrainpatterns.BrainMapCount(); i++) {
            if (eeg.ibrainpatterns.GetBrainMap(i).bActive) {
                g.setColor(colscarr[i % colscarr.length]);
                drawEtrace(g, idata.subspacescalars, i, idata.sscafac * csize.height / 2.0, csize.height / 2.0F);
                if (i >= eeg.ibrainpatterns.nchosenbrainpatterns) {
                    g.setColor(colsamp);
                    int d = i - eeg.ibrainpatterns.nchosenbrainpatterns;
                    int x = (idata.subspacei[d] * csize.width) / (idata.nPoints - 1);
                    g.drawLine(x, 0, x, csize.height);
                }
            }
        }
        if (eeg.vectracetype == 2) {
            g.setColor(coldiff);
        }
    }
}
