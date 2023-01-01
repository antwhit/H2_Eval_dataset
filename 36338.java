import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class DoubleList {

    private final double[] contents;

    DoubleList(double[] vals) {
        contents = new double[vals.length];
        for (int i = vals.length; i-- > 0; ) {
            contents[i] = Math.abs(vals[i]);
        }
        Arrays.sort(contents);
    }

    double get(int i) {
        return contents[i];
    }

    String[] toStringArray() {
        String[] ret = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            ret[i] = (new Double(get(i))).toString();
        }
        return ret;
    }

    int size() {
        return contents.length;
    }

    int getIndex(double d) {
        int index = Arrays.binarySearch(contents, d);
        if (index < 0) {
            index = -(index + 1);
        }
        if (index >= size()) {
            index = size() - 1;
        }
        return index;
    }
}
