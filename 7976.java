public class HelloUniverse {

    public void printHelloUniverse() {
        System.out.println("Hello Universe!!!");
    }

    public void printCustomString(String str) {
        System.out.println(str);
    }

    public void passArrayOfIntegers(int[] array) {
        for (int i = 0; i < array.length; i++) System.out.println("array[" + i + "] = " + array[i]);
    }

    public float[] getArrayOfFloats() {
        float[] res = { 2.43f, 835.34536f, 2456.234f, -234.213f };
        return res;
    }

    public String getIntAsString(int i) {
        String s = String.valueOf(i);
        return s;
    }

    public float sumOfFloatAndInt_AsFloat(float fl, int i) {
        System.out.println("float: " + fl);
        System.out.println("integer: " + i);
        return (float) (fl + i);
    }

    public void passLong(long l) {
        System.out.println("Maximal value of Java long is:     " + Long.MAX_VALUE);
        System.out.println("Maximal value of C++ long long is: 9223372036854775807");
        System.out.println("Value passed from Python as long:  " + l);
    }
}
