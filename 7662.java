class SelectTest {

    public static native void doit();

    public static void main(String[] args) {
        System.loadLibrary("SelectTest");
        doit();
    }
}
