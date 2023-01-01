/**
 * Just a random test class meant to demonstrate aggregations.
 *
 * @author Randy Gettman
 * @since 0.1.0
 */
public class Record implements Comparable<Record> {

    private String myCategory1;

    private int myCategory2;

    private String myCategory3;

    private String myCategory4;

    private int myValue1;

    private double myValue2;

    public Record(String category1, int category2, String category3, String category4, int value1, double value2) {
        myCategory1 = category1;
        myCategory2 = category2;
        myCategory3 = category3;
        myCategory4 = category4;
        myValue1 = value1;
        myValue2 = value2;
    }

    public String getCategory1() {
        return myCategory1;
    }

    public void setCategory1(String myCategory1) {
        this.myCategory1 = myCategory1;
    }

    public int getCategory2() {
        return myCategory2;
    }

    public void setCategory2(int myCategory2) {
        this.myCategory2 = myCategory2;
    }

    public String getCategory3() {
        return myCategory3;
    }

    public void setCategory3(String myCategory3) {
        this.myCategory3 = myCategory3;
    }

    public String getCategory4() {
        return myCategory4;
    }

    public void setCategory4(String myCategory4) {
        this.myCategory4 = myCategory4;
    }

    public int getValue1() {
        return myValue1;
    }

    public void setMalue1(int myValue1) {
        this.myValue1 = myValue1;
    }

    public double getValue2() {
        return myValue2;
    }

    public void setValue2(double myValue2) {
        this.myValue2 = myValue2;
    }

    public int compareTo(Record other) {
        int comp = myCategory1.compareTo(other.myCategory1);
        if (comp != 0) return comp;
        comp = myCategory2 - other.myCategory2;
        if (comp != 0) return comp;
        comp = myCategory3.compareTo(other.myCategory3);
        if (comp != 0) return comp;
        comp = myCategory4.compareTo(other.myCategory4);
        return comp;
    }
}
