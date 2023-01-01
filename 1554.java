import java.util.*;

class CombinationPair {

    private final int[] left;

    private final int[] right;

    private int order;

    public CombinationPair(Combination l, Combination r) {
        if (l.getFirst() < r.getFirst()) {
            left = l.getValues();
            right = r.getValues();
        } else {
            left = r.getValues();
            right = l.getValues();
        }
        order = -1;
        for (int n = left.length; n-- > 0; ) {
            if (left[n] < left.length) {
                order++;
            }
        }
    }

    public int getOrder() {
        return order;
    }

    public int[] getLeft() {
        return left;
    }

    public int getLeft(int n) {
        return left[n];
    }

    public int[] getRight() {
        return right;
    }

    public int getRight(int n) {
        return right[n];
    }

    public String toString() {
        return left.toString() + ":" + right.toString() + " (order=" + getOrder() + ")";
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if ((obj == null) || (obj.getClass() != this.getClass())) return false;
        CombinationPair p = (CombinationPair) obj;
        for (int n = left.length; n-- > 0; ) {
            if ((left[n] != p.left[n]) || (right[n] != p.right[n])) {
                return false;
            }
        }
        return true;
    }
}
