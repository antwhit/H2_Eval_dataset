public class Blort {

    public boolean insBoolean;

    public byte insByte;

    public char insChar;

    public short insShort;

    public int insInt;

    public long insLong;

    public float insFloat;

    public double insDouble;

    public Object insObject;

    public Object test1() {
        int x = insByte + insChar + insShort + insInt + (int) insLong + (int) insFloat + (int) insDouble;
        if (insBoolean && (x > 0)) {
            ;
            return insObject;
        } else {
            return null;
        }
    }

    public void test2(boolean b, int i, Object o) {
        insBoolean = b;
        insByte = (byte) i;
        insChar = (char) i;
        insShort = (short) i;
        insInt = i;
        insLong = i;
        insFloat = i;
        insDouble = i;
        insObject = o;
    }
}
