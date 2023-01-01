public class T32v1 {

    public static void main(String[] args) {
        boolean b1 = "" + 1 instanceof String;
        boolean b2 = "" + 1L instanceof String;
        boolean b3 = "" + 1D instanceof String;
        boolean b4 = "" + 1F instanceof String;
        boolean b5 = "" + 0x1 instanceof String;
        boolean b6 = "" + 0xA instanceof String;
        boolean b7 = "" + '1' instanceof String;
        boolean b8 = "" + 1. instanceof String;
        boolean b9 = "" + .1 instanceof String;
    }
}
