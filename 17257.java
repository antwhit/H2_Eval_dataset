import java.io.Serializable;

/**
 * @author unascribed
 */
class TestSerialization implements Serializable {

    private static final class Obj implements Serializable {

        public Obj() {
        }
    }

    private boolean z;

    private byte b;

    private short h;

    private int i;

    private long j;

    private float f;

    private double d;

    private String s;

    private Obj o;

    private Object n;

    private boolean[] za;

    private byte[] ba;

    private short[] ha;

    private int[] ia;

    private long[] ja;

    private float[] fa;

    private double[] da;

    private String[] sa;

    private Obj[] oa;

    public TestSerialization() {
        z = true;
        b = (byte) 1;
        h = (short) 2;
        i = 3;
        j = 4;
        f = 5.0f;
        d = 6.0;
        s = "7";
        o = new Obj();
        n = null;
        za = new boolean[1];
        za[0] = z;
        ba = new byte[1];
        ba[0] = b;
        ha = new short[1];
        ha[0] = h;
        ia = new int[1];
        ia[0] = i;
        ja = new long[1];
        ja[0] = j;
        fa = new float[1];
        fa[0] = f;
        da = new double[1];
        da[0] = d;
        sa = new String[1];
        sa[0] = s;
        oa = new Obj[1];
        oa[0] = o;
    }

    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append("Z:").append(z).append("\n");
        res.append("B:").append(b).append("\n");
        res.append("S:").append(h).append("\n");
        res.append("I:").append(i).append("\n");
        res.append("J:").append(j).append("\n");
        res.append("F:").append(f).append("\n");
        res.append("D:").append(d).append("\n");
        res.append("Ljava/lang/String;:").append(s).append("\n");
        res.append("Ljava/lang/Object;:").append(o).append("\n");
        res.append("null:").append(n).append("\n");
        res.append("[Z:").append(za[0]).append("\n");
        res.append("[B:").append(ba[0]).append("\n");
        res.append("[S:").append(ha[0]).append("\n");
        res.append("[I:").append(ia[0]).append("\n");
        res.append("[J:").append(ja[0]).append("\n");
        res.append("[F:").append(fa[0]).append("\n");
        res.append("[D:").append(da[0]).append("\n");
        res.append("[Ljava/lang/String;:").append(sa[0]).append("\n");
        res.append("[Ljava/lang/Object;:").append(oa[0]).append("\n");
        return res.toString();
    }
}
