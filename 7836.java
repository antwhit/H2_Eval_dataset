public class IntVec {

    private int[] data;

    IntVec(int dim) {
        data = new int[dim];
    }

    IntVec(int x, int y) {
        data = new int[2];
        data[0] = x;
        data[1] = y;
    }

    IntVec(int x, int y, int z) {
        data = new int[2];
        data[0] = x;
        data[1] = y;
    }

    int x() {
        return data[0];
    }

    int y() {
        return data[1];
    }

    void x(int x) {
        data[0] = x;
    }

    void y(int y) {
        data[1] = y;
    }

    int get(int dim) {
        return data[dim];
    }

    void set(int dim, int value) {
        data[dim] = value;
    }

    public int hashCode() {
        int v = 0;
        for (int i = 0; i < data.length; i++) {
            v *= 10000;
            v += i;
        }
        return v;
    }

    public boolean equals(Object other) {
        if (!(other instanceof IntVec)) return false;
        IntVec iv = (IntVec) other;
        if (iv.data.length != data.length) return false;
        for (int i = 0; i < data.length; i++) if (data[i] != iv.data[i]) return false;
        return true;
    }
}
