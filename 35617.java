import java.awt.*;

public class Engine extends Thread {

    app parent;

    Graphics sg;

    int i = 0;

    int xc, yc;

    static final double pi = Math.acos(-1.);

    static final double cvfact = pi / 180;

    ;

    int xa, ya, xda, yda;

    double xphase;

    double yphase;

    double xfreq;

    double yfreq;

    double fmax;

    double thetax, thetay;

    int xnew, ynew;

    public int segment[] = { 0, 0, 10, 10 };

    Engine(app _parent) {
        parent = _parent;
        sg = parent.stage.getGraphics();
        xc = parent.stage.getCenter().width;
        yc = parent.stage.getCenter().height;
        xa = parent.settings[0];
        ya = parent.settings[1];
        xfreq = ((double) parent.settings[2] / 10.);
        yfreq = ((double) parent.settings[3] / 10.);
        xphase = parent.settings[4] * cvfact;
        yphase = parent.settings[5] * cvfact;
        thetax = (float) (i) * cvfact + xphase;
        thetay = (float) (i) * cvfact + yphase;
        segment[0] = (int) (xc + xa * Math.sin(thetax));
        segment[1] = (int) (yc + ya * Math.cos(thetay));
        i++;
    }

    public void init() {
    }

    public void run() {
        while (((xa - (xda * i / 1000.)) > 1) & ((ya - (yda * i / 1000.)) > 1)) {
            xa = parent.settings[0];
            ya = parent.settings[1];
            xfreq = ((double) parent.settings[2] / 10.);
            yfreq = ((double) parent.settings[3] / 10.);
            xphase = parent.settings[4] * cvfact;
            yphase = parent.settings[5] * cvfact;
            xda = parent.settings[6];
            yda = parent.settings[7];
            fmax = Math.max(xfreq, yfreq);
            thetax = xfreq / fmax * (float) (i) * cvfact + xphase;
            thetay = yfreq / fmax * (float) (i) * cvfact + yphase;
            segment[2] = (int) (xc + (xa - (xda * i / 1000.)) * Math.sin(thetax));
            segment[3] = (int) (yc + (ya - (yda * i / 1000.)) * Math.cos(thetay));
            i++;
            sg.drawLine(segment[0], segment[1], segment[2], segment[3]);
            segment[0] = segment[2];
            segment[1] = segment[3];
            Thread.yield();
        }
        System.out.println("condition false");
        this.stop();
        parent.stageLock = 0;
    }
}
