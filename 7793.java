public class OBSqrtTbl {

    private long swigCPtr;

    protected boolean swigCMemOwn;

    protected OBSqrtTbl(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    protected static long getCPtr(OBSqrtTbl obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    protected void finalize() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0 && swigCMemOwn) {
            swigCMemOwn = false;
            openbabelJNI.delete_OBSqrtTbl(swigCPtr);
        }
        swigCPtr = 0;
    }

    public OBSqrtTbl() {
        this(openbabelJNI.new_OBSqrtTbl__SWIG_0(), true);
    }

    public OBSqrtTbl(double max, double incr) {
        this(openbabelJNI.new_OBSqrtTbl__SWIG_1(max, incr), true);
    }

    public double Sqrt(double d2) {
        return openbabelJNI.OBSqrtTbl_Sqrt(swigCPtr, this, d2);
    }

    public void Init(double max, double incr) {
        openbabelJNI.OBSqrtTbl_Init(swigCPtr, this, max, incr);
    }
}
