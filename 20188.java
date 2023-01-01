import java.util.Iterator;

class Combination {

    private int[] values;

    public Combination(int length) {
        values = new int[length];
    }

    public Combination(Combination c) {
        values = new int[c.values.length];
        for (int n = values.length; n-- > 0; ) {
            values[n] = c.values[n];
        }
    }

    public Combination(int[] cvalues) {
        values = new int[cvalues.length];
        for (int n = values.length; n-- > 0; ) {
            values[n] = cvalues[n];
        }
    }

    int[] getValues() {
        return values;
    }

    void setAt(int i, int n) {
        values[i] = n;
    }

    int getLength() {
        return values.length;
    }

    public boolean sameAs(Combination c) {
        if (getLength() != c.getLength()) {
            return false;
        }
        for (int n = values.length; n-- > 0; ) {
            if (values[n] != c.values[n]) {
                return false;
            }
        }
        return true;
    }

    public Combination inverse() {
        Combination result = new Combination(getLength());
        int slot = 0;
        for (int r = 0; r < 2 * getLength(); r++) {
            if (!this.contains(r)) {
                result.values[slot++] = r;
            }
        }
        return result;
    }

    public boolean contains(int i) {
        for (int n = values.length; n-- > 0; ) {
            if (values[n] == i) {
                return true;
            }
        }
        return false;
    }

    int getFirst() {
        return values[0];
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < getLength(); i++) {
            result += values[i] + " ";
        }
        return result;
    }
}
