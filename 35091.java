public class main {

    static {
        try {
            System.loadLibrary("simple");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load. See the chapter on Dynamic Linking Problems in the SWIG Java documentation for help.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[]) {
        System.out.println("Drawing some basic shapes");
        SWIGTYPE_p_ColorMap cmap = simple.new_ColorMap(null);
        SWIGTYPE_p_FrameBuffer f = simple.new_FrameBuffer(400, 400);
        simple.FrameBuffer_clear(f, (short) simple.BLACK);
        simple.FrameBuffer_box(f, 40, 40, 200, 200, (short) simple.RED);
        simple.FrameBuffer_circle(f, 200, 200, 40, (short) simple.BLUE);
        simple.FrameBuffer_line(f, 10, 390, 390, 200, (short) simple.GREEN);
        simple.FrameBuffer_writeGIF(f, cmap, "image.gif");
        System.out.println("Wrote image.gif");
        simple.delete_FrameBuffer(f);
        simple.delete_ColorMap(cmap);
    }
}
