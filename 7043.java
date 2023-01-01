import java.util.Comparator;

class DoubleComparator implements Comparator<Double> {

    public int compare(Double a, Double b) {
        return a.compareTo(b);
    }
}
