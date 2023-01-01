import java.awt.*;

public class ChartRect {

    protected Image chart;

    protected int latMin;

    protected int longMin;

    protected int dLat;

    protected int dLong;

    protected int ch0;

    protected int cw0;

    private int dpd;

    private int zoom = 100;

    private String filename;

    private ChartRect south;

    static int gid = 100;

    int myID = 42;

    public ChartRect(ChartRect nxt, String fn, int nlat, int ndLat, int nlong, int ndLong, int ndpd) {
        filename = fn;
        south = nxt;
        latMin = nlat;
        longMin = nlong;
        dLat = ndLat;
        dLong = ndLong;
        dpd = ndpd;
        gid++;
        myID = gid;
    }

    public String toString() {
        return (filename);
    }

    public String toStringQ() {
        return (filename + "Q=" + dpd);
    }

    int getDPD() {
        return (dpd);
    }

    public String epsString(int lvl) {
        return ("fn=" + filename + " latMin=" + latMin + " longMin=" + longMin + " dLat=" + dLat + " dLong=" + dLong + " cw0=" + cw0 + " ch0=" + ch0 + "z=" + zoom + " ID=" + myID);
    }

    public double getLong() {
        return (longMin / 60d);
    }

    public double getLat() {
        return (latMin / 60d);
    }

    public double getdLong() {
        return (dLong / 60d);
    }

    public double getdLat() {
        return (dLat / 60d);
    }

    public void insert(String fn, int nlat, int ndLat, int nlong, int ndLong, int ndpd) {
        if (nlat == latMin && dLong == ndLong && dLat == ndLat) {
            CAux.perr("double insertion", -1);
        } else if (south != null && nlat <= south.latMin) {
            south.insert(fn, nlat, dLat, nlong, dLong, ndpd);
        } else {
            south = new ChartRect(south, fn, nlat, dLat, nlong, dLong, ndpd);
        }
    }

    public ChartRect find(int nlat, int ndLat) {
        ChartRect res = null;
        if ((nlat < latMin) && south != null) {
            return (south.find(nlat, ndLat));
        } else if ((latMin <= nlat) && ((latMin + dLat) >= (nlat + ndLat))) {
            CAux.perr("ChartRect FOUND image " + this, 4);
            return (this);
        } else if (south != null) {
            CAux.perr("go south 2" + this, 7);
            return (south.find(nlat, ndLat));
        } else {
            CAux.perr("go back " + this, 7);
            return (null);
        }
    }

    public String dump() {
        return ("\n    C" + latMin + "," + longMin + " " + toString() + (south == null ? "-" : south.dump()));
    }
}
