public class Int {

    public Int() {
        value = 0;
    }

    public Int(int i) {
        value = i;
    }

    public Int(double d) {
        value = (int) d;
    }

    public Int(float f) {
        value = (int) f;
    }

    public Int(String s) {
        value = Integer.parseInt(s);
    }

    public int add(int i) {
        return value += i;
    }

    public int getIntVal() {
        return value;
    }

    public void setIntVal(int i) {
        value = i;
    }

    public String toString() {
        return String.valueOf(value);
    }

    int value;
}
