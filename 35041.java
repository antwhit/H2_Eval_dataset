/**
 * Sub class of MethodInvocation to test CallNonVirtual<type>Method
 *
 * @author Ton Ngo
 */
class MethodInvocationSub extends MethodInvocation {

    public boolean virtualReturnBoolean(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return val8;
    }

    public byte virtualReturnByte(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return (byte) (val0 + 9);
    }

    public char virtualReturnChar(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        if (val1 == 'x') return 'q'; else return 'r';
    }

    public short virtualReturnShort(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return (short) (val2 + 29);
    }

    public int virtualReturnInt(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return val3 + 99;
    }

    public long virtualReturnLong(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return val4 + 2000;
    }

    public float virtualReturnFloat(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return val5 + (float) 64.0;
    }

    public double virtualReturnDouble(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return val6 + (double) 2000.0;
    }

    public void virtualReturnVoid(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        testFlagForVoid = 123;
    }

    public Object virtualReturnObject(byte val0, char val1, short val2, int val3, long val4, float val5, double val6, Object val7, boolean val8) {
        return new String("Hot stuff");
    }

    /************************************************************
   * Dummy constructor to get to the virtual methods
   */
    public MethodInvocationSub() {
    }
}
